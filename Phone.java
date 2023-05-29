package ex2_Phone;
import java.util.Scanner;



// ------------------------ Team 8 ------------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------

public class Phone {
    //members:
    private SMS _smsApp;
    private PhoneBook _phoneBook;
    //need to add shoam's & hadas's app

    //constructor:
    public Phone() {
        this._phoneBook = new PhoneBook();
        this._smsApp = new SMS(this._phoneBook);
        //need to add shoam's & hadas's app
    }


    //build menu:
    public void mainSysMenu()
    {
        while (true) {
            //ask the user to choose an app
            System.out.println("~~~~~~~Phone System~~~~~~~");
            System.out.println("Hello User! Please enter your choice:");
            System.out.println("1. Main menu of phone");
            System.out.println("2. Print Apps content");
            System.out.println("3. Exit");
            //get the user's choice
            int choice = getInt(1, 3);
            //check if the user wants to exit
            if (choice == 3) {
                System.out.println("Bye Bye!");
                break;
            }
            else {
                //check the user's choice
                switch (choice) {
                    case 1:
                        //call the main menu
                        this.mainMenu();
                        break;
                    case 2:
                        //call the print apps content
                        this.printAppsContent();
                        break;
                    default:
                        //wrong input
                        System.out.println("Wrong input! Please try again");
                        this.mainSysMenu();
                        break;
                }
            }
        }
    }

    public static int getInt(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            while (choice < min || choice > max) {
                System.out.println("Wrong input! Please try again");
                choice = scanner.nextInt();
            }
            return choice;
        }
        catch (Exception e) {
            System.out.println("Your input is not a number! Please try again");
            return getInt(min, max);
        }
    }

    private void mainMenu() {
        while (true) {
            //present the 4 apps in our phone
            System.out.println("~~~~~~~Main Menu~~~~~~~");
            System.out.println("Please choose an app:");
            System.out.println("1. Phone Book");
            System.out.println("2. SMS");
            System.out.println("3. Calendar");
            System.out.println("4. Media");
            System.out.println("5. Back to main menu");
            //get the user's choice
            int choice = getInt(1, 5);
            //check if the user wants to exit
            if (choice == 5) {
                break;
            }
            else {
                //check the user's choice
                switch (choice) {
                    case 1:
                        //call the phone book
                        this._phoneBook.phoneBookMenu();
                        break;
                    case 2:
                        //call the SMS
                        this._smsApp.smsMenu();
                        break;
                    case 3:
                        //call the calendar
                        //this.calendarMenu();
                        break;
                    case 4:
                        //call the media
                        //this.mediaMenu();
                        break;
                }
            }
        }
    }


    private void printAppsContent() {
        //call all the printing methods of the apps
        System.out.println("~~~~~~~Apps Content~~~~~~~");
        System.out.println("Phone Book:");
        System.out.println(this._phoneBook);
        System.out.println("SMS:");
        System.out.println(this._smsApp);
        //need to add shoam's & hadas's app
    }


}
