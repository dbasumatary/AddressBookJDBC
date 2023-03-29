package addressbook;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookRegister {

    // Map all the names and address books
    HashMap<String, AddressBookLogic> addressBookRegistry = new HashMap<>();
    public Scanner scanner = new Scanner(System.in);

    // Method to find address book object in the hash map
    public AddressBookLogic findAddressBook(String adBookName) {

        for (Map.Entry<String, AddressBookLogic> entry : addressBookRegistry.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(adBookName)) {
                return entry.getValue();
            }
        }
        return null;
    }

    // Method to create a new address book in the hash map and that the names are unique
    public void addAddressBook() {

        System.out.print(" Please enter the name of the address book: ");
        String name = scanner.next();

        if (addressBookRegistry.get(name) != null) {
            System.out.println(" ERROR: An address book of this name already exists!");
            return;
        }
        addressBookRegistry.put(name, new AddressBookLogic());

    }

    // Method to call the add contact method in the address book class
    public void addContact() {

        System.out.print(" Please enter the name of the address book: ");
        String name = scanner.next();
        AddressBookLogic adBook = findAddressBook(name);

        if (adBook == null) {
            System.out.println(" ERROR: An address book of this name does not exist");
            return;
        }
        adBook.addContact();
    }

    // Method to call the edit contact method in the address book logic class
    public void editContact() {
        System.out.print(" Please enter the name of the address book: ");
        String name = scanner.next();
        AddressBookLogic adBook = findAddressBook(name);

        if (adBook == null) {
            System.out.println(" ERROR: An address book of this name does not exist!");
            return;
        }
        adBook.editContact();
    }

    // Method to call the delete contact method in the address book logic class
    public void deleteContact() {
        System.out.print(" Please enter the name of the address book: ");
        String name = scanner.next();
        AddressBookLogic adBook = findAddressBook(name);

        if (adBook == null) {
            System.out.println(" ERROR: An address book of this name does not exist!");
            return;
        }
        adBook.deleteContact();
    }

    // Method to display contacts in address book logic
    public void displayContacts() {
        System.out.print(" Please enter the name of the address book: ");
        String name = scanner.next();
        AddressBookLogic adBook = findAddressBook(name);

        if (adBook == null) {
            System.out.println(" ERROR: An address book of this name does not exist!");
            return;
        }
        adBook.displayContacts();
    }

    // Method to view all contacts in all address books in a particular city or state
    public void viewCityState(String location, String choice) {
        addressBookRegistry.values().stream().forEach((adBook) -> {
            adBook.addressBook.stream().filter(contact -> {

                if (choice.equalsIgnoreCase("city"))
                    return contact.getCity().equalsIgnoreCase(location);
                else
                    return contact.getState().equalsIgnoreCase(location);
            }).forEach(contact -> System.out.println(contact));
        });
    }

    // Method to search all the address books for a person in the corresponding city or state
    public void searchCityState(String name, String location, String choice) {
        addressBookRegistry.values().stream().forEach((adBook) -> {
            adBook.addressBook.stream().filter(contact -> {

                        if (choice.equalsIgnoreCase("city"))
                            return contact.getCity().equalsIgnoreCase(location);
                        else
                            return contact.getState().equalsIgnoreCase(location);
                    }).filter(contact -> contact.getFirstName().equalsIgnoreCase(name))
                    .forEach(contact -> System.out.println(contact));
        });
    }

    // Method to count all contacts in a given state or city across all address books
    public void countContact(String location, String choice) {
        int finalCount = 0;
        for (AddressBookLogic adBook : addressBookRegistry.values()) {
            finalCount += adBook.addressBook.stream().filter(contact -> {
                if (choice.equalsIgnoreCase("city"))
                    return contact.getCity().equalsIgnoreCase(location);
                else
                    return contact.getState().equalsIgnoreCase(location);
            }).count();
        }
        System.out.println(" The total count is : " + finalCount);
    }

    // Method to print out contacts sorted by name, city, state or zip
    public void sortContacts(String addBookName, String sortChoice) {
        AddressBookLogic adBook = findAddressBook(addBookName);

        adBook.addressBook.stream().sorted((contact1, contact2) -> {
            if (sortChoice.equalsIgnoreCase("name") || sortChoice.equalsIgnoreCase("n"))
                return contact1.getFirstName().compareToIgnoreCase(contact2.getFirstName());

            else if (sortChoice.equalsIgnoreCase("city") || sortChoice.equalsIgnoreCase("c"))
                return contact1.getCity().compareToIgnoreCase(contact2.getCity());
            else if (sortChoice.equalsIgnoreCase("state") || sortChoice.equalsIgnoreCase("s"))
                return contact1.getState().compareToIgnoreCase(contact2.getState());
            else
                return contact1.getZip().compareTo(contact2.getZip());
        }).forEach(contact -> System.out.println(contact));
    }
}
