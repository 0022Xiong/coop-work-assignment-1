import controllers.Playlist;
import models.Song;
import utils.ScannerInput;

import java.util.concurrent.TimeUnit;//https://github.com/ddrohan/VehicleRepository/blob/main/VehicleAppWithArrayList/src/Driver.java line 2

public class Driver {

     private Playlist playlist;
     private Song song;
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.runMenu();
    }

    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------
    private int mainMenu(){
        int option = ScannerInput.readNextInt("""
                 Song MENU
                1) Add a Song
                2) List all Songs
                3) Update a Song
                4) Delete a Song
                5) Set verified status of a specific song's artist
                6) Find a specific Song (by songID)
                7) Search for a specific Song (by name)
                8) Add a like to playlist
                ---------------------------------------------------------
                REPORT MENU
                ---------------------------------------------------------
                9) list all Songs by verified artists
                10)List all Songs over a given length
                11)List all Songs by a given artist
                12)Print the average length of songs in the playlist
                13) Print the total length of songs in the playlist
                ---------------------------------------------------------
                SETTINGS MENU
                ---------------------------------------------------------
                19)Create
                20)Save
                21)Load
                0) Exit
                ---------------------------------------------------------
                ====>""");
        return option;
    }

    private void runMenu(){
        clearScreen();
        int option = mainMenu();
        while (option !=0){
            switch(option){
                case 1 -> addSong();
                case 2 -> showSongs();
                case 3 -> updateSong();
                case 4 -> deleteSong();
                case 5 -> setVerifiedStatus();
                case 6 -> findSongByCode();
                case 7 -> searchSongByName();
                case 8 -> addLikesToPlaylist();
                case 9 -> listAllSongByVerifiedArtists();
                case 10 -> listAllSongOverGivenLength();
                case 11 -> listAllSongOverGivenArtist();
                case 12 -> printAverageLengthOfSongs();
                case 13 -> printTotalLengthOfSongs();
                case 19 -> createPlaylist();
                case 20 -> savePlaylist();
                case 21 -> loadPlaylist();
                default -> System.out.println("Invalid option entered: " + option);
            }
            System.out.println("\nPress enter key to continue...");
            option = mainMenu();
        }
        exit();
    }
    //------------------------------------
    // Private methods for CRUD on Song
    //------------------------------------
    private void setPlaylist() {
        clearScreen();
        if(isNoPlaylist()){
            int option = ScannerInput.readNextInt("""
                    1)Create
                    2)Load
                    ===>""");
            if(option == 1) {
                createPlaylist();
            }
            else if(option == 2) {
                loadPlaylist();
            }
        }
    }

    private void createPlaylist() {
        String playlistName = ScannerInput.readNextLine("Enter the Name of Playlist: ");
        String description = ScannerInput.readNextLine("Enter the description: ");
        playlist = new Playlist(playlistName,description);
    }

    private boolean isNoPlaylist() {
        if(playlist == null){
            System.out.println("No Playlist currently. You need to create or load one. ");
            return true;
        }
        return false;
    }

    private void addSong(){
        setPlaylist();
        clearScreen();
        int songID = ScannerInput.readNextInt("Enter the songId:");
        String name = ScannerInput.readNextLine("Enter the name of song :");
        String artistInput = ScannerInput.readNextLine("Enter the artist's name:");
        char verify = ScannerInput.readNextChar("The artist is verified(y) or not(n): ");
        boolean verified = false;
        if((verify == 'y') || (verify == 'Y'))
            verified = true;
        int length = ScannerInput.readNextInt("Enter the length of song:");
        Song song = new Song(songID,name,artistInput,verified,length);
        if(playlist.addSong(song)){
            System.out.println("Song add successfully. ");
        }
    }

    private void showSongs(){
        setPlaylist();
        clearScreen();
        System.out.println(playlist.listAllSongs());
    }

    private void updateSong(){
        setPlaylist();
        showSongs();
        if(playlist.numberOfSongs() > 0) {
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the song to update :");
            int songID = ScannerInput.readNextInt("Enter the songId:");
            String name = ScannerInput.readNextLine("Enter the name of song :");
            String artistInput = ScannerInput.readNextLine("Enter the artist's name:");

            char verify = ScannerInput.readNextChar("Enter the new verify:");
            boolean verified = false;
            if ((verify == 'y') || (verify == 'Y')) {
                verified = true;
            }
            int length = ScannerInput.readNextInt("Enter the length of song");
            if (playlist.updateSong(indexToUpdate, new Song(songID, name, artistInput, verified, length))) {
                System.out.println("Update Successful");
            }
            else {
                System.out.println("Update NOT Successful");
            }
        }
        else{
            System.out.println("There no songs for the index number");
        }
    }

    private void deleteSong(){
        setPlaylist();
        showSongs();
        if(playlist.numberOfSongs()>0){
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the song to delete ==>");
            Song songToDelete = playlist.deleteSong(indexToDelete);
            if(songToDelete !=null){
                System.out.println("Delete Successful! Deleted product :"+songToDelete.getName());
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }
    //-----------------------------------------------------------------
    //  Private methods for Search facility
    //-----------------------------------------------------------------
    private void setVerifiedStatus(){
        setPlaylist();
        clearScreen();
        int indexToUpdate = ScannerInput.readNextInt("Enter index of the song to update:");
        char verify = ScannerInput.readNextChar("Enter the new verify:");
        boolean verified = false;
        if((verify == 'y') || (verify == 'Y')){
            verified = true;}
        playlist.updateVerifiedStatus(indexToUpdate,verified);

    }
    private void findSongByCode(){
        setPlaylist();
        clearScreen();
        int codeToFind = ScannerInput.readNextInt("Enter code of the song to find:");
        Song song = playlist.findSongByCode(codeToFind);
        if(song != null){
           System.out.println("Song:" + song);
        }
        else{
           System.out.println("Song NOT found");
        }
    }

    private void searchSongByName(){
        setPlaylist();
        clearScreen();
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
        setPlaylist();
        clearScreen();
        playlist.addLike();
        System.out.println("Add successful");
        System.out.println("Total likes =" + playlist.getLikes());
    }
    //-----------------------------
    //  Private methods for Reports
    // ----------------------------
    private void listAllSongByVerifiedArtists(){
        setPlaylist();
        clearScreen();
        System.out.println(playlist.listSongsFromVerifiedArtists());
    }

    private void listAllSongOverGivenLength(){
        setPlaylist();
        clearScreen();
        int length = ScannerInput.readNextInt("Enter the length of song that you want to find:");
        System.out.println(playlist.listSongsLongerThan(length));
    }

    private void listAllSongOverGivenArtist(){
        setPlaylist();
        clearScreen();
        String artist = ScannerInput.readNextLine("Enter the name of artist:");
        System.out.println(playlist.listOfSongsOfArtist(artist));
    }

    private void printAverageLengthOfSongs(){
        setPlaylist();
        System.out.println("The average length of song:" + playlist.getAverageSongLength());
    }

    private void printTotalLengthOfSongs(){
        setPlaylist();
        System.out.println("The total length of songs:" + playlist.getTotalPlayListLength());
    }

    //---------------------------------
    //  Private methods for Persistence
    // --------------------------------

    private void loadPlaylist() {
        playlist = new Playlist("","");//avoid NullPointer
        try {
            playlist.load();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }//Dry code from Lab5.1

    private void savePlaylist() {
        try {
            playlist.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }//Dry code from Lab5.1

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }//https://github.com/ddrohan/VehicleRepository/blob/main/VehicleAppWithArrayList/src/Driver.java line 176

    private void exit() {
        clearScreen();
        System.out.println("Exiting...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {}
    }//https://github.com/ddrohan/VehicleRepository/blob/main/VehicleAppWithArrayList/src/Driver.java line 125

}