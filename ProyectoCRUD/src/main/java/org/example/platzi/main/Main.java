package org.example.platzi.main;

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection myConn = DatabaseConnection.getInstance()){

            if(myConn.getAutoCommit()){
                myConn.setAutoCommit((false));
            }

            try{
                Repository<Employee> repository = new EmployeeRepository(myConn);

                System.out.println("-----Insertar un nuevo cliente------");
                Employee employee = new Employee();
                /*employee.setFirst_name("America");
                employee.setPa_surname("Lopez");
                employee.setMa_surname("Villa");
                employee.setEmail("ame2@example.com");
                employee.setSalary(3000F);
                employee.setCurp("AMEC234Y91JOLPSDET");
                repository.save(employee);
                myConn.commit();*/

                employee.setFirst_name("David");
                employee.setPa_surname("Gutierrez");
                employee.setMa_surname("Olvera");
                employee.setEmail("olv2@example.com");
                employee.setSalary(32000F);
                employee.setCurp("AMEC234Y91JOLPSDET");
                repository.save(employee);
                myConn.commit();


            } catch (SQLException e) {
                myConn.rollback();
                throw new RuntimeException(e);
            }

        }
    }
}

