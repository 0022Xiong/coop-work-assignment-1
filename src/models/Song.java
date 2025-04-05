package models;

import java.util.Objects;

public class Song {

    private Artist artist;
    private int songId = 9999;
    private int length = 1;
    private String name = "";

    public int getSongId() {
        return songId;
    }
    public void setSongId(int songId) {
        if(songId <= 9999 && songId >= 1000){
            this.songId = songId;
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name.length() <= 20) {
            this.name = name;
        }//create and update
        else if(this.name.isEmpty()) {
            this.name = name.substring(0,20);
        }//create for > 20
    }

    //TODO The song's artist (Artist artist)
    //    You should have already written the Artist class #
    //     When creating the song, you should have the artist object as a parameter

    public void setLength(int seconds) {
       if(seconds <= 600 && seconds >= 1){
           this.length = seconds;
       }
    }
    public int getLength() {
        return length;
    }

    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Song(int songId, String name, String artistNameInput, boolean verified, int length){
        setSongId(songId);
        setName(name);
        Artist artistIn = new Artist(artistNameInput, verified);
        setArtist(artistIn);
        setLength(length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songId == song.songId && length == song.length && Objects.equals(artist, song.artist) && Objects.equals(name, song.name);
    }

    public String toString(){
        return "SongID is: " + songId + "\n"+
               "Name is: " + name +"\n"+
               artist + "\n" +
               "The time of song is " + length + " seconds"+ "\n";
    }

}