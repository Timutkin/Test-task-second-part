package ru.timutkin.secondTask.service;

import ru.timutkin.secondTask.model.Employee;

import java.util.List;

public interface EmployeeService {

    Boolean addNewEmployee(Employee emp);

    List<Employee> listEmployeeBySurname(String surname);

    List<Employee> listEmployeeByPosition(String position);

    void deleteById(String id);
}
