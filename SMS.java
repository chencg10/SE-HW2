package ex2_Phone;

import java.lang.String;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import static ex2_Phone.Phone.getInt;
import static ex2_Phone.PhoneBook.checkInput;


// ------------------------ Team 8 ------------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------


public class SMS {

    //define members
    private PhoneBook _phoneBook;
    private Map<String, ArrayList<String>> _messages;

    // Constructor
    public SMS(PhoneBook phoneBook) {
        // update the phone book
        this._phoneBook = phoneBook;
        // create a new map
        this._messages = new HashMap<String, ArrayList<String>>();
    }

    // Copy Constructor
    public SMS(SMS other) {
        // copy the phone book
        this._phoneBook = other.getPhoneBook();
        // copy the messages
        this._messages = other.getMessagesMap();
    }

    //Geter's:
    public Map<String, ArrayList<String>> getMessagesMap() {
        return this._messages;
    }

    public PhoneBook getPhoneBook() {
        return this._phoneBook;
    }

    //Seter's:
    public void setPhoneBook(PhoneBook phoneBook)
    {
        this._phoneBook = phoneBook;
    }

    public void setMap(Map<String, ArrayList<String>> messages)
    {
        this._messages = messages;
    }


    //methods:
    public void addTexting(String contact_name, String message)
    {
        //check if the contact exist in the phone book
        if(this.getPhoneBook().isNameExists(contact_name))
        {
            //Need to divide into 2 cases:
            //1. there is a history of texting with this contact
            //2. there is no history of texting with this contact

            //check if there is a history of texting with this contact
            if (this.getMessagesMap().containsKey(contact_name))
            {
                //add the new message to the history
                this.getMessagesMap().get(contact_name).add(message);
            }
            //if there is no history:
            else
            {
                //create new messaging history
                ArrayList<String> new_history = new ArrayList<String>();
                //add the new message to the history
                new_history.add(message);
                //add the new history to the map
                this.getMessagesMap().put(contact_name, new_history);
            }
        }
        else
        {
            //print error message to the user
            System.out.println("ERROR: NO CONTACT WITH THIS NAME IN THE PHONE BOOK!");
        }
    }

    public void deleteMessageHistory(String contact_name)
    {
        //check if the contact exist in the phone book
        if (this.getPhoneBook().isNameExists(contact_name))
        {
            //check if there is a message history to delete
            if (this.getMessagesMap().containsKey(contact_name))
            {
                //delete the message history
                this.getMessagesMap().remove(contact_name);
            }
            else
            {
                //print error message to the user
                System.out.println("ERROR: NO MESSAGE HISTORY TO DELETE");
            }
        }
        //if there is no contact with this name in the phone book
        else
        {
            //print error message to the user
            System.out.println("ERROR: NO CONTACT WITH THIS NAME IN THE PHONE BOOK!");
        }
    }

    public String toString(String contactName)
    {
        if (this.getPhoneBook().isNameExists(contactName))
        {
            //check if there is a message history to print
            if (this.getMessagesMap().containsKey(contactName))
            {
                //print the message history
                return this.getMessagesMap().get(contactName).toString();
            }
            else
            {
                //print error message to the user
                return "ERROR: NO MESSAGE HISTORY TO PRINT";
            }
        }
        //if there is no contact with this name in the phone book
        else
        {
            //print error message to the user
            return "ERROR: NO CONTACT WITH THIS NAME IN THE PHONE BOOK!";
        }
    }

    public void searchMessage(String message)
    {
        //check if the messages map is empty
        if (this.getMessagesMap().isEmpty())
        {
            //print error message to the user
            System.out.println("MESSAGE HISTORY IS EMPTY, NO CONTACTS TO PRESENT");
        }
        //search for a given message in the message history of all contacts
        //create a new array list to save the contacts that have the given message in their message history
        ArrayList<String> contacts_names = new ArrayList<String>();
        //iterate over the map of the message history
        for (Map.Entry<String, ArrayList<String>> entry : this.getMessagesMap().entrySet())
        {
            //iterate over the message history of each contact
            for (String message_in_history : entry.getValue())
            {
                //check if the message is in the message history
                if (message_in_history.equals(message))
                {
                    //add the contact to the array list
                    contacts_names.add(entry.getKey());
                }
            }
        }
        //print the names of all contacts that have the given message in their message history
        System.out.println("Those contacts contain the message: \""+ message + "\" in there message history:");
        System.out.println(contacts_names);
    }

    @Override
    public String toString()
    {
        //check if the message map is empty:
        if (this.getMessagesMap().isEmpty())
        {
            //print error message to the user
            return "MESSAGE HISTORY IS EMPTY, NOTHING TO PRINT";
        }
            //iterate over the map of the message history and print all the messages
            //define an iterator for the map
            Iterator<Map.Entry<String, ArrayList<String>>> iterator = this.getMessagesMap().entrySet().iterator();
            //create a string to save the message history
            String message_history = "";
            //iterate over the map
            while (iterator.hasNext())
            {
                //get the next entry in the map
                String current_contact = iterator.next().getKey();
                message_history += "Message history of: " + current_contact + "\n";
                message_history += this.toString(current_contact) + "\n";
                message_history += "--------------------------------------------------\n";
            }
            return message_history;
    }


    public void smsMenu() {
        while (true) {
            //this is the SMS menu
            System.out.println("~~~~~~~SMS System~~~~~~~");
            System.out.println("Hello User! Please enter your choice:");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Add texting with a contact");
            System.out.println("2. Print all messages");
            System.out.println("3. Print messages history with a contact");
            System.out.println("4. Search message");
            System.out.println("5. Delete message history");
            System.out.println("6. Back to main menu");
            //get the user's choice
            int choice = getInt(1, 6);
            //check if user wants to exit:
            if (choice == 6) {
                break;
            }
            else
            {
                //check the user's choice
                switch (choice) {
                    case 1:
                        //get the contact name
                        System.out.println("Please enter the contact name:");
                        String contactName1 = scanner.nextLine();
                        //check if the name is valid
                        contactName1 = checkInput(contactName1, "[a-zA-Z]+", scanner);
                        //get the message
                        System.out.println("Please enter the message:");
                        String message1 = scanner.nextLine();
                        this.addTexting(contactName1, message1);
                        break;
                    case 2:
                        //call the print all messages
                        System.out.println(this);
                        break;
                    case 3:
                        //call the print message history
                        System.out.println("Please enter the contact name:");
                        String contactName3 = scanner.nextLine();
                        //check if the name is valid
                        contactName3 = checkInput(contactName3, "[a-zA-Z]+", scanner);
                        //this.printMessageHistory(contactName3);
                        System.out.println(this.toString(contactName3));
                        break;
                    case 4:
                        //call the search message
                        System.out.println("Please enter the message you want to search:");
                        String message2 = scanner.nextLine();
                        this.searchMessage(message2);
                        break;
                    case 5:
                        //call the delete message history
                        System.out.println("Please enter the contact name that you want to delete history with: ");
                        String contactName5 = scanner.nextLine();
                        //check if the name is valid
                        contactName5 = checkInput(contactName5, "[a-zA-Z]+", scanner);
                        this.deleteMessageHistory(contactName5);
                        break;
                }
            }
        }
    }
}
