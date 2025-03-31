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
        new Driver();
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
            }
        }
}
    //------------------------------------
    // Private methods for CRUD on Song
    //------------------------------------
    private void Addsong(){
        int songID = ScannerInput.readNextInt("Enter the songId:");
        String name = ScannerInput.readNextLine("Enter the name of song :");
        String artistInput = ScannerInput.readNextLine("Enter the artist's name:");
        char vertify = input.next().charAt(0);
        boolean vertified = false;
        if((vertify == 'y') || (vertify == 'Y'))
            vertified = true;
        int length = ScannerInput.readNextInt("Enter the length of song:");

        Song song = new Song(songID,name,artistInput,vertified,length);

        playlist.addSong(new Song(songID,name,artistInput,vertified,length));
    }

    private void Showsongs(){
        System.out.println(playlist.listAllSongs());
    }

    private void Updatesong(){}
    //-----------------------------------------------------------------
    //  Private methods for Search facility
    //-----------------------------------------------------------------


    //-----------------------------
    //  Private methods for Reports
    // ----------------------------


    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------


    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialize the playList object and their associated artists from
    //    an XML file into the Songs array list.


    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the playList object and their associated artists to
    //    an XML file.
}