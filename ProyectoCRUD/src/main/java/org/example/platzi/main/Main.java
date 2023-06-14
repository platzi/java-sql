package org.example.platzi.main;

import org.example.platzi.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        try (Connection myConn = DatabaseConnection.getInstance();
             Statement myStamt = myConn.createStatement();
             ResultSet myRes = myStamt.executeQuery("SELECT * FROM employees");){

            while (myRes.next()) {
                System.out.println(myRes.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algo salio mal ):");
        }
    }
}

