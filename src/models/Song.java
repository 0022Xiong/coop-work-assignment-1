package models;

import java.util.Objects;

public class Song {
      Artist artist;
    //TODO The song id (int songId) is between 1000 to 9999(both inclusive).  Default is 9999.
    private int songId = 9999;
    private int seconds = 1;
    //TODO The song name (String name).
    //     Default value is "".
    //     When creating the song, truncate the name to 20 characters.
    //     When updating an existing song, only update the name if it is 20 characters or less.
    private String name = "";

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        if(songId>9999 || songId<1000){
            System.out.println("The song id should be between 1000 to 9999");
        }
        else{
            this.songId=songId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length()>20){
            this.name = name.substring(0,20);
        }
        else{
            this.name=name;
        }
    }
    //TODO The song's artist (Artist artist)
    //    You should have already written the Artist class
    //     When creating the song, you should have the artist object as a parameter
    public Song(int songId,String name,String artistName, boolean verified){
        artist.setArtistName(artistName);
        artist.setVerified(verified);
        setSongId(songId);
        setName(name);
    }

    //TODO The length of the song in seconds (int length) is between 1 and 600. Default is 1.

    public void setSeconds(int seconds) {
       if(seconds>600 || seconds<1){
           System.out.println("The length of the song in seconds (int length) is between 1 and 600.");
       }
       else{
           this.seconds=seconds;
       }
    }

    public int getSeconds() {
        return seconds;
    }

    //TODO Add the constructor, Song(int, String, Artist), that adheres to the above validation rules


    //TODO Add a getter and setter for each field, that adheres to the above validation rules


    //TODO Add a generated equals method.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songId == song.songId && seconds == song.seconds && Objects.equals(artist, song.artist) && Objects.equals(name, song.name);
    }
    //TODO The toString should return the string containing each of the field values including the use of the artist's toString()
    public String toString(){
        return "songId is" + songId + "\n"+
                "name is" + name +"\n"+
                artist + "\n" +
                "the time of song is" + seconds;
    }
}