/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2020 Caprica Software Limited.
 */

package com.teillet.bibliothequeElement.graphicInterface.test;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;


public class SnapshotTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Debut");
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        final EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
        mediaPlayer.snapshots().setSnapshotDirectory("C:\\Users\\teill\\Videos\\Test");
        final long[] interval = new long[1];
        final boolean[] finished = new boolean[1];
        MediaPlayerEventAdapter mpea =  new MediaPlayerEventManager();
        mediaPlayer.events().addMediaPlayerEventListener(mpea);

        boolean res = mediaPlayer.media().play("C:\\Users\\teill\\IdeaProjects\\Bibliotheque-Element\\src\\main\\resources\\bibliothequeElement\\element\\BigBuckBunny.mp4");

        Thread.sleep(20000);

    }
}
