package com.teillet.bibliothequeElement.graphicInterface.test;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ThumbnailFFMPEG {
    public static void main(String[] args) throws IOException {
        FFmpeg ffmpeg = new FFmpeg("C:\\Program Files\\ffmpeg-4.4-full_build\\bin\\ffmpeg.exe");
        FFprobe ffprobe = new FFprobe("C:\\Program Files\\ffmpeg-4.4-full_build\\bin\\ffprobe.exe");

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput("C:\\Users\\teill\\IdeaProjects\\Bibliotheque-Element\\src\\main\\resources\\bibliothequeElement\\element\\BigBuckBunny.mp4")
                .overrideOutputFiles(true)

                .addOutput("preview/test.png")
                    .setFrames(1)
                    .setVideoFilter("select='gte(n\\,10)',scale=200:-1")
                    .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder).run();

    }
}
