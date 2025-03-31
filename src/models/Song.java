package models;

import java.util.Objects;

public class Song {

    Artist artist;

    //TO-DO The song id (int songId) is between 1000 and 9999(both inclusive).  Default is 9999. #

    private int songId = 9999;
    private int length = 1;

    //TO-DO The song name (String name).
    //     Default value is "". #
    //     When creating the song, truncate the name to 20 characters. #
    //     When updating an existing song, only update the name if it is 20 characters or fewer. #

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
        if(this.name.isEmpty()){
            if(name.length()>20){
                this.name = name.substring(0,20);
            }
            else{
                this.name=name;
            }
        }//create
        else {
            if(name.length() <= 20) {
                this.name = name;
            }
        }//update
    }

    //TODO The song's artist (Artist artist)
    //    You should have already written the Artist class #
    //     When creating the song, you should have the artist object as a parameter

    //TO-DO The length of the song in seconds (int length) is between 1 and 600. Default is 1. #

    public void setLength(int seconds) {
       if(seconds>600 || seconds<1){
           System.out.println("The length of the song in seconds should be between 1 and 600.");
       }
       else{
           this.length=seconds;
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

    //TO-DO Add the constructor, Song(int, String, Artist), that adheres to the above validation rules #

    public Song(int songId, String name, String artistInput, boolean verified, int length){
        setSongId(songId);
        setName(name);
        Artist artistIn = new Artist(artistInput, verified);
        setArtist(artistIn);
        setLength(length);
    }

    //TO-DO Add a getter and setter for each field, that adheres to the above validation rules #

    //TO-DO Add a generated equals method. #

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songId == song.songId && length == song.length && Objects.equals(artist, song.artist) && Objects.equals(name, song.name);
    }

    //TO-DO The toString should return the string containing each of the field values including the use of the artist's toString() #

    public String toString(){
        return "songId is" + songId + "\n"+
                "name is" + name +"\n"+
                artist + "\n" +
                "the time of song is" + length + "\n";
    }

}