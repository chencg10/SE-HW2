package ex2_Phone;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

import static ex2_Phone.Phone.getInt;



// ------------------------ Team 8 ------------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------


public class PhoneBook {
    // create an array list of nodes for the linked list
    private ArrayList<Contact> _contacts;
    private Contact _head;
    private Contact _tail;



    // Default Constructor
    public PhoneBook() {
        // initialize the array list
        this._contacts = new ArrayList<Contact>();
    }

    // constructor with a name and phone number
    public PhoneBook(String name, String phoneNumber)
    {
        this._contacts = new ArrayList<Contact>();
        this._contacts.add(new Contact(name, phoneNumber));
    }

    // constructor with a node
    public PhoneBook(Contact newContact)
    {
        this._contacts = new ArrayList<Contact>();
        this._contacts.add(new Contact(newContact));
    }
//############################################################################################################
    public PhoneBook(PhoneBook phoneBook) {
        // initialize the array list
        this._contacts = new ArrayList<Contact>();
        // copy the nodes
        for (Contact contact : phoneBook._contacts)
        {
            this._contacts.add(new Contact(contact));
        }
    }
//############################################################################################################

    // add a node to the linked list
    public void addNode(Contact newContact)
    {
        this._contacts.add(new Contact(newContact));
    }

    public void toString(String name) {
        for (Contact contact : this._contacts) {
            if (contact.getName().equals(name)) {
                System.out.println(contact.toString());
                return;
            }
        }
        System.out.println("Contact not found");
    }

    @Override
    public String toString() {
        //check if the list is empty
        if (this._contacts.size() == 0)
            return "PHONE BOOK IS EMPTY, NOTHING TO PRINT";

        String str = "";
        int counter = 1;
        for (Contact contact : this._contacts)
        {
            str += "This node is the:  " + counter + "th contact\n" +
                    contact.toString() + "\n----------\n";
            counter++;
        }
        return str;
    }

    // get a node from the linked list given an index
    public Contact getNode(int index) {return this._contacts.get(index);}

    public int size() {return this._contacts.size();}

    public void setHead(Contact head) {this._head = head;}

    public void setTail(Contact tail) {this._tail = tail;}

    public Contact getHead() {return this._head;}

    public Contact getTail() {return this._tail;}

    //Delete contact:
    public void deleteContact(String nameDelNode) {
        int sizeList= this._contacts.size();

        //if the list is empty
        if (sizeList == 1 && (this._contacts.get(0).getName()).equals(nameDelNode) ) {
            this._contacts = new ArrayList<Contact>();
            return;
        }
        // if the list is not empty
        for( int i=0; i<sizeList; i++) {

            if((this._contacts.get(i).getName()).equals(nameDelNode))
            {
                if(this._contacts.get(i).getPreviousNode() == null) { //If Head
                    // Update the head
                    this._head = this._contacts.get(i).getNextNode();
                    // now update the previous node of the next node
                    this._contacts.get(i).getNextNode().setPreviousNode(null); //now he is the head
                    // now update the next node of the current node
                    this._contacts.get(i).setNextNode(null);
                    // now remove the node in index i
                    this._contacts.remove(0);
                    // finished removing the node
                    break;
                }

                else if(this._contacts.get(i).getNextNode() == null)
                { //If tail
                    // Update the tail
                    this._tail = this._contacts.get(i).getPreviousNode();
                    // now update the next node of the previous node
                    this._contacts.get(i).getPreviousNode().setNextNode(null); //now he is the tail
                    // now update the previous node of the current node
                    this._contacts.get(i).setPreviousNode(null);
                    // now remove the node in index i
                    this._contacts.remove(sizeList -1);
                    // finished removing the node
                    break;

                }
                // if the node is between two nodes
                else
                {
                    this._contacts.get(i).getPreviousNode().
                            setNextNode(new Contact(this._contacts.get(i).getNextNode()));
                    this._contacts.get(i).getNextNode().
                            setPreviousNode(new Contact(this._contacts.get(i).getPreviousNode()));
                    this._contacts.remove(i);
                    // finished removing the node
                    break;
                }
            }
            // if the node is not in the list
            else if (i == sizeList -1)
            {
                System.out.println("The contact is not in the list, nothing to delete");
            }
        }
    }

    // Sort the linked list by phone number: Greatest to smallest
    public void sortByPhoneNum()
    {
        // We will sort the linked list using bubble sort

        //check if the list is empty:
        if ( this.getHead() == null)
        {
            System.out.println("The list is empty!, nothing to sort");
        }

        // check if the list has only one element:
        else if (this.size() == 1) {
            System.out.println("The list has only one element, nothing to sort");
        }
        else
        {
            //sort using lambda expression
            this._contacts.sort((Contact c1, Contact c2) -> c1.getPhoneNumber().compareTo(c2.getPhoneNumber()));
        }
    }

    public void deleteContact(int index)
    {
        // check if the index is valid
        if (index < 0 || index >= this.size())
        {
            System.out.println("Invalid index");
            return;
        }

        // check if the list is empty
        else if (this.getHead() == null)
        {
            System.out.println("The list is empty, nothing to delete");
            return;
        }

        // check if the list has only one element
        else if (this.size() == 1)
        {
            this._contacts = new ArrayList<Contact>();
            System.out.println("The list had only one element, now it is empty");
            return;
        }

        // check if the node is the head
        if (index == 0)
        {
            // update the head
            this.setHead(this._contacts.get(index).getNextNode());
            // now update the previous node of the next node
            this._contacts.get(index).getNextNode().setPreviousNode(null); //now he is the head
            // now update the next node of the current node
            this._contacts.get(index).setNextNode(null);
            // now remove the node in index i
            this._contacts.remove(index);
            // finished removing the node
            return;
        }

        // check if the node is the tail
        else if (index == this.size() - 1)
        {
            // update the tail
            this.setTail(this._contacts.get(index).getPreviousNode());
            // now update the next node of the previous node
            this._contacts.get(index).getPreviousNode().setNextNode(null); //now he is the tail
            // now update the previous node of the current node
            this._contacts.get(index).setPreviousNode(null);
            // now remove the node in index i
            this._contacts.remove(this.size() - 1);
            // finished removing the node
            return;
        }

        // if the node is between two nodes
        this._contacts.get(index).getPreviousNode().setNextNode(this._contacts.get(index).getNextNode());
        this._contacts.get(index).getNextNode().setPreviousNode(this._contacts.get(index).getPreviousNode());
        //update the next and prev of the node to be deleted
        this._contacts.get(index).setNextNode(null);
        this._contacts.get(index).setPreviousNode(null);
        // now remove the node in index i
        this._contacts.remove(index);
        // finished removing the node
    }

    public int getIndex(Contact other)
    {
        // return the node index in the linked list
        return this._contacts.indexOf(other);
    }

    // Sort the linked list by name:
    public void sortByName()
    {
        // We will sort the linked list using bubble sort

        //check if the list is empty:
        if ( this.getHead() == null)
        {
            System.out.println("The list is empty!, nothing to sort");
        }

        // check if the list has only one element:
        else if (this.size() == 1) {
            System.out.println("The list has only one element, nothing to sort");
        }
        else
        {
            //sort using lambda expression
            this._contacts.sort((Contact c1, Contact c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        }
    }

    //Q4:
    public ArrayList<Contact> searchContactByName(String name)
    {
        //searching all the contacts by the name
        //and return array of all contacts
        ArrayList<Contact> contact_contacts = new ArrayList<Contact>();
        int nodes_len = this._contacts.size();
        for(int i=0; i<nodes_len; i++)
        {
            //checking if the current node's name is name:
            String current_name = getNode(i).getName().toLowerCase();
            if(current_name.equals(name.toLowerCase()))
                contact_contacts.add(new Contact(getNode(i)));
        }
        //if there is no contact with the name:
        if(contact_contacts.size() == 0) {
            System.out.println("There is no contact with the name: " + name);
            System.out.println("Please try again...");
        }

        return contact_contacts;

    }

    //Q8:
    public void arrangePhoneBookReverseOrder()
    {
        ArrayList<Contact> temp_contacts = new ArrayList<Contact>();
        int nodes_len = this._contacts.size();
        for(int i = nodes_len - 1; i >= 0; i--)
        {
            temp_contacts.add(new Contact(getNode(i)));
        }
        this._contacts = new ArrayList<Contact>();
        for(int i=0; i < nodes_len; i++)
        {
            this._contacts.add(new Contact(temp_contacts.get(i)));
            //setting the next and prev nodes:
            // Special case for an array of size 1
            if (nodes_len == 1)
            {
                //setting the head:
                this.setHead(this._contacts.get(i));
                //set the prev of the head to null:
                this._contacts.get(i).setPreviousNode(null);
                //setting the tail:
                this.setTail(this._contacts.get(i));
                //set the next of the tail to null:
                this._contacts.get(i).setNextNode(null);
            }
            //general case:
            else
            {
                //if it is the first node:
                if(i == 0)
                {
                    //setting the head:
                    this.setHead(this._contacts.get(i));
                    //set the prev of the head to null:
                    this._contacts.get(i).setPreviousNode(null);
                }
                else if(i == nodes_len - 1)
                {
                    //setting the tail:
                    this.setTail(this._contacts.get(i));
                    this._contacts.get(i).setNextNode(null);
                    this._contacts.get(i).setPreviousNode(this._contacts.get(i-1));
                    //update the next of the previous node:
                    this._contacts.get(i-1).setNextNode(this._contacts.get(i));
                }
                else
                {
                    this._contacts.get(i).setPreviousNode(this._contacts.get(i-1));
                    //update the next of the previous node:
                    this._contacts.get(i-1).setNextNode(this._contacts.get(i));
                }
            }
        }
    }


    //Q10:
    public void getPhonebookFromFile(String fileName)
    {
        //initialing the prev node to the tail
        Contact prev = this._tail;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = reader.readLine(); //reading the first line of the title
            while ((line = reader.readLine()) != null)
            {
                String name = extractValue(line, "Name: ");
                String phone = extractValue(line, "Phone: ");
                Contact new_contact = new Contact(name,phone);
                if (this._head == null)
                {
                    this._head = new_contact; // Setting the head to the new node
                    this._tail = new_contact; // Setting the tail to the new node
                    this._contacts.add(new_contact); // Adding the new node to the list of nodes
                    new_contact.setPreviousNode(null); // Setting the previous node for the new node as null
                    new_contact.setNextNode(null); // Setting the next node for the new node as null
                    prev = new_contact; // Setting the previous node to the new node
                }
                else
                {
                    prev.setNextNode(new_contact);// Setting the next node for the previous node
                    this._contacts.add(new_contact);// Adding the new node to the list of nodes
                    new_contact.setPreviousNode(prev);// Setting the previous node for the new node
                    new_contact.setNextNode(null);// Setting the next node for the new node as null
                    prev = prev.getNextNode();// Advancing the previous node
                    this._tail = new_contact; // Updating the tail to the new node
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static String extractValue(String line, String key) {
        int startIndex = line.indexOf(key) + key.length(); // Find the starting index of the value
        int endIndex = line.indexOf(" ", startIndex); // Find the ending index of the value

        if (endIndex == -1) {
            endIndex = line.length(); // If the ending index is not found, set it to the length of the line
        }

        return line.substring(startIndex, endIndex); // Extract and return the value from the line
    }

    private void removeDuplicates() {

        //check if the list is empty:
        if ( this.getHead() == null)
        {
            System.out.println("The list is empty!, nothing to sort");
        }

        // check if the list has only one element:
        else if (this.size() == 1) {
            System.out.println("The list has only one element, nothing to sort");
        }
        else
        {
            // define an HashSet to store the nodes
            HashSet<String> uniqeValues = new HashSet<String>();
            //set the first node
            Contact current = this.getHead();
            // loop over the list and add the nodes to the HashSet
            while (current != null)
            {
                if (uniqeValues.contains(current.getPhoneNumber()))
                {
                    if (current.getNextNode() == null)
                    {
                        int index = this.getIndex(current);
                        this.deleteContact(index);
                        break;
                    }
                    // save the next node
                    Contact temp = current.getNextNode();
                    //get the node index
                    int index = this.getIndex(current);
                    // remove the node
                    this.deleteContact(index);
                    // update the next node
                    current = temp;
                }
                else
                {
                    uniqeValues.add(current.getPhoneNumber());
                    // update the next node
                    current = current.getNextNode();
                }
            }
        }
    }


    private void saveToFile(String fileName) {
//check if the list is empty:
        if ( this.getHead() == null)
        {
            // nothing to save
            System.out.println("The list is empty!, nothing to save");
        }
        else
        {
            try {
                FileWriter fr = new FileWriter(fileName + ".txt");
                BufferedWriter br = new BufferedWriter(fr);
                // set the first node
                Contact current = this.getHead();
                // loop over the list and add print the nodes to the file
                br.write("--------------------Phone Book--------------------\n");
                while (current != null)
                {
                    br.write("Name: " + current.getName() + " Phone: " + current.getPhoneNumber() + "\n");
                    // update the next node
                    current = current.getNextNode();
                }
                br.close();
                // notify the user
                System.out.println("The phone book was saved successfully");
            }
            // in case of an error
            catch (IOException e) {
                System.out.println("An error occurred while saving the phone book" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // This function is used to get the phone book from a file
    public boolean isNameExists(String name) {
        // check if the list is empty
        if (this.getHead() == null)
        {
            // the list is empty
            return false;
        }
        else
        {
            Iterator<Contact> iterator = this._contacts.iterator();
            // loop over the list
            while (iterator.hasNext())
            {
                // get the current node
                Contact current = iterator.next();
                // check if the name exists
                if (current.getName().equals(name))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static String checkInput(String input, String regex, Scanner scanner) {
        while (true)
        {
            if (!input.matches(regex)) {
                System.out.println("Invalid input, please try again");
                input = scanner.next();
            }
            else
                break;
        }
    return input;
    }

    public static void menu()
    {
        System.out.println("1. Add a new contact");
        System.out.println("2. Delete a contact by name");
        System.out.println("3. Print phone book");
        System.out.println("4. Search for a contact by name");
        System.out.println("5. Sort the phone book by name");
        System.out.println("6. Sort the phone book by phone number");
        System.out.println("7. Remove duplicates");
        System.out.println("8. Reverse the phone book");
        System.out.println("9. Save the phone book to a file");
        System.out.println("10. Load the phone book from a file and append to the current phone book");
        System.out.println("11. Exit");
    }

//______________________________________________________________________________________________________________________

    public void phoneBookMenu() {

        while (true)
        {
            int UserChoice;
            System.out.println("~~~~~~~~~Welcome to the phone book!~~~~~~~~~");
            menu();
            // get the user's choice
            Scanner input = new Scanner(System.in);

            System.out.println("Enter your choice: ");
            UserChoice = getInt(1, 11);

            // check if the user wants to exit
            if (UserChoice == 11)
            {
                break;
            }
            else
            {
                switch (UserChoice)
                {
                    // Add a new contact
                    case 1:

                        System.out.println("Enter the name: ");
                        String name = input.next();
                        name = checkInput(name, "[a-zA-Z]+", input);

                        // check if name exists in the phone book:
                        if (this.isNameExists(name))
                        {
                            System.out.println("THE NAME ALREADY EXISTS IN THE PHONE BOOK!");
                            break;
                        }

                        System.out.println("Enter the phone number: ");
                        //Phone number format message:
                        System.out.println("(Phone number format must be: 05XXXXXXXX)");
                        String phoneNumber = input.next();
                        phoneNumber = checkInput(phoneNumber, "[0-9]+", input);

                        //check if the phone number is in the right format:
                        while (phoneNumber.length() != 10 || !phoneNumber.startsWith("05"))
                        {
                            System.out.println("Invalid phone number, please try again");
                            phoneNumber = input.next();
                            phoneNumber = checkInput(phoneNumber, "[0-9]+", input);
                        }

                        // add the new node to the phone book
                        //Node newNode = new Node(name, phoneNumber);
                        this.addNode(new Contact(name, phoneNumber));

                        // check if this element is the first element
                        if (this.size() == 1) {

                            // update the head
                            this.setHead(this.getNode(0));
                            // update the tail
                            this.setTail(this.getNode(0));
                        }
                        else
                        {
                            //update the prev node of the new added node
                            this.getNode(this.size() - 1).setPreviousNode(this.getTail());

                            //update the next of the old tail
                            this.getTail().setNextNode(this.getNode(this.size() - 1));

                            //update the tail
                            this.setTail(this.getNode(this.size() - 1));
                        }
                        break;

                    case 2:
                        // delete a contact by name
                        System.out.println("Enter the name of the contact you want to delete: ");
                        String nameDel = input.next();
                        //check if the name is valid
                        nameDel = checkInput(nameDel, "[a-zA-Z]+", input);
                        //check if the name exists in the phone book
                        if (!this.isNameExists(nameDel))
                        {
                            System.out.println("The name doesn't exist in the phone book!");
                            break;
                        }
                        // if the name exists, delete it
                        else
                            this.deleteContact(nameDel);

                        break;

                    case 3:
                        // print the phone book
                        System.out.println(this);
                        break;

                    case 4:
                        System.out.println("Enter the name you want to search: ");
                        String name_4 = input.next();
                        //check if the name is valid
                        name_4 = checkInput(name_4, "[a-zA-Z]+", input);

                        ArrayList<Contact> contact_contacts = this.searchContactByName(name_4);
                        for (Contact contact : contact_contacts)
                        {
                            System.out.println(contact);
                            System.out.println("----------");
                        }
                        break;

                    case 5:
                        // sort the phone book by name
                        this.sortByName();
                        break;

                    case 6:
                        // sort the phone book by phone number
                        this.sortByPhoneNum();
                        break;

                    case 7:
                        // remove duplicates
                        this.removeDuplicates();
                        break;

                    case 8:
                        this.arrangePhoneBookReverseOrder();
                        break;

                    case 9:
                        // ask the user to enter the file name
                        System.out.println("Enter the file name (without .txt): ");
                        String fileName = input.next();
                        // save the phone book to a file
                        this.saveToFile(fileName);
                        break;

                    case 10:
                        System.out.println("Enter the file name including the end of '.txt': ");
                        String fileName1 = input.next();
                        // add to the phone book from a file
                        this.getPhonebookFromFile(fileName1);

                        break;
                }
            }
        }
    }
}