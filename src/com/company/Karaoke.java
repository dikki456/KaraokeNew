package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Karaoke {
    public static void main(String[] args) throws IOException {

        SongBook songBook = new SongBook();
        songBook.importFile("songData.txt");
        KaraokeMachine machine = new KaraokeMachine(songBook);
        machine.run();
        songBook.exportFile("song.txt");



    }
}
