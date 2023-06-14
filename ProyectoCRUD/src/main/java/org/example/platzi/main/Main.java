package org.example.platzi.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection myConn = null; // Nuestro objeto de conexion
        Statement myStamt = null; // Nuestro objeto statement
        ResultSet myRes = null; // Para procesar nuestros resultados
        try {
            // 1. Conexion a nuestra base de datos, pasando 3 argumentos
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234.");
            // 2. Crear una declaracion objeto
            myStamt = myConn.createStatement();
            // 3. Ejecutar consulta SQL
            myRes = myStamt.executeQuery("SELECT * FROM employees");
            while (myRes.next()) {
                System.out.println(myRes.getString("first_name"));
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algo salio mal ):");
        }
    }
}
