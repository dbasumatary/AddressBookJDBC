package addressbook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    //Reading into the file
    public void readFromFile() {
        Path path = Paths.get("src/main/java/resource/AddressBookText.txt");

        try {
            System.out.println(Files.readAllLines(path));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Writing into the file
    public void writeIntoFile(String bookName, AddressBookRegister addressBookRegister) {

        AddressBookLogic addBook = addressBookRegister.findAddressBook(bookName);
        Path path = Paths.get("src/main/java/resource/AddressBookText.txt");

        try {
            Files.write(path, addBook.addressBook.toString().getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reading from csv files
    public void readCSVFile() {
        try {
            CSVReader csv = new CSVReader(new FileReader("src/main/java/resource/AddressBookCSV.csv"));
            //It creates a String array called contact to store each row of data in the CSV file.
            String[] contact;

            while ((contact = csv.readNext()) != null) {
                //It creates a new Contacts object for each row of data,
                //using the data in the contact array to set the values for each 'Contacts' object.
                Contacts newContact = new Contacts(contact[0], contact[1], contact[2], contact[3], contact[4],
                        Integer.parseInt(contact[5]), Long.parseLong(contact[6]), contact[7]);
                System.out.println(newContact);
            }
        }
        catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    // Writing into csv files
    public void writeCSVFile(String adBookName, AddressBookRegister adBookRegister) {

        //It finds the AddressBookLogic object corresponding to the adBookName using
        // the findAddressBook() method of the AddressBookRegister object.
        AddressBookLogic addBook = adBookRegister.findAddressBook(adBookName);
        try {
            CSVWriter csv = new CSVWriter(new FileWriter("src/main/java/resource/AddressBookCSV.csv"));
            for (Contacts contact : addBook.addressBook) {

                //It creates a new String array called writer that contains the data to be written to CSV file for each 'Contacts' object.
                String writer[] = new String[] { contact.getFirstName(), contact.getLastName(), contact.getAddress(),
                        contact.getCity(), contact.getState(), "" + contact.getZip(), "" + contact.getPhone(),
                        contact.getEmail() };

                // This writes the writer array to the CSV file using the writeNext() method of the CSVWriter object.
                csv.writeNext(writer);
            }
            csv.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

