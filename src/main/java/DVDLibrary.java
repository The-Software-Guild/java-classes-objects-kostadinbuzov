import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DVDLibrary {
    private static List<DVD> myCollection = new ArrayList<>();

    public static void addDVD(String title, String releaseDate, double MPAARating, String directorName, String studio, String userNote) {
        myCollection.add(new DVD(title, releaseDate, MPAARating, directorName, studio, userNote));

    }

    public static void removeDVD(int dvdNumber) {
        myCollection.remove(dvdNumber);
    }

    public static void editDVD(int dvdNumber, String title, String releaseDate, double MPAARating, String directorName, String studio, String userNote) {
        DVD dvd = myCollection.get(dvdNumber);
        dvd.setTitle(title);
        dvd.setReleaseDate(releaseDate);
        dvd.setMPAARating(MPAARating);
        dvd.setDirectorName(directorName);
        dvd.setStudio(studio);
        dvd.setUserNote(userNote);
    }

    public static DVD getDVD(int dvdNum) {
        return myCollection.get(dvdNum);
    }

    public static int getSize() {
        return myCollection.size();
    }

    public static void readFromFile() {
        try {
            Scanner fromFile = new Scanner(new BufferedReader(new FileReader("src/main/resources/DVDCollection.txt")));
            while (fromFile.hasNextLine()) {
                String[] dvdAttributes = fromFile.nextLine().split("::");
                myCollection.add(new DVD(dvdAttributes[0], dvdAttributes[1], Double.parseDouble(dvdAttributes[2]), dvdAttributes[3], dvdAttributes[4], dvdAttributes[5]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The file you want to read from doesn't exist");
        }
    }

    public static void saveToFile() {

        try {
            PrintWriter out = new PrintWriter(new FileWriter(("src/main/resources/DVDCollection.txt")));
            for (DVD dvd : myCollection) {

                out.println(dvd.toString());
            }
            out.flush();
            out.close();

        } catch (IOException ignored) {
        }

    }
}
