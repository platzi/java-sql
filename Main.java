import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;

        try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234.");
            System.out.println("Genial, nos conectamos");

        } catch (Exception e) {
           e.printStackTrace();
            System.out.println("Algo salio mal ):");
        }
    }
}