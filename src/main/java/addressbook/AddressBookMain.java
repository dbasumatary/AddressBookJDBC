package addressbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Date;
import java.util.Scanner;

public class AddressBookMain {
    public static Scanner scanner = new Scanner(System.in);
    public static void operations() throws IOException {
        DatabaseConnection connection = new DatabaseConnection();
        AddressBookRegister bookRegister = new AddressBookRegister();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String flag;

        while (true) {
            System.out.println(" ---- Address Book Menu ----\n");
            System.out.println(" 1. Add contacts\n 2. Display contacts\n 3. Edit contacts\n 4. Delete contact\n"
                    + " 5. Add address book\n 6. Search for contact\n 7. View contacts by city or state\n"
                    + " 8. Count contacts by city or state\n 9. Sort contacts\n 10. File IO\n 11. Database operations\n"
                    + " 12. Exit");
            System.out.print("\n Please enter your choice: ");
            flag = scanner.next();

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
                    String name = scanner.next();

                    System.out.print(" Please enter to search by city or state(city/state): ");
                    String choice = scanner.next();

                    System.out.print(" Please enter the location: ");
                    String location = scanner.next();

                    bookRegister.searchCityState(name, location, choice);
                    break;

                // View all contacts by city or state
                case "7":
                    System.out.print(" Please enter to view by city or state(city/state): ");
                    String viewChoice = scanner.next();

                    System.out.print(" Please enter the location: ");
                    String viewLocation = scanner.next();

                    bookRegister.viewCityState(viewLocation, viewChoice);
                    break;

                // Count all contacts in a particular city or state
                case "8":
                    System.out.print(" Please enter to count by city or state(city/state): ");
                    String countChoice = scanner.next();

                    System.out.print(" Please enter the location: ");
                    String countLocation = scanner.next();

                    bookRegister.countContact(countLocation, countChoice);
                    break;

                // Sort the contacts in an address book by name, city, state or zip
                case "9":
                    System.out.print(" Please enter which address book to sort: ");
                    String sortAdBook = scanner.next();

                    System.out.print(" Please enter to sort by name, city, state or zip: ");
                    String sortChoice = scanner.next();

                    bookRegister.sortContacts(sortAdBook, sortChoice);
                    break;

                case "10":                                           // Handling file IO operations
                    FileHandler file = new FileHandler();

                    System.out.print(" Please type whether to perform read or write: ");
                    String fileOption = scanner.next();
                    System.out.print(" Please enter which file format to operate in (txt/csv/json): ");
                    String fileType = scanner.next();

                    //Adding into txt files
                    if (fileType.equalsIgnoreCase("txt")){
                        if (fileOption.equalsIgnoreCase("read") || fileOption.equalsIgnoreCase("r"))
                            file.readFromFile();
                        else {
                            System.out.print(" Please enter which address book to write csv file: ");
                            String adBookFile = scanner.next();
                            file.writeIntoFile(adBookFile, bookRegister);
                        }
                    }
                    //Adding into csv files
                    else if (fileType.equalsIgnoreCase("csv")) {
                        if (fileOption.equalsIgnoreCase("read") || fileOption.equalsIgnoreCase("r"))
                            file.readCSVFile();
                        else {
                            System.out.print(" Please enter which address book to write csv file: ");
                            String adBookFile = scanner.next();

                            file.writeCSVFile(adBookFile, bookRegister);
                        }
                    }
                    //Adding into json file
                    else if (fileType.equalsIgnoreCase("json")) {
                        if (fileOption.equalsIgnoreCase("read") || fileOption.equalsIgnoreCase("r"))
                            file.readJSONFile();
                        else {
                            System.out.print(" Please enter which address book to write in: ");
                            String adJSONFile = scanner.next();
                            file.writeJSONFile(adJSONFile, bookRegister);
                        }
                    }
                    break;
                case "11":
                    int roll = -1;
                    while (roll !=0){
                        System.out.println("\nEnter the operation to perform in database :");
                        System.out.println("1. Display details from database\n2. Update contact of person in database\n" +
                                "3. Retrieve contacts in given time period\n4. Count of contacts by city\n5. Count of contacts by state\n" +
                                "6. Exit");
                        roll = scanner.nextInt();
                        switch (roll){
                            case 1: connection.displayDetails();
                                    break;
                            case 2: System.out.print("Enter first name: ");
                                    String fName = scanner.next();
                                    System.out.print("Enter last name: ");
                                    String lName = scanner.next();
                                    System.out.print("Enter the new phone number: ");
                                    int newPhone = scanner.nextInt();
                                    connection.updateContact(fName,lName,newPhone);
                                    break;
                            case 3: System.out.print("Enter the start date in format YYYY-MM-DD : ");
                                    Date startDate = Date.valueOf(bufferedReader.readLine());
                                    System.out.print("Enter the end date in format YYYY-MM-DD : ");
                                    Date endDate = Date.valueOf(bufferedReader.readLine());
                                    connection.getEmployeesByDateRange(startDate,endDate);
                                    break;
                            case 4: System.out.print("Enter the city: ");
                                    String cityName = scanner.next();
                                    connection.getCountOfContactsByCity(cityName);
                                    break;
                            case 5: System.out.print("Enter the state: ");
                                    String stateName = scanner.next();
                                    connection.getCountOfContactsByState(stateName);
                                    break;
                            case 6: roll = 0;
                                    break;
                        }
                    }
                    break;
                case "12":                                               // Exit the program
                    System.out.println(" Thank you!");
                    return;
                default:                                                 // If in case the user enters invalid choice
                    System.out.println(" Please enter a valid choice: ");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println(" Welcome to the Address Book Program in Java");
        operations();
    }
}