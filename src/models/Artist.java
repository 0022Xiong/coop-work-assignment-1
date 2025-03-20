package models;

public class Artist {


    //TODO The artist name (String artistName)  in the system is entered by the user.
    //     Default value is "".#
    //     When creating the Artist, truncate the name to 15 characters.3
    //     When updating an existing Artist, only update the name if it is 15 characters or less.#
    private String artistName = "";

    //TODO The verified status (boolean verified)  Default is false.#
    private boolean verified = false;

    //TODO Add the constructor, Artist(String, boolean), that adheres to the above validation rules #
    public Artist(String artistName, boolean verified) {
        setArtistName(artistName);
        setVerified(verified);
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules #
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        if(artistName.length() > 15){
            this.artistName = artistName.substring(0,15);
        }
        else {
            this.artistName = artistName;
        }
    }

    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    //TODO Add a generated equals method.


    //TODO The toString should return the string in this format:
    //      Taylor Swift is a verified artist  OR
    //      Shane Hennessy is not a verified artist
    public String toString() {
        return artistName + " is a " + " artist ";
    }
}
