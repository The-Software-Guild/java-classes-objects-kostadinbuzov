import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        DVDLibrary.readFromFile();
        System.out.println("Welcome to your DVD Library!");
        while(true){
            System.out.println( "\nMenu:\n" +
                                "1. Add a DVD to the collection\n" +
                                "2. Remove a DVD from the collection\n" +
                                "3. Edit existing DVD\n" +
                                "4. Display all DVDs in the collection\n" +
                                "5. Display a particular DVD by number\n" +
                                "6. Display a particular DVD by Title\n" +
                                "7. Exit the program\n"
                                );
            Scanner in = new Scanner(System.in);
            int userInput = 0;
            try {
                userInput = Integer.parseInt(in.nextLine());
            }catch (NumberFormatException ignored){}


            switch (userInput){

                case 1:
                    String[] dvdToAdd = dvdElements();
                    DVDLibrary.addDVD(dvdToAdd[0],dvdToAdd[1],Double.parseDouble(dvdToAdd[2]),dvdToAdd[3],dvdToAdd[4],dvdToAdd[5]);
                    break;

                case 2:
                    System.out.println("Choose the number of the DVD you want to remove: ");
                    int dvdToRemove = in.nextInt();
                    try {
                        DVDLibrary.removeDVD(dvdToRemove-1);
                    }catch (IndexOutOfBoundsException ex){
                        System.out.println("There are " + DVDLibrary.getSize() + " DVDs in the collection.");
                    }
                    break;

                case 3:
                    System.out.println("Which DVD do you want to edit? Please enter its number: ");
                    int dvdToEdit = in.nextInt();
                    String [] dvdToChange = dvdElements();
                    try {
                        DVDLibrary.editDVD(dvdToEdit-1, dvdToChange[0], dvdToChange[1], Double.parseDouble(dvdToChange[2]), dvdToChange[3], dvdToChange[4], dvdToChange[5]);
                    }catch (IndexOutOfBoundsException ex){
                        System.out.println("There are " + DVDLibrary.getSize() + " DVDs in the collection.");
                    }
                    break;

                case 4:
                    System.out.println("Current DVDs in the collection:\n");
                    for(int i = 0; i <DVDLibrary.getSize();i++){
                        System.out.println(DVDLibrary.dvdToString(DVDLibrary.getDVD(i)));
                    }
                    break;

                case 5:
                    System.out.println("Choose the number of the DVD you want to display: ");
                    try {
                        int dvdNumber = in.nextInt();
                        DVD dvdByNumber = DVDLibrary.getDVD(dvdNumber-1);
                        System.out.println("Your dvd info:\n" + DVDLibrary.dvdToString(dvdByNumber));
                    }catch (IndexOutOfBoundsException ex){
                        System.out.println("There are " + DVDLibrary.getSize() + " DVDs in the collection.");
                    }

                    break;

                case 6:
                    System.out.println("Choose the Title of the DVD you want to display: ");
                    String title = in.nextLine();
                    int dvdNum = 0;
                    for(int i = 0; i<DVDLibrary.getSize();i++){
                        DVD parseDVD = DVDLibrary.getDVD(i);
                        if(parseDVD.getTitle().equals(title)){
                            dvdNum = i;
                            System.out.println(DVDLibrary.dvdToString(parseDVD));
                            break;
                        }
                    }
                    if(dvdNum == 0){
                        System.out.println("The DVD doesn't exist in the collection");
                    }


                    break;
                case 7:
                    DVDLibrary.saveToFile();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input a valid number between 1 and 7!");
                    break;

            }
        }

    }
    public static String[] dvdElements(){
        Scanner element = new Scanner(System.in);
        String[] dvdAttributes = new String[6];
        System.out.println("Enter Title: ");
        dvdAttributes[0] = element.nextLine();
        System.out.println("Enter Release date(yyyy-mm-dd): ");
        dvdAttributes[1] = element.nextLine();
        System.out.println("Enter MPAA rating: ");
        dvdAttributes[2] = element.nextLine();
        System.out.println("Enter Director's name: ");
        dvdAttributes[3] = element.nextLine();
        System.out.println("Enter Studio: ");
        dvdAttributes[4] = element.nextLine();
        System.out.println("Enter rating or any comments you might have: ");
        dvdAttributes[5] = element.nextLine();
        return dvdAttributes;
    }
}
