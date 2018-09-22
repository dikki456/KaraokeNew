package com.company;


import java.io.*;
import java.util.*;

public class SongBook {

    private List<Song> mSong;

    public SongBook() {

        mSong = new ArrayList<>();

    }

    public void addSongBook(Song song) {

        mSong.add(song);

    }

    public int getSongCount() {

        return mSong.size();
    }


    private Map<String, List<Song>> byArtist() {
        Map<String, List<Song>> byArtist = new HashMap<>();
        for (Song song : mSong) {

            List<Song> artistSongs = byArtist.get(song.getArtist());
            if (artistSongs == null) {
                artistSongs = new ArrayList<>();
                byArtist.put(song.getArtist(), artistSongs);

            }
            artistSongs.add(song);
        }

        return byArtist;
    }

    public Set<String> getArtist() {
        return byArtist().keySet();
    }

    public List<Song> getSongForArtist(String artistName) {

        return byArtist().get(artistName);
    }

    public void exportFile(String fileName) throws FileNotFoundException {

        FileOutputStream output = new FileOutputStream(fileName);
        PrintWriter write = new PrintWriter(output);
        for(Song song: mSong) {
            write.printf("%s|%s|%s%n",
                    song.getArtist(),song.getTitle(),song.getVideoURL());
        }
    }

    public void importFile(String fileName) throws IOException {
        FileInputStream input = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        while (( line = reader.readLine())!= null ){
            String[] args = line.split("\\|");
            addSongBook(new Song(args[0],args[1],args[2]));

        }
    }
}
