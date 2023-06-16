package org.example.platzi.main;

import org.example.platzi.Util.UtilEntity;
import org.example.platzi.entity.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        List<Employee> employees = em.createQuery("SELECT e from Employee e", Employee.class).getResultList();
        System.out.println("------LISTAR TODOS---------");
        employees.forEach(System.out::println);

        System.out.println("-----BUSCAR UNO-----------");
        int employeeId = 3;
        Employee employee = em.find(Employee.class, employeeId);
        System.out.println("Empleado encontrado" + employee);

        /*System.out.println("----CREAR UNO-------");
        Employee newEmployee = new Employee();
        newEmployee.setFirstName("Diego");
        newEmployee.setPaSurname("Lechuga");
        newEmployee.setMaSurname("Pimentel");
        newEmployee.setEmail("diego@example.com");
        newEmployee.setSalary((float) 890000);

        em.getTransaction().begin();
        em.persist(newEmployee);
        em.getTransaction().commit();

        System.out.println("Nuevo empleado creado:" + newEmployee);*/

        /*System.out.println("----ACTUALIZAR------");
        int employeeToUpdateId = 2;
        Employee employeeToUpdate = em.find(Employee.class, employeeToUpdateId);
        System.out.println("Empleado a modificar" + employeeToUpdate);

        employeeToUpdate.setFirstName("Irving");
        employeeToUpdate.setPaSurname("Juarez");
        employeeToUpdate.setSalary(97000F);

        em.getTransaction().begin();
        em.merge(employeeToUpdate);
        em.getTransaction().commit();

        System.out.println("Empleado actualizado" + employeeToUpdate);*/

        System.out.println("----ELIMINAR-----");
        int employeeToDeleteId = 1;
        Employee employeeToDelete = em.find(Employee.class, employeeToDeleteId);
        System.out.println("Empleado a eliminar" + employeeToDelete);

        em.getTransaction().begin();
        em.remove(employeeToDelete);
        em.getTransaction().commit();
        em.close();

    }
}

