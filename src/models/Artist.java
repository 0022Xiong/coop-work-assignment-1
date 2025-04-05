package models;

import java.util.Objects;

public class Artist {

    private String artistName = "";
    private boolean verified = false;

    public Artist(String artistName, boolean verified) {
        setArtistName(artistName);
        setVerified(verified);
    }

    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        if(artistName.length() <= 15) {
            this.artistName = artistName;
        }//create and update
        else if(this.artistName.isEmpty()) {
            this.artistName = artistName.substring(0,15);
        }//create for artistName's length larger than 15
    }

    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return isVerified() == artist.isVerified() && Objects.equals(getArtistName(), artist.getArtistName());
    }

    public String toString() {
        if(verified) {
            return artistName + " is a verified artist ";
        }
        else {
            return artistName + " is not a verified artist ";
        }
    }
}
