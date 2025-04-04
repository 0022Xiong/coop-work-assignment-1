import controllers.Playlist;
import models.Song;
import utils.ScannerInput;

import java.util.Scanner;

public class Driver {
    private Scanner input = new Scanner(System.in);
    //TODO Define an object of the Playlist here.  It should be declared private.
     private Playlist playlist;
     private Song song;
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.runMenu();
    }

    //TODO Refer to the tutors instructions for building this class and for the menu.  You are free to deviate in any way
    //     from the Driver menu that is in the tutors instructions, once you have these included:
    //     (with tests still compiling)
    //       - CRUD on Playlist
    //       - Search facility (for Songs)
    //       - Reports
    //       - Persistence
    // Note:  This is the ONLY class that can talk to the user i.e. have System.out.print and Scanner reads in it.
    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------
private int mainMenu(){
        System.out.println("""
                 Song MENU 
                1) Add a Song
                2) List all Songs
                3) Update a Song
                4) Delete a Song
                5) Set verified status of a specific song's artist
                6) Find a specific Song (by code)
                7) Search for a specific Song (by name)
                
                8) Add a like to playlist
                ---------------------------------------------------------
                REPORT MENU
                ---------------------------------------------------------
                9) list all Songs by verified artists
                10)List all Songs over a given length
                11)List all Songs by a given artist
                12)Print the average length of songs in the playist
                13) Print the total length of songs in the playlist
                ---------------------------------------------------------
                SETTINGS MENU
                ---------------------------------------------------------
                20)Save
                21)Load
                0) Exit
                ---------------------------------------------------------
                ====>""");
int option = ScannerInput.readNextInt("Enter an option");
return option;
}
private void runMenu(){
        int option = mainMenu();
        while (option !=0){
            switch(option){
                case 1 -> Addsong();
                case 2 -> Showsongs();
                case 3 -> Updatesong();
                case 4 -> deletesong();
                case 5 -> Setverifiedstatus();
                case 6 -> fingSongbycode();
                case 7 -> searchSongbyname();
                case 8 -> addLikesToPlaylist();
                case 9 -> listAllSongByVerifiedArtists();
                case 10-> listAllSongOverAgivenLength();
                case 11-> listAllSongOverAgivenAritist();
                case 12 -> printAveragelengthOfSongs();
                case 13 -> printTotallengthOfSongs();
                case 20 -> savePlaylist();
                case 21 -> loadPlaylist();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid option entered: " + option);
            }
            System.out.println("\nPress enter key to continue...");
            option = mainMenu();
        }
}
    //------------------------------------
    // Private methods for CRUD on Song
    //------------------------------------
    private void createPlaylist() {
        if(playlist == null){
            String playlistName = ScannerInput.readNextLine("Enter the Name of Playlist: ");
            String description = ScannerInput.readNextLine("Enter the description: ");
            playlist = new Playlist(playlistName,description);
        }
    }

    private void Addsong(){
        createPlaylist();
        int songID = ScannerInput.readNextInt("Enter the songId:");
        String name = ScannerInput.readNextLine("Enter the name of song :");
        String artistInput = ScannerInput.readNextLine("Enter the artist's name:");
        char verify = ScannerInput.readNextChar("The artist is verified(y) or not(n): ");
        boolean verified = false;
        if((verify == 'y') || (verify == 'Y'))
            verified = true;
        int length = ScannerInput.readNextInt("Enter the length of song:");
        Song song = new Song(songID,name,artistInput,verified,length);
        playlist.addSong(song);
    }

    private void Showsongs(){System.out.println(playlist.listAllSongs());}

    private void Updatesong(){
    Showsongs();
    if(playlist.numberOfSongs() > 0) {
        int indexToUpdaTe = ScannerInput.readNextInt("Enter the index of the song to update :");
        int songID = ScannerInput.readNextInt("Enter the songId:");
        String name = ScannerInput.readNextLine("Enter the name of song :");
        String artistInput = ScannerInput.readNextLine("Enter the artist's name:");

        char vertify = ScannerInput.readNextChar("Enter the new vertify:");
        boolean vertified = false;
        if ((vertify == 'y') || (vertify == 'Y')) {
            vertified = true;
        }
        int length = ScannerInput.readNextInt("Enter the length of song");
        if (playlist.updateSong(indexToUpdaTe, new Song(songID, name, artistInput, vertified, length)))
        {System.out.println("Update Successful");}
    else{System.out.println("Update NOT Successful");}
    }
    else{System.out.println("There no songs for the index number");}
    }
    private void deletesong(){
        Showsongs();
        if(playlist.numberOfSongs()>0){
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the song to delete ==>");
            Song songToDelete = playlist.deleteSong(indexToDelete);
            if(songToDelete !=null){
                System.out.println("Delete Successful! Deleted product :"+songToDelete.getName());
            }
            else{
                System.out.println("Delte NOT Successful");
            }
        }
    }
    //-----------------------------------------------------------------
    //  Private methods for Search facility
    //-----------------------------------------------------------------
    private void Setverifiedstatus(){
        int indexToUpdate = ScannerInput.readNextInt("Enter index of the song to update:");
        char vertify = ScannerInput.readNextChar("Enter the new vertify:");
        boolean vertified = false;
        if((vertify == 'y') || (vertify == 'Y')){
            vertified = true;}
        playlist.updateVerifiedStatus(indexToUpdate,vertified);

    }
    private void fingSongbycode(){
        int codeToFind = ScannerInput.readNextInt("Enter code of the song to find:");
       Song song = playlist.findSongByCode(codeToFind);
       if(song != null){
           System.out.println("Song:" + song);
       }
       else{
           System.out.println("Song NOT found");
       }
    }
    private void searchSongbyname(){
        String nameToFind = ScannerInput.readNextLine("Enter the name of the song to find");
        String song = playlist.searchSongByName(nameToFind);
        if(song != null){
            System.out.println("Song:" + song);
        }
        else{
            System.out.println("Song NOT find");
        }
    }

    private void addLikesToPlaylist(){
     playlist.addLike();
     System.out.println("Add successful");
     System.out.println("Total likes =" + playlist.getLikes());
    }
    //-----------------------------
    //  Private methods for Reports
    // ----------------------------
    private void listAllSongByVerifiedArtists(){System.out.println(playlist.listSongsFromVerifiedArtists());}
    private void listAllSongOverAgivenLength(){
        int length = ScannerInput.readNextInt("Enter the length of song that you want to find:");
        System.out.println(playlist.listSongsLongerThan(length));
    }
    private void listAllSongOverAgivenAritist(){
    String aritist = ScannerInput.readNextLine("Enter the name of artist:");
    System.out.println(playlist.listOfSongsOfArtist(aritist));}
    private void printAveragelengthOfSongs(){System.out.println("The average length of song:" + playlist.getAverageSongLength());}
    private void printTotallengthOfSongs(){System.out.println("The total length of songs:" + playlist.getTotalPlayListLength());}
    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------
    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialize the playList object and their associated artists from
    //    an XML file into the Songs array list.

    private void loadPlaylist() {
        try {
            playlist.load();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the playList object and their associated artists to
    //    an XML file.

    private void savePlaylist() {
        try {
            playlist.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

}