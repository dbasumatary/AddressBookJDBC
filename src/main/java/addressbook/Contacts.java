package addressbook;

import java.util.Objects;

public class Contacts {

    //Instance variables
    String firstName, lastName, address, city, state, email;
    int zip;
    long phone;

    // Parameterized constructor for creating object
    public Contacts(String firstName, String lastName, String address, String city, String state, int zip, long phone,
                    String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    //It returns a string representation of an object.
    // It is used to provide a human-readable summary of an object's state.
    @Override
    public String toString() {
        return " Contact [FirstName = " + firstName + ", LastName = " + lastName + ", Address = " + address + ", City = " + city
                + ", State = " + state + ", Zip = " + zip + ", Phone = " + phone + ", Email = " + email + "]";
    }

    //The hashCode() method returns an integer value that represents the hash code of an object.
    //The Objects.hash() method is used to compute the hash code based on the firstName value.
    @Override
    public int hashCode() {
        return Objects.hash(firstName);
    }

    //The equals() method is used to compare two objects for equality.
    @Override
    public boolean equals(Object object) {

        //checks if the two objects are the same instance by comparing the memory addresses
        if (this == object)
            return true;
        if (object == null)
            return false;

        //checking if the other object is an instance of the same class.
        if (getClass() != object.getClass())
            return false;
        Contacts contacts = (Contacts) object;

        //The Objects.equals() method checks if the two objects are equal according to their equals() method.
        return Objects.equals(firstName, contacts.firstName);
    }
}

