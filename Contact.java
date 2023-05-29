package ex2_Phone;
// For using strings in Java
import java.lang.String;


// ------------------------ Team 8 ------------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------

public class Contact {
    private String _name;
    private String _phoneNumber;
    private Contact _nextContact;
    private Contact _previousContact;

    // Constructor
    public Contact(String name, String phoneNumber) {
        // update the name and phone number
        this._name = name;
        this._phoneNumber = phoneNumber;
        // set the next node to null
        this._nextContact = null;
        // set the previous node to null
        this._previousContact = null;
    }

    // Constructor
    public Contact(String name, String phoneNumber, Contact nextContact, Contact previousContact) {
        // update the name and phone number
        this._name = name;
        this._phoneNumber = phoneNumber;
        // set the next node to null
        this._nextContact = nextContact;
        // set the previous node to null
        this._previousContact = previousContact;
    }

    // Copy Constructor
    public Contact(Contact other) {
        // copy the name and phone number
        this._name = other.getName();
        this._phoneNumber = other.getPhoneNumber();
        // copy also the next node and previous node
        this._nextContact = other.getNextNode();
        this._previousContact = other.getPreviousNode();
    }

    @Override
    public String toString() {
        // Return the name and phone number of the current node
        return "Name: " + this._name + "\nPhone Number: " + this._phoneNumber;
    }

    // Getters and Setters
    public String getName() {return this._name;}

    public void setName(String name) {this._name = name;}

    public String getPhoneNumber() {return this._phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this._phoneNumber = phoneNumber;}

    public Contact getNextNode() {return this._nextContact;}

    public void setNextNode(Contact nextContact) {this._nextContact = nextContact;}

    public Contact getPreviousNode() {return this._previousContact;}

    public void setPreviousNode(Contact previousContact) {this._previousContact = previousContact;}

}
