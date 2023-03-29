package addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class AddressBookLogic {

    // Hashset of contacts class type
    public HashSet<Contacts> addressBook = new HashSet<>();

    // Hashmap of contacts and their respective states
    public HashMap<String, ArrayList<Contacts>> stateContactMap = new HashMap<>();

    // Hashmap of contacts and their respective cities
    public HashMap<String, ArrayList<Contacts>> cityContactMap = new HashMap<>();

    Scanner scanner = new Scanner(System.in);

    // Read all the values and create an object and return the object
    public Contacts inputDetails() {

        System.out.print(" Please enter the first name: ");
        String firstName = scanner.next();

        System.out.print(" Please enter the last name: ");
        String lastName = scanner.next();

        System.out.print(" Please enter the address: ");
        String address = scanner.next();

        System.out.print(" Please enter the city: ");
        String city = scanner.next();

        System.out.print(" Please enter the state: ");
        String state = scanner.next();

        System.out.print(" Please enter the zip code: ");
        int zip = scanner.nextInt();

        System.out.print(" Please enter the phone number: ");
        long phone = scanner.nextLong();

        System.out.print(" Please enter the email address: ");
        String email = scanner.next();

        // Creating a new object of the contacts class with the parameter values filled by the scanner
        Contacts contact = new Contacts(firstName, lastName, address, city, state, zip, phone, email);
        return contact;
    }

    // Display all the contacts in the address book arraylist
    public void displayContacts() {
        for (Contacts contact : addressBook) {
            System.out.println(contact);
        }
    }

    // Method to add objects of addressbook.Contacts class to address book arraylist
    public void addContact() {

        System.out.print(" Please enter how many contacts do you want to add: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            Contacts newContact = inputDetails();

            // If the add method returns false it means that there is already a contact of this name
            // We used hash set, so it'll only allow unique values to be stored.
            if (!addressBook.add(newContact)) {
                System.out.println(" ERROR: A contact of this name already exists!\n");
                i--;
                continue;
            }

            if (!cityContactMap.containsKey(newContact.getCity()))
                cityContactMap.put(newContact.getCity(), new ArrayList<>());
            cityContactMap.get(newContact.getCity()).add(newContact);

            if (!stateContactMap.containsKey(newContact.getState()))
                stateContactMap.put(newContact.getState(), new ArrayList<>());
            stateContactMap.get(newContact.getState()).add(newContact);

            System.out.println();
        }
    }

    // Method to find contacts via name in the address book arraylist
    public Contacts findContact() {

        System.out.print(" Please enter the first name: ");
        String firstName = scanner.next();

        for (Contacts contact : addressBook) {
            if (firstName.compareToIgnoreCase(contact.getFirstName()) == 0) {
                return contact;
            }
        }
        return null;
    }

    //We search for the contact by first name and then delete that contact detail from the arraylist

    public void deleteContact() {
        Contacts contact = findContact();
        if (contact == null) {
            System.out.println(" ERROR: No such contact exists!");
            return;
        }
        addressBook.remove(contact);
        System.out.println(" Contact is deleted!");
    }

    // Method to edit contacts
    public void editContact() {
        //We read all the parameters for that contact and then set it to the array list
        Contacts contact = findContact();
        if (contact == null) {
            System.out.println(" ERROR: No such contact exists!");
            return;
        }
        System.out.println(" Contact found! Please enter new details of the contact");

        //Remove the contact and add anew the contact details
        addressBook.remove(contact);
        addressBook.add(inputDetails());
    }
}
