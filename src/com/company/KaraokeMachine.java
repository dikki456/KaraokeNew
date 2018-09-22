package com.company;

import java.io.IOException;
import java.util.*;

public class KaraokeMachine {

    private SongBook mSongBook = new SongBook();
    private Scanner input;
    private Queue<Song> mSongQueue;
    public Map<String, String> mMenu;


    public KaraokeMachine(SongBook songBook) {

        input = new Scanner(System.in);
        mSongBook = songBook;
        mSongQueue = new    ArrayDeque<>();
        mMenu = new HashMap<String, String>();
        mMenu.put("add", "Add a new song to the songbook");
        mMenu.put("choose", "Select a song by artist");
        mMenu.put("play", "Play next song in the queue");
        mMenu.put("quit", "Exit the program");
        mMenu.put("git" ,"commit");

    }

    private String promptAction() {

        System.out.printf("You have %s options  and %s in queue%n", mSongBook.getSongCount(), mSongQueue.size());
        for (Map.Entry<String, String> option : mMenu.entrySet()) {

            System.out.printf("%s - %s %n", option.getKey(), option.getValue());
        }
        String choices = "";
        System.out.println("What is your choice : ");
        choices = input.nextLine();
        return choices.trim().toLowerCase();
    }

    public void run() throws IOException {

        String choice = "";
        do {

            choice = promptAction();

            switch (choice) {

                case "add":
                    Song song = promptAddSong();
                    mSongBook.addSongBook(song);
                    System.out.printf("Added %s %n%n", song);
                    break;

                case "choose":
                    String artist = promptArtist();
                    Song artistSong = promptForArtistSongs(artist);
                    mSongQueue.add(artistSong);
                    System.out.printf(" %s %n", artistSong);
                    break;
                case "play":
                    play();
                    break;
                case "quit":
                    System.out.println("Closing the program");


                    break;
                default:
                    System.out.printf("Unknown choice : %s  %n%n", choice);
            }
        }
        while (!choice.equals("quit"));
    }


    public Song promptAddSong() {

        System.out.printf("Enter Artist name :  ");
        String artist = input.nextLine();
        System.out.printf("Enter Title name :  ");
        String title = input.nextLine();
        System.out.printf("Enter VideoURL name :  ");
        String VideoURL = input.nextLine();
        return new Song(artist, title, VideoURL);

    }

    private String promptArtist() throws IOException {

        System.out.println("Avalaible artists:  ");
        List<String> artists = new ArrayList<>(mSongBook.getArtist());
        int index = promptForIndex(artists);
        return artists.get(index);
    }

    private Song promptForArtistSongs(String artist) throws IOException {

        List<Song> songs = mSongBook.getSongForArtist(artist);
        List<String> songTitle = new ArrayList<>();
        for (Song song : songs) {
            songTitle.add(song.getTitle());
        }
        int index = promptForIndex(songTitle);
        return songs.get(index);
    }

    private int promptForIndex(List<String> options) throws IOException {
        int counter = 1;
        for (String option : options) {

            System.out.printf("%d. %s %n", counter, option);
            counter++;
        }
        System.out.print("Your choice s:  ");
        String optionAsString = input.nextLine();
        int choice = Integer.parseInt(optionAsString.trim());
        return choice - 1;
    }

    public void play(){

        Song song  = mSongQueue.poll();
        if(song == null){
            System.out.printf("There are no songs in queue%n");
        }
        else {
            System.out.printf("%n%n%n Open %s to hear %s by %s %n%n%n%n",
                    song.getVideoURL(),song.getTitle(),song.getArtist());
        }
    }

}



