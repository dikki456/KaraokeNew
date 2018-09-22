package com.company;


public class Song {

    private String mArtist;
    private String mTitle;
    private String mVideoURL;

    public Song(String mArtist, String mTitle, String mVideoURL) {
        this.mArtist = mArtist;
        this.mTitle = mTitle;
        this.mVideoURL = mVideoURL;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getVideoURL() {
        return mVideoURL;
    }

    public void setVideoURL(String mVideoURL) {
        this.mVideoURL = mVideoURL;
    }

    @Override
    public String toString() {

        return String.format("Song: %s by %s", mTitle, mArtist);

    }
}
