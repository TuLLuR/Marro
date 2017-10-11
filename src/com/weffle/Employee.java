package com.weffle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;

    public Employee() {}

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Employee[] getEmployees() {
        Database database = WebApplication.getDatabase();
        if (database.connect()) {
            try {
                ResultSet resultSet = database.getStatement().executeQuery("SELECT * FROM employee");
                ArrayList<Employee> employees = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    employees.add(new Employee(id, firstName, lastName));
                }
                return Arrays.stream(employees.toArray()).toArray(Employee[]::new);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        database.close();
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
