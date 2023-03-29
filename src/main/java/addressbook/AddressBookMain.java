package addressbook;

import java.util.Scanner;

public class AddressBookMain {
    public static Scanner scan = new Scanner(System.in);
    public static void operations(){
        AddressBookRegister bookRegister = new AddressBookRegister();
        String flag;

        while (true) {
            System.out.println(" ---- Address Book Menu ----\n");
            System.out.println(" 1. Add contacts\n 2. Display contacts\n 3. Edit contacts\n 4. Delete contact\n"
                    + " 5. Add address book\n 6. Search for contact\n 7. View contacts by city or state\n"
                    + " 8. Count contacts by city or state\n 9. Sort contacts\n" + " 10. File IO\n 11. Exit");
            System.out.print("\n Please enter your choice: ");
            flag = scan.next();

            switch (flag) {

                // Add new contact(s) to the address book arraylist
                case "1":
                    bookRegister.addContact();
                    break;

                // Display all the contacts stored in the address book arraylist
                case "2":
                    bookRegister.displayContacts();
                    break;

                // Edit a contact in the address book
                case "3":
                    bookRegister.editContact();
                    break;

                // Delete a contact from the address book
                case "4":
                    bookRegister.deleteContact();
                    break;

                // Adding a new address book to the list
                case "5":
                    bookRegister.addAddressBook();
                    break;

                // Search for a contact by using city or state
                case "6":
                    System.out.print(" Please enter the name of the contact you want to find: ");
                    String name = scan.next();

                    System.out.print(" Please enter to search by city or state(city/state): ");
                    String choice = scan.next();

                    System.out.print(" Please enter the location: ");
                    String location = scan.next();

                    bookRegister.searchCityState(name, location, choice);
                    break;

                // View all contacts by city or state
                case "7":
                    System.out.print(" Please enter to view by city or state(city/state): ");
                    String viewChoice = scan.next();

                    System.out.print(" Please enter the location: ");
                    String viewLocation = scan.next();

                    bookRegister.viewCityState(viewLocation, viewChoice);
                    break;

                // Count all contacts in a particular city or state
                case "8":
                    System.out.print(" Please enter to count by city or state(city/state): ");
                    String countChoice = scan.next();

                    System.out.print(" Please enter the location: ");
                    String countLocation = scan.next();

                    bookRegister.countContact(countLocation, countChoice);
                    break;

                // Sort the contacts in an address book by name, city, state or zip
                case "9":
                    System.out.print(" Please enter which address book to sort: ");
                    String sortAdBook = scan.next();

                    System.out.print(" Please enter to sort by name, city, state or zip: ");
                    String sortChoice = scan.next();

                    bookRegister.sortContacts(sortAdBook, sortChoice);
                    break;

                case "10":                                           // Handling file IO operations
                    FileHandler file = new FileHandler();

                    System.out.print(" Please enter to perform read or write: ");
                    String fileOption = scan.next();

                    if (fileOption.equalsIgnoreCase("read") || fileOption.equalsIgnoreCase("r"))
                        file.readFromFile();
                    else {
                        System.out.print(" Please enter which address book to write: ");
                        String adBookFile = scan.next();

                        file.writeIntoFile(adBookFile, bookRegister);
                    }
                    break;

                case "11":                                               // Exit the program
                    System.out.println(" Thank you!");
                    return;

                default:                                                 // If in case the user enters invalid choice
                    System.out.println(" Please enter a valid choice: ");
            }
        }
    }
    public static void main(String[] args){
        System.out.println(" Welcome to the Address Book Program in Java");
        operations();
    }
}
