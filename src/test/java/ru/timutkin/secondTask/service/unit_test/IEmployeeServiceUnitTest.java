package ru.timutkin.secondTask.service.unit_test;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.timutkin.secondTask.model.Employee;
import ru.timutkin.secondTask.service.EmployeeService;
import ru.timutkin.secondTask.service.IEmployeeService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class IEmployeeServiceUnitTest {

    EmployeeService employeeService;

    @Mock
    List<Employee> employees;

    private static final String SURNAME = "Utkin";

    private static final String POSITION = "Sales";

    private static final int BATCH_SIZE = 10;

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
        MockitoAnnotations.openMocks(this);
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
    void listEmployeeBySurnameTest() {
        EmployeeService service = getFakeIEmployeeService();
        List<Employee> employeeBySurname =
                service.listEmployeeBySurname(SURNAME);

        assertThat(employeeBySurname)
                .hasSizeLessThan(BATCH_SIZE+1)
                .filteredOn(employee -> employee.getSurname().equals(SURNAME));

    }

    @Test
    void listEmployeeByPositionTest() {
        EmployeeService service = getFakeIEmployeeService();
        List<Employee> employeesByPosition = service.listEmployeeByPosition(POSITION);

        assertThat(employeesByPosition)
                .hasSizeLessThan(BATCH_SIZE+1)
                .filteredOn(employee -> employee.getPosition().equals(POSITION));
    }

    @Test
    void deleteByIdTest() {
        List<Employee> fakeEmployee = new ArrayList<>();
        fakeEmployee.add(FAKE_EMPLOYEES.get(0));
        fakeEmployee.add(FAKE_EMPLOYEES.get(1));
        fakeEmployee.add(FAKE_EMPLOYEES.get(2));
        EmployeeService service = new IEmployeeService(fakeEmployee);

        service.deleteById(FAKE_EMPLOYEES.get(0).getId());
        boolean isContain = fakeEmployee.contains(EMPLOYEE);
        assertThat(isContain).isFalse();
    }

    private EmployeeService getFakeIEmployeeService(){
        List<Employee> fakeEmployee = new ArrayList<>();
        fakeEmployee.add(FAKE_EMPLOYEES.get(0));
        fakeEmployee.add(FAKE_EMPLOYEES.get(1));
        fakeEmployee.add(FAKE_EMPLOYEES.get(2));
        return new IEmployeeService(fakeEmployee);
    }


}
