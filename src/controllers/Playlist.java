package controllers;

import models.Artist;
import models.Song;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
public class Playlist {

    private String playlistName = ""; // valid length is 20 - default to the first 20 characters of input.
    private ArrayList<Song> songs; // should start empty
    private String description = ""; // valid length is 30 - default to the first 30 characters of input.
    private int likes = 0;

    public String getPlaylistName() {
        return playlistName;
    }
    public void setPlaylistName(String playlistName) {
        if(playlistName.length() <= 20) {
            this.playlistName = playlistName;
        }//create and update
        else if(this.playlistName.isEmpty()) {
            this.playlistName = playlistName.substring(0,20);
        }//create for playlistName's length larger than 20
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if(description.length() <= 30) {
            this.description = description;
        }//create and update
        else if(this.description.isEmpty()) {
            this.description = description.substring(0,30);
        }//create for description's length larger than 30
    }

    public Playlist(String playlistName, String description) {
        songs = new ArrayList<>();
        setPlaylistName(playlistName);
        setDescription(description);
    }

//    public Playlist() {
//         songs = new ArrayList<>();
//    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        if(likes >= 0){
            this.likes = likes;
        }//avoid negative int
    }

    //-------------------------------------
    //  ARRAYLIST CRUD
    //-------------------------------------

    public boolean addSong(Song song) {
        return songs.add(song);//An optimized way
    }

    public boolean updateSong(int index, Song song) {
        if(isValidIndex(index)) {
            songs.set(index, song);
            return true;
        }
        return false;
    }//no boolean return for .set

    public Song deleteSong(int index) {
        if(isValidIndex(index)) {
            return songs.remove(index);//An optimized way
        }
        return null;
    }

    public void addLike() {
        likes++;
    }

    //-------------------------------------
    //  ARRAYLIST - Utility methods
    //-------------------------------------

    private boolean isValidIndex(int index) {
        return (index < songs.size() && index >= 0);
    }

    private Song findSongIndex(int index) {
        if(isValidIndex(index)) {
            return songs.get(index);
        }
        return null;
    }//add -Index to avoid the conflict

    public Song findSongName(String songName) {
        if(!songs.isEmpty()){
            for(Song song : songs) {
                if(song.getName().equals(songName)){
                    return song;
                }
            }
            return null;
        }
        return null;
    }//add -Name to avoid the conflict

    //-------------------------------------
    //  ARRAYLIST -  Verified Status Update
    //-------------------------------------

    public Song updateVerifiedStatus(int index, boolean verified) {
        if(isValidIndex(index)) {
            songs.get(index).getArtist().setVerified(verified);
            return songs.get(index);
        }
        return null;
    }

    //-------------------------------------
    //  Counting Methods
    //-------------------------------------

    public int numberOfSongs() {
        return songs.size();
    }

    public int numberOfShortSongs() {
        int index = 0;
        int total = 0;
        while(index <= songs.size()) {
            if(songs.get(index).getLength() <= 180){
                total++;
            }
            index++;
        }
        return total;
    }

    public int getTotalPlayListLength() {
        if(!songs.isEmpty()){
            int index = 0;
            int totalTime = 0;
            while(index < songs.size()) {
                totalTime += songs.get(index).getLength();
                index++;
            }
            return totalTime;
        }
        return -1;
    }

    public int getAverageSongLength() {
        if(getTotalPlayListLength() != -1) {
            int averageSongLength = getTotalPlayListLength() / songs.size();
            return averageSongLength;
        }
        return -1;
    }

    //------------------------------------
    // LISTING METHODS - Basic and Advanced
    //------------------------------------

    public String listAllSongs() {
        if(!songs.isEmpty()) {
            String songInList = "";
            for(int index = 0; index < songs.size(); index++) {
                songInList += index + ": " + "\n" + songs.get(index) + "\n";
            }
            return "Songs from playlist: " + getPlaylistName() + "\n" + songInList;
        }
        return "No songs in playlist.";
    }

    public String listSongsFromVerifiedArtists() {
        if(!songs.isEmpty()) {
            String songList = "";
            for(int index = 0; index < songs.size(); index++){
                if(songs.get(index).getArtist().isVerified()) {
                    songList += index + ": " + songs.get(index);
                }
            }
            if(songList.isEmpty()){
                return "There are no songs from verified artists on this playlist.";
            }
            return songList;
        }
        return "No songs in playlist.";
    }

    public String listSongsLongerThan(int length) {
        if(!songs.isEmpty()){
            String songList = "";
            for(int index = 0; index < songs.size(); index++){
                if(songs.get(index).getLength() > length) {
                    songList += index + ": " + songs.get(index);
                }
            }
            if(songList.isEmpty()){
                return "There are no songs on this playlist longer than :" + length + "secs";
            }
            return songList;
        }
        return "No songs in playlist.";
    }

    public String listOfSongsOfArtist(String artistName) {
        if(!songs.isEmpty()){
            String songList = "";
            for(int index = 0; index < songs.size(); index++){
                if(songs.get(index).getArtist().getArtistName().equals(artistName) && songs.get(index).getArtist().isVerified()) {
                    songList += index + ": " + songs.get(index);
                }//verified the artist
            }//iterate the songs Arraylist
            if(songList.isEmpty()){
                return "There are no  songs on this playlist by " + artistName;
            }
            return songList;
        }
        return "No songs in playlist.";
    }

    //------------------------------
    //  FINDING METHODS
    //-------------------------------

    public Song findSongIndexPublic(int index) {
        if (isValidIndex(index)) {
            return songs.get(index);
        }
        return null;
    }

    public Song findSongByCode(int code) {
        if(!songs.isEmpty()){
            for(Song song : songs) {
                if(song.getSongId() == code){
                    return song;
                }
            }
            return null;
        }
        return null;
    }//What is code?

    //------------------------------
    //  SEARCHING METHODS
    //-------------------------------

    public String searchSongByName(String name) {
        if(!songs.isEmpty()){
            String song = "";
            for(int index = 0; index < songs.size(); index++){
                if(songs.get(index).getName().equals(name)) {
                    song += index + ": " + songs.get(index);
                }
            }
            if(song.isEmpty()){
                return "No songs found";
            }
        }
        return "No songs";
    }

    public String searchSongsByArtistName(String artistName) {
        if(!songs.isEmpty()){
            String song = "";
            for(int index = 0; index < songs.size(); index++){
                if(songs.get(index).getArtist().getArtistName().contains(artistName)) {
                    song += songs.get(index).getName() + "(" + songs.get(index).getSongId() + ")" + "\n";//what is code?
                }
            }
            if(song.isEmpty()){
                return "No songs found for this artist";
            }
        }
        return "No songs";
    }

    //-------------------------
    // HELPER METHODS
    //-------------------------

    public boolean isValidIndexPublic(int index) {
        return (index < songs.size() && index >= 0);
    }

    public String toString() {
        if(!songs.isEmpty()){
            return "Playlist Name: " + playlistName + "\n" +
                   "Playlist Description: " + description + "\n" +
                   "Songs: \n" + songs;
        }
        return "No songs in playlist.";
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver()); //1
        ObjectOutputStream out =
                xstream.createObjectOutputStream(new FileWriter("Playlist.xml")); //2
        out.writeObject(playlistName);
        out.writeObject(description);
        out.writeObject(likes);
        out.writeObject(songs); //3
        out.close(); //4
    }//Dry code from Lab5.1

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        Class<?>[] classes = new Class[] { Song.class }; //1
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream); //2
        xstream.allowTypes(classes);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Playlist.xml")); //3
        playlistName = (String) is.readObject();
        description = (String) is.readObject();
        likes = (int) is.readObject();
        songs = (ArrayList<Song>) is.readObject(); //4
        is.close(); //5
    }//Dry code from Lab5.1
}

