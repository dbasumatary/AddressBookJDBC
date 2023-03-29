package addressbook;

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
}

