import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStamt = null;
        ResultSet myRes = null;

        try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234.");
            System.out.println("Genial, nos conectamos");

            myStamt = myConn.createStatement();

            int rowsAffected = myStamt.executeUpdate("DELETE FROM employees "+ "WHERE first_name='Johana'");
           
            myRes = myStamt.executeQuery("SELECT * FROM employees");

            System.out.println("Empleados despues de eliminar:");
            while(myRes.next()){
                System.out.println(myRes.getString("first_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algo salio mal ):");
        }
    }
}
