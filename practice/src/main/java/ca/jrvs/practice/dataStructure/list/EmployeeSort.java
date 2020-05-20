package ca.jrvs.practice.dataStructure.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeSort {

  public static void main(String[] args) {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee(1, "Bob", 25, 60000L));
    employees.add(new Employee(2, "Amy", 26, 50000L));
    employees.add(new Employee(3, "Jane", 25, 50000L));
    employees.add(new Employee(4, "Ben", 28, 30000L));

    System.out.println(employees.toString());
    Collections.sort(employees);
    System.out.println(employees.toString());

    Comparator<Employee> ageComparator = (employee1, employee2) -> {return employee1.getAge()-employee2.getAge();};
    Comparator<Employee> salaryComparator = (employee1, employee2) -> {return (int) (employee1.getSalary()-employee2.getSalary());};

    Collections.sort(employees, ageComparator);
    System.out.println(employees.toString());
    Collections.sort(employees, salaryComparator);
    System.out.println(employees.toString());
  }
}