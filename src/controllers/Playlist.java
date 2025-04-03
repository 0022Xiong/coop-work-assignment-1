package controllers;

import models.Artist;
import models.Song;

import java.util.ArrayList;
public class Playlist {

    private String playlistName = ""; // valid length is 20 - default to the first 20 characters of input.
//    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Song> songs; // should start empty
    private String description = ""; // valid length is 30 - default to the first 30 characters of input.
    private int likes = 0;

    //TO-DO Declare an array list of songs(songs). This should be empty at the start and does not need to be the constructor. #

    //TO-DO playlist name (String playlistName) of the playlist in the system in the system is entered by the user. #
    //     Default value is "". #
    //     When creating the playlist, truncate the name to 20 characters. #
    //     When updating an existing playlist, only update the name if it is 20 characters or fewer. #

    public String getPlaylistName() {
        return playlistName;
    }
    public void setPlaylistName(String playlistName) {
//        if(this.playlistName.isEmpty()) {
//            if(playlistName.length() > 20) {
//                this.playlistName = playlistName.substring(0,20);
//            }//truncate the playlistName into 20
//            else {
//                this.playlistName = playlistName;
//            }
//        }//detect the playlist is creating...
//        else {
//            if(playlistName.length() <= 20){
//                this.playlistName = playlistName;
//            }
//        }//...or updating
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

//TO-DO The playlist description (String description) of the playlist in the system is entered by the user. #
    //     Default value is "". #
    //     When creating the playlist, truncate the description to 30 characters. #
    //     When updating an existing playlist, only update the description if it is 30 characters or fewer. #

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
//        if(this.description.isEmpty()){
//            if(description.length() > 30) {
//                this.description = description.substring(0,30);
//            }//truncate
//            else {
//                this.description = description;
//            }
//        }//creating
//        else {
//            if(description.length() <= 30){
//                this.description = description;
//            }
//        }//updating
        if(description.length() <= 30) {
            this.description = description;
        }//create and update
        else if(this.description.isEmpty()) {
            this.description = description.substring(0,30);
        }//create for description's length larger than 30
    }

    //TO-DO The number of likes a playlist has (int likes) #
    //    This should start at 0 and not be part of the constructor #

    //TO-DO Add the constructor, Playlist(String, String), that adheres to the above validation rules. #
    //     The order of the fields in the parameter list is the same as the order of fields above i.e. playlistName is
    //     first, then description. #

    public Playlist(String playlistName, String description) {
        songs = new ArrayList<>();
        setPlaylistName(playlistName);
        setDescription(description);
    }

//    public Playlist() {
//         songs = new ArrayList<>();
//    }

    //TO-DO Add a getter and setter for each field, that adheres to the above validation rules #

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

    //TO-DO Add a method, addSong(Song). The return type is boolean.
    //     This method will add the song object, passed as a parameter to the arraylist of songs.
    //     If the add was successful, return true, otherwise, return false.

    public boolean addSong(Song song) {
//        if(songs.add(song)) {
//            return true;
//        }
//        return false;
        return songs.add(song);//An optimized way
    }

    //TO-DO Add a method, updateSong(int, Song).  The return type is boolean.
    //     This method takes in, as the first parameter, the index of the songs object that you want to update.
    //     If the index is invalid (i.e. there is no song object at that location), return false.
    //     The other parameter is a  Song object - that is being updated
    //     i.e. it holds the new values of  id, name, length, and artist.
    //     If the update was successful, then return true.

    public boolean updateSong(int index, Song song) {
        if(isValidIndex(index)) {
            songs.set(index, song);
            return true;
        }
        return false;
    }//no boolean return for .set

    //TO-DO Add a method, deleteSong(int).  The return type is Song.
    //     This method takes in the index of the song object that you want to delete.
    //     If the index is invalid (i.e. there is no song object at that location), return null.
    //     If the index is valid, remove the object at that index location.  Return the object you just deleted.

    public Song deleteSong(int index) {
        if(isValidIndex(index)) {
//            Song songDelete = songs.get(index);//get the deleted object
//            songs.remove(index);
//            return songDelete;
            return songs.remove(index);//An optimized way
        }
        return null;
    }

    //TO-DO  Add a method  addLike() (no parameter) with return type void.
    //      This method simply adds 1 to the likes variable.

    public void addLike() {
        likes++;
    }

    //-------------------------------------
    //  ARRAYLIST - Utility methods
    //-------------------------------------

    //TO-DO Add a method isValidIndex(int) which returns a boolean -
    //      - returns true if the index is valid for the songs arrayList (in range)
    //      - returns false otherwise
    //      As this method is used inside this class, it should be private

    private boolean isValidIndex(int index) {
        return (index < songs.size() && index >= 0);
    }

//    private boolean isValidIndex(int index) {
//        return (index <= songs.size() && index >= 0);
//    }

    //TO-DO  Add a method  findSong(int) which returns a Song object:
    //       - if the supplied index is valid, the Song object at that location is returned
    //       - if the supplied index is invalid, null is returned

    private Song findSongIndex(int index) {
        if(isValidIndex(index)) {
            return songs.get(index);
        }
        return null;
    }//add -Index to avoid the conflict

    //TO-DO  Add a method  findSong(String) which returns a Song object:
    //       - if the supplied string (songName) matches a song name in the songs list, the Song object that matches that name  is returned
    //       - if the supplied string (songName) does not match a song name in the songs list, null is returned
    //       NOTE - if that name appears more than once, it is sufficient to return the first occurrence.

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

    //TO-DO Add a method,updateVerifiedStatus(int , boolean ).  The return type is Song.
    //     This method takes in the index of the song object whose artist's verified status
    //     you want to update.
    //     If the index is invalid (i.e. there is no song object at that location), return null.
    //     If the index is valid, retrieve the object and:
    //      set the verified status to the parameter value

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

    //TO-DO Add a method, numberOfSongs().  The return type is int.
    //     This method returns the number of song objects currently stored in the array list.

    public int numberOfSongs() {
        return songs.size();
    }

    //TO-DO Add a method, numberOfShortSongs().  The return type is int.
    //     This method returns the number of song objects in the array list that have a length of <= 180.

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

    //TO-DO Add a method getTotalPlayListLength() which returns a integer value of
    //     the total time (in seconds) if the there are songs in the playlist
    //     -1 if playlist is empty.

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

    //TO-DO Add a method getAverageSongLength() which returns a integer value of
    //      the average length of songs on the playlist
    //     -1 if playlist is empty.

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

    //TO-DO Add a method, listAllSongs().  The return type is String.
    //     This method returns a list of the songs stored in the array list.
    //     Each song should be on a new line and should be preceded by the index number e.g.
    //        0: song 1 Details
    //        1: song 2 Details
    //    If there are no songs stored in the array list, return a string that contains "No songs in playlist.".

    public String listAllSongs() {
        if(!songs.isEmpty()) {
            String songInList = "";
            for(int index = 0; index < songs.size(); index++) {
                songInList += index + ": " + songs.get(index) + "\n";
            }
            return "Songs from playlist : " + getPlaylistName() + "\n" + songInList;
        }
        return "No songs in playlist.";
    }

    //TO-DO Add a method, listSongsFromVerifiedArtists().  The return type is String.
    //     This method returns a list of the songs stored in the array list whose song artist is verified.
    //     Each matching song should be on a new line and should be preceded by the index number e.g.
    //        0: song 1 Details
    //        3: song 4 Details
    //    If there are no such  songs stored in the array list, the return string should
    //    have "No songs in playlist".
    //    If there are songs in the playlist but none with a verified artist, the return string should
    //    have "There are no songs from verified artists on this playlist".

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

    //TO-DO Add a method, listSongsLongerThan(int).  The return type is String.
    //    This method returns a list of the songs that are equal or above the length supplied as a parameter.
    //     Each matching song should be on a new line and should be preceded by the index number e.g.
    //        1: song 2 Details
    //        4: song 5 Details
    //    If there are no songs stored in the array list, return a string that contains "No songs in playlist.".
    //    If there are songs in the playlist, but none with songs over (or equal to) this length, then
    //     "There are no songs on this playlist longer than   'length supplied' " should be returned.

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

    //TO-DO Add a method, listOfSongsOfArtist(String).  The return type is String.
    //    This method returns a list of all the Songs of an artist (whose name you have supplied as parameter)  across all the song objects
    //    stored in the array list.
    //    Each song should be on a new line and should contain the song name and code too e.g.
    //        1: song 2 Details
    //        4: song 5 Details
    //    If there are no songs stored in the array list, return a string that contains "No songs in playlist".
    //     If there are songs in the playlist, but none by verified artists, then
    //     "There are no  songs on this playlist by   'artist supplied' " should be returned.

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

    //TO-DO Add a method, findSong(int).  The return type is Song.
    //    This method returns the song stored at the index that was passed as a parameter.
    //    However, if the index is not valid, null is returned.

    public Song findSongIndexPublic(int index) {
        if (isValidIndex(index)) {
            return songs.get(index);
        }
        return null;
    }

    //TODO Add a method, findSongByCode(int).  The return type is Song.
    //    This method searches the array list for a song with a specific code (passed as a parameter).
    //    When a song is found for this code, it is returned back.
    //    If no song exists for that code, return null.
    // NOTE: the first song encountered is returned, even if more exist with that code.  For extra credit,
    //       you could add in validation to ensure that the code is unique when adding a Song.

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

    //TO-DO Add a method, searchSongsByName(String).  The return type is String.
    //    This method returns a list of the songs whose name contains the string passed as a parameter.
    //    Each matching song should be on a new line and should be preceded by the index number e.g.
    //        1: song 2 Details
    //        4: song 5 Details
    //    If there are no songs stored in the array list, return a string that contains "No songs".
    //    If there are no songs whose name contains the supplied string, the return string should
    //    have "No songs found".

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

    //TODO Add a method, searchSongsByArtistName(String).  The return type is String.
    //    This method returns a list of songs whose artist name contains the string passed
    //    as a parameter.
    //    Each song should be on a new line and should contain the song name and code e.g.
    //        Flowers (45343)
    //        Wrecking Ball (65434)
    //    If there are no songs stored in the array list, return a string that contains "No songs".
    //    If there are no songs whose name contains the supplied string, the return string should
    //    have "No songs found for this artist.
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

    //TO-DO Add a method, isValidIndex(int).  The return type is boolean. #
    //    This method returns true if the value passed as a parameter is a valid index in the arraylist. #
    //    However, if the index is not valid, false is returned. #

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

}

