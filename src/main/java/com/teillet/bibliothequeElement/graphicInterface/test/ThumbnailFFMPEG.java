package com.teillet.bibliothequeElement.graphicInterface.test;

import com.teillet.bibliothequeElement.utils.FfmpegUse;

import java.io.IOException;

public class ThumbnailFFMPEG {
    public static void main(String[] args) throws IOException {
/*        FFmpeg ffmpeg = new FFmpeg("src/main/resources/bibliothequeElement/ffmpeg/windows/ffmpeg.exe");
        FFprobe ffprobe = new FFprobe("src/main/resources/bibliothequeElement/ffmpeg/windows/ffprobe.exe");

        String file = "C:\\Users\\teill\\test_element\\BigBuckBunny.mp4";

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(file)
                .overrideOutputFiles(true)

                .addOutput("preview/test.jpeg")
                    .setFrames(1)
                    .setVideoFilter("select='gte(n\\,10)',scale=w='min(100\\, iw*3/2):h=-1'")
                    .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        FFmpegJob job = executor.createJob(builder);
        job.run();*/

        String inputFile = "C:\\Users\\teill\\test_element\\BigBuckBunny.mp4";
        String outputFile = "preview/test.jpeg";

        FfmpegUse ffmpeg = new FfmpegUse();
        ffmpeg.thumbnail(inputFile, outputFile, new Object());



    }
}
