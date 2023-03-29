package addressbook;

import java.sql.*;

public class DatabaseConnection {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/address_book_service";
        String userName = "root";
        String password = "DBUnited23@sql";
        Connection connection = null;
        try {
            //load and register the driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection established\n");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    //UC16 - Retrieve all entries from the database
    public void displayDetails() {
        try {
            Connection con = this.getConnection();
            String query = "select * from address_book";
            Statement statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                System.out.println("First Name : " + set.getString(1));
                System.out.println("Last Name : " + set.getString(2));
                System.out.println("Address : " + set.getString(3));
                System.out.println("City : " + set.getString(4));
                System.out.println("State : " + set.getString(5));
                System.out.println("Zip Code : " + set.getInt(6));
                System.out.println("Phone Number : " + set.getInt(7));
                System.out.println("Email address : " + set.getString(8));
                System.out.println("**************************************************\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //UC17 - Updating the contact info and sync it with database using JDBC Prepared Statement
    public void updateContact(String firstName, String lastName, int phoneNumber){
        try {
            Connection connection = this.getConnection();

            //The query updates the phone_num column of address_book table for the person with specified first_name and last_name.
            String query = "UPDATE address_book SET phone_num = ? WHERE first_name = ? AND last_name = ?";

            //The PreparedStatement object is used to set the new value of phone_num
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, phoneNumber);
            statement.setString(2, firstName);
            statement.setString(3, lastName);

            //the executeUpdate() method returns the number of rows affected by the update.
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Phone Number updated successfully for person with first name " + firstName + " and last name "+lastName);
            } else {
                System.out.println("No employee found with the given name ");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //UC18 - Retrieve contacts from database in the given date range
    public void getEmployeesByDateRange(Date startDate, Date endDate) {
        try {
            Connection con = this.getConnection();
            String query = "SELECT * FROM address_book WHERE date_added BETWEEN ? AND ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                System.out.println("First Name : " + set.getString(1));
                System.out.println("Last Name : " + set.getString(2));
                System.out.println("Address : " + set.getString(3));
                System.out.println("City : " + set.getString(4));
                System.out.println("State : " + set.getString(5));
                System.out.println("Zip Code : " + set.getInt(6));
                System.out.println("Phone Number : " + set.getInt(7));
                System.out.println("Email address : " + set.getString(8));
                System.out.println("**************************************************\n");
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //UC19 - Get count of contacts group by city
    public void getCountOfContactsByCity(String city) {
        try {
            Connection con = this.getConnection();
            int count = 0;
            String query = "SELECT COUNT(*) FROM address_book WHERE city = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, city);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            preparedStatement.close();
            if (count > 0) System.out.println("The number of contacts in " + city + " is: " + count);
            else System.out.println("No such city found in the database");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //UC19 - Get count of contacts group by state
    public void getCountOfContactsByState(String state) {
        try {
            Connection con = this.getConnection();
            int count = 0;
            String query = "SELECT COUNT(*) FROM address_book WHERE state = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, state);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            preparedStatement.close();
            if (count > 0) System.out.println("The number of contacts in " + state + " is: " + count);
            else System.out.println("No such state found in the database");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
