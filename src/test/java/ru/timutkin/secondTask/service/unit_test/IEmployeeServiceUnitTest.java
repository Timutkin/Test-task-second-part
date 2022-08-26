package ru.timutkin.secondTask.service.unit_test;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.verification.VerificationMode;
import ru.timutkin.secondTask.model.Employee;
import ru.timutkin.secondTask.service.EmployeeService;
import ru.timutkin.secondTask.service.IEmployeeService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class IEmployeeServiceUnitTest {

    EmployeeService employeeService;

    List<Employee> employees;

    private static final String SURNAME = "Utkin";

    private static final int PATH_SIZE = 10;

    private static Employee EMPLOYEE = Employee.builder()
            .id("1")
            .name("Timofey")
            .surname("Utkin")
            .patronymic("Sergeevich")
            .position("Developer")
            .build();

    private static List<Employee> FAKE_EMPLOYEES = new ArrayList<>();

    static {
        Collections.addAll(FAKE_EMPLOYEES,
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
                );
    }



    @BeforeEach
    void initEmployeeService(){
        employees = mock(ArrayList.class);
        employeeService = new IEmployeeService(employees);
    }

    @Test
    void shouldReturnFalse_EmployeeAlreadyExists_addNewEmployeeTest() {

        doReturn(true).when(employees).contains(EMPLOYEE);

        Boolean resultAddNewEmployee = employeeService.addNewEmployee(EMPLOYEE);

        assertThat(resultAddNewEmployee).isFalse();

    }

    @Test
    void shouldReturnTrue_addNewEmployeeTest() {

        doReturn(false).when(employees).contains(EMPLOYEE);

        Boolean resultAddNewEmployee = employeeService.addNewEmployee(EMPLOYEE);

        assertThat(resultAddNewEmployee).isTrue();

    }

    @Test
    @Disabled
    void listEmployeeBySurname() {
//        employees = spy(ArrayList.class);
//        doReturn(fakeEmployees).when(employees).stream()
//                .filter(employee -> employee.getSurname().equals(SURNAME))
//                .limit(10)
//                .collect(Collectors.toList());
//        List<Employee> employeeListBySurname =
//                employeeService.listEmployeeBySurname(Mockito.anyString());
//        assertThat(employeeListBySurname)
//                .hasSizeLessThan(PATH_SIZE+1)
//                .filteredOn(employee -> employee.getPosition().equals(SURNAME));
    }

    @Test
    void listEmployeeByPosition() {
    }

    @Test
    void deleteById() {


    }

}
