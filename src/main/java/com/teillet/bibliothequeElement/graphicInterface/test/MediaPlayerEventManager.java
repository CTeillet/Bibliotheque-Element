package com.teillet.bibliothequeElement.graphicInterface.test;

import uk.co.caprica.vlcj.media.MediaRef;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

public class MediaPlayerEventManager extends MediaPlayerEventAdapter {
    private final float interval = 0.01f ;
    private long length;

    @Override
    public void error(MediaPlayer mediaPlayer) {
        System.out.println("Error");
    }

    @Override
    public void chapterChanged(MediaPlayer mediaPlayer, int newChapter) {
        System.out.println("Chapter changed");
    }

    @Override
    public void mediaChanged(MediaPlayer mediaPlayer, MediaRef media) {
        System.out.println("Media changed");
    }

    @Override
    public void playing(MediaPlayer mediaPlayer) {
        System.out.println("Playing");
        mediaPlayer.controls().skipPosition(interval);
    }

    @Override
    public void opening(MediaPlayer mediaPlayer) {
        System.out.println("opening");
    }

    @Override
    public void buffering(MediaPlayer mediaPlayer, float newCache) {
        System.out.println("Buffering");
    }

    @Override
    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
        System.out.println("Time changed time : " + newTime);
        mediaPlayer.snapshots().save();
    }

    @Override
    public void forward(MediaPlayer mediaPlayer) {
        System.out.println("Forward");
        mediaPlayer.snapshots().save();
    }

    @Override
    public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {
        System.out.println("snapshotTaken(filename=" + filename + ")");
        mediaPlayer.controls().skipPosition(interval);
    }

    @Override
    public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
        length = newLength;
        System.out.println("Duration : " + newLength);
        //interval = newLength / 5;
        System.out.println("Interval : " + interval);
    }

    @Override
    public void mediaPlayerReady(MediaPlayer mediaPlayer) {
        System.out.println("Ready");
    }

    @Override
    public void finished(MediaPlayer mediaPlayer) {
        System.out.println("Finished");
        //finished[0]= true;
    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {
        System.out.println("stopped");
    }

    @Override
    public void paused(MediaPlayer mediaPlayer) {
        System.out.println("Paused");
    }

}
