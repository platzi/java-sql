package org.example.platzi.view;

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
public class SwingApp extends JFrame {

    private final Repository<Employee> employeeRepository;
    private final JTable employeeTable;

    public SwingApp() {
        // Configurar la ventana
        setTitle("Gestión de Empleados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 230);

        // Crear una tabla para mostrar los empleados
        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Crear botones para acciones
        JButton agregarButton = new JButton("Agregar");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");

        // Configurar el panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Establecer estilos para los botones
        agregarButton.setBackground(new Color(46, 204, 113));
        agregarButton.setForeground(Color.WHITE);
        agregarButton.setFocusPainted(false);

        actualizarButton.setBackground(new Color(52, 152, 219));
        actualizarButton.setForeground(Color.WHITE);
        actualizarButton.setFocusPainted(false);

        eliminarButton.setBackground(new Color(231, 76, 60));
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setFocusPainted(false);

        // Crear el objeto Repository para acceder a la base de datos
        employeeRepository = new EmployeeRepository();

        // Cargar los empleados iniciales en la tabla
        refreshEmployeeTable();

        // Agregar ActionListener para los botones
        agregarButton.addActionListener(e -> {
            try {
                agregarEmpleado();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        actualizarButton.addActionListener(e -> actualizarEmpleado());

        eliminarButton.addActionListener(e -> eliminarEmpleado());
    }

    private void refreshEmployeeTable() {
        // Obtener la lista actualizada de empleados desde la base de datos
        try {
            List<Employee> employees = employeeRepository.findAll();

            // Crear un modelo de tabla y establecer los datos de los empleados
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Apellido Paterno");
            model.addColumn("Apellido Materno");
            model.addColumn("Email");
            model.addColumn("Salario");

            for (Employee employee : employees) {
                Object[] rowData = {
                        employee.getId(),
                        employee.getFirst_name(),
                        employee.getPa_surname(),
                        employee.getMa_surname(),
                        employee.getEmail(),
                        employee.getSalary()
                };
                model.addRow(rowData);
            }

            // Establecer el modelo de tabla actualizado
            employeeTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los empleados de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarEmpleado() throws SQLException {
        // Crear un formulario para agregar un empleado
        JTextField nombreField = new JTextField();
        JTextField paternoField = new JTextField();
        JTextField maternoField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField salarioField = new JTextField();

        Object[] fields = {
                "Nombre:", nombreField,
                "Apellido Paterno:", paternoField,
                "Apellido Materno:", maternoField,
                "Email:", emailField,
                "Salario:", salarioField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Agregar Empleado", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Crear un nuevo objeto Employee con los datos ingresados
            Employee employee = new Employee();
            employee.setFirst_name(nombreField.getText());
            employee.setPa_surname(paternoField.getText());
            employee.setMa_surname(maternoField.getText());
            employee.setEmail(emailField.getText());
            employee.setSalary(Float.parseFloat(salarioField.getText()));

            // Guardar el nuevo empleado en la base de datos
            employeeRepository.save(employee);

            // Actualizar la tabla con los empleados actualizados
            refreshEmployeeTable();

            JOptionPane.showMessageDialog(this, "Empleado agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarEmpleado() {
        // Obtener el ID del empleado a actualizar
        String empleadoIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del empleado a actualizar:", "Actualizar Empleado", JOptionPane.QUESTION_MESSAGE);
        if (empleadoIdStr != null) {
            try {
                int empleadoId = Integer.parseInt(empleadoIdStr);

                // Obtener el empleado desde la base de datos
                Employee empleado = employeeRepository.getById(empleadoId);

                if (empleado != null) {
                    // Crear un formulario con los datos del empleado
                    JTextField nombreField = new JTextField(empleado.getFirst_name());
                    JTextField apellidoPaternoField = new JTextField(empleado.getPa_surname());
                    JTextField apellidoMaternoField = new JTextField(empleado.getMa_surname());
                    JTextField emailField = new JTextField(empleado.getEmail());
                    JTextField salarioField = new JTextField(String.valueOf(empleado.getSalary()));

                    Object[] fields = {
                            "Nombre:", nombreField,
                            "Apellido Paterno:", apellidoPaternoField,
                            "Apellido Materno:", apellidoMaternoField,
                            "Email:", emailField,
                            "Salario:", salarioField
                    };

                    int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar Empleado", JOptionPane.OK_CANCEL_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION) {
                        // Actualizar los datos del empleado con los valores ingresados en el formulario
                        empleado.setFirst_name(nombreField.getText());
                        empleado.setPa_surname(apellidoPaternoField.getText());
                        empleado.setMa_surname(apellidoMaternoField.getText());
                        empleado.setEmail(emailField.getText());
                        empleado.setSalary(Float.parseFloat(salarioField.getText()));

                        // Guardar los cambios en la base de datos
                        employeeRepository.save(empleado);

                        // Actualizar la tabla de empleados en la interfaz
                        refreshEmployeeTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos del empleado de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarEmpleado() {
        // Obtener el ID del empleado a eliminar
        String empleadoIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del empleado a eliminar:", "Eliminar Empleado", JOptionPane.QUESTION_MESSAGE);
        if (empleadoIdStr != null) {
            try {
                int empleadoId = Integer.parseInt(empleadoIdStr);

                // Confirmar la eliminación del empleado
                int confirmResult = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el empleado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Eliminar el empleado de la base de datos
                    employeeRepository.delete(empleadoId);

                    // Actualizar la tabla de empleados en la interfaz
                    refreshEmployeeTable();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID del empleado", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
