import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        PreparedStatement myStamt = null;

        try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234.");
            System.out.println("Genial, nos conectamos");

            String sql = "INSERT INTO employees (first_name, pa_surname) VALUES (?, ?)";

            myStamt = myConn.prepareStatement(sql);
            myStamt.setString(1, "Johana");
            myStamt.setString(2, "Dorantes");

            int rowsAffected = myStamt.executeUpdate();
 
            if(rowsAffected>0){
                System.out.println("Se ha creado un nuevo cliente");

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algo salio mal ):");
        }
    }
}