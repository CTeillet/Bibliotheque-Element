package com.teillet.bibliothequeElement.utils;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FfmpegUse {
    private final String ffmpegPath;
    private final String ffprobePath;

    public FfmpegUse(){
        ffmpegPath = "src/main/resources/bibliothequeElement/ffmpeg/windows/ffmpeg.exe";
        ffprobePath = "src/main/resources/bibliothequeElement/ffmpeg/windows/ffprobe.exe";
    }

    public void thumbnail(String inputFile, String outputFile, Object foo) throws IOException {
        FFmpeg ffmpeg = new FFmpeg(this.ffmpegPath);
        FFprobe ffprobe = new FFprobe(this.ffprobePath);
        FFmpegProbeResult in = ffprobe.probe(inputFile);

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(in)
                .overrideOutputFiles(true)

                .addOutput(outputFile)
                .setFrames(1)
                .setVideoFilter("select='gte(n\\,10)',scale=w='min(100\\, iw*3/2):h=-1'")
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        FFmpegJob job = executor.createJob(builder, new ProgressListener() {

            // Using the FFmpegProbeResult determine the duration of the input
            final double duration_ns = in.getFormat().duration * TimeUnit.SECONDS.toNanos(1);

            @Override
            public void progress(Progress progress) {
                if (progress.status== Progress.Status.END) {
                    synchronized (foo) {
                        try {
                            foo.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                double percentage = progress.out_time_ns / duration_ns;

                // Print out interesting information about the progress
                System.out.printf(
                        "[%.0f%%] status:%s frame:%d time:%s ms fps:%.0f speed:%.2fx%n",
                        percentage * 100,
                        progress.status,
                        progress.frame,
                        FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
                        progress.fps.doubleValue(),
                        progress.speed
                );
            }
        });
        job.run();
    }

}
