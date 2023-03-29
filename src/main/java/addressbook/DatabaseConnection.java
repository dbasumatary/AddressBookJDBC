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

    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.displayDetails();
    }
}
