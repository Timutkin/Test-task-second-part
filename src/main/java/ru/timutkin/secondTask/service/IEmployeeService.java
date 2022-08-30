package ru.timutkin.secondTask.service;

import lombok.AllArgsConstructor;
import ru.timutkin.secondTask.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class IEmployeeService implements EmployeeService{
    public static void main(String[] args) {
    }

    private List<Employee> employees = new ArrayList<>();

    @Override
    public Boolean addNewEmployee(Employee emp) {
        if (employees.contains(emp)){
            return false;
        }

        employees.add(emp);

        return true;
    }

    @Override
    public List<Employee> listEmployeeBySurname(String surname) {
       return employees.stream()
               .filter(employee -> employee.getSurname().equals(surname))
               .limit(10)
               .collect(Collectors.toList());
    }

    @Override
    public List<Employee> listEmployeeByPosition(String position) {
        return employees.stream()
                .filter(employee -> employee.getPosition().equals(position))
                .sorted(
                        Comparator.comparing(Employee::getSurname)
                                .thenComparing(Employee::getName)
                                .thenComparing(Employee::getPatronymic)
                )
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        for (Employee employee: employees){
            if (employee.getId().equals(id)){
                employees.remove(employee);
                break;
            }
        }
    }

    public IEmployeeService() {
        Collections.addAll(employees,
                Employee.builder()
                        .id("1")
                        .name("Timofey")
                        .surname("Utkin")
                        .patronymic("Sergeevich")
                        .position("Developer")
                        .build()
                ,
                Employee.builder()
                        .id("2")
                        .name("Gleb")
                        .surname("Utkin")
                        .patronymic("Alekseevich")
                        .position("Sales")
                        .build()
                ,
                Employee.builder()
                        .id("3")
                        .name("Ivan")
                        .surname("Utkin")
                        .patronymic("Aleksandrovich")
                        .position("HR")
                        .build()
                ,
                Employee.builder()
                        .id("4")
                        .name("Dmitriy")
                        .surname("Utkin")
                        .patronymic("Sergeevich")
                        .position("HR")
                        .build()
                ,
                Employee.builder()
                        .id("5")
                        .name("Max")
                        .surname("Smirnov")
                        .patronymic("Dmitrievich")
                        .position("Sales")
                        .build()
                ,
                Employee.builder()
                        .id("6")
                        .name("Petr")
                        .surname("{Petrov}")
                        .patronymic("Aleksandrovich")
                        .position("Developer")
                        .build()
                ,Employee.builder()
                        .id("7")
                        .name("Maria")
                        .surname("Stepanova")
                        .patronymic("Sergeevna")
                        .position("HR")
                        .build()
        );
    }
}
