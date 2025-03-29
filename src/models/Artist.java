package models;

import java.util.Objects;

public class Artist {

    //TO-DO The artist name (String artistName)  in the system is entered by the user. #
    //     Default value is "". #
    //     When creating the Artist, truncate the name to 15 characters.3 #
    //     When updating an existing Artist, only update the name if it is 15 characters or fewer.#

    private String artistName = "";

    //TO-DO The verified status (boolean verified)  Default is false.#

    private boolean verified = false;

    //TO-DO Add the constructor, Artist(String, boolean), that adheres to the above validation rules #

    public Artist(String artistName, boolean verified) {
        setArtistName(artistName);
        setVerified(verified);
    }

    //TO-DO Add a getter and setter for each field, that adheres to the above validation rules #

    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        if(artistName.length() > 15){
            this.artistName = artistName.substring(0,15);
        }//To validation and cut the name
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

    //TO-DO Add a generated equals method. #

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return isVerified() == artist.isVerified() && Objects.equals(getArtistName(), artist.getArtistName());
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(getArtistName(), isVerified());
//    }//Is this so?

    //TO-DO The toString should return the string in this format: #
    //      Taylor Swift is a verified artist # OR
    //      Shane Hennessy is not a verified artist #

    public String toString() {
        if(verified) {
            return artistName + " is a verified artist ";
        }
        else {
            return artistName + " is not a verified artist ";
        }
    }
}
