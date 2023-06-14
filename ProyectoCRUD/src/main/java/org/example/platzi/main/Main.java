package org.example.platzi.main;

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        try (Connection myConn = DatabaseConnection.getInstance()){
            Repository<Employee> repository = new EmployeeRepository();

            System.out.println("---Listando-----");
            repository.findAll().forEach(System.out::println);

            System.out.println("---Empleado eliminado-----");
            repository.delete(6);

            System.out.println("---Listando-----");
            repository.findAll().forEach(System.out::println);

        }

    }
}

