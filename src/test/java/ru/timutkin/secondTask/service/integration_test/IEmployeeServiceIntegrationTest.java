package ru.timutkin.secondTask.service.integration_test;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;
import ru.timutkin.secondTask.model.Employee;
import ru.timutkin.secondTask.service.EmployeeService;
import ru.timutkin.secondTask.service.IEmployeeService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@FieldDefaults(level = AccessLevel.PRIVATE)
class IEmployeeServiceIntegrationTest {

    EmployeeService employeeService;

    private static Employee EMPLOYEE = Employee.builder()
            .id("1")
            .name("Timofey")
            .surname("Utkin")
            .patronymic("Sergeevich")
            .position("Developer")
            .build();

    private static final String SURNAME = "Utkin";

    private static final String FAKE_SURNAME = "Sidorov";

    private static final String POSITION = "HR";

    private static final String FAKE_POSITION = "Owner";

    private static final int PATH_SIZE = 10;

    @BeforeEach
    void setUpData(){
        employeeService = new IEmployeeService();
    }

    @Test
    void shouldReturnTrue_addNewEmployeeTest() {
        EMPLOYEE.setName("Petr");
        EMPLOYEE.setId("8");
        EMPLOYEE.setSurname("Kitov");
        boolean resultAdd =  employeeService.addNewEmployee(EMPLOYEE);
        assertThat(resultAdd).isTrue();
    }

    @Test
    @Disabled
    void shouldReturnFalse_addNewEmployeeTest() {
        boolean resultAdd =  employeeService.addNewEmployee(EMPLOYEE);
        assertThat(resultAdd).isFalse();
    }

    @Test
    void shouldReturnListOfFourEmployees_listEmployeeBySurnameTest() {
        List<Employee> listEmployeeBySurname =
                employeeService.listEmployeeBySurname(SURNAME);
        assertThat(listEmployeeBySurname)
                .hasSizeLessThan(PATH_SIZE+1)
                .filteredOn(employee -> employee.getSurname().equals(SURNAME));

    }

    @Test
    void shouldReturnEmptyList_listEmployeeBySurnameTest() {
        List<Employee> listEmployeeBySurname =
                employeeService.listEmployeeBySurname(FAKE_SURNAME);
        assertThat(listEmployeeBySurname).isEmpty();
    }

    @Test
    void shouldReturnListOfThreeEmployees_listEmployeeByPositionTest() {
        List<Employee> listEmployeeByPosition =
                employeeService.listEmployeeByPosition(POSITION);

        assertThat(listEmployeeByPosition)
                .hasSizeLessThan(PATH_SIZE+1)
                .filteredOn(employee -> employee.getPosition().equals(POSITION));

    }

    @Test
    void shouldReturnEmptyList_listEmployeeByPositionTest() {
        List<Employee> listEmployeeByPosition =
                employeeService.listEmployeeByPosition(FAKE_POSITION);

        assertThat(listEmployeeByPosition).isEmpty();

    }

    @Test
    void shouldDeletedEmployee_deleteById() {
        Boolean addResult = employeeService.addNewEmployee(EMPLOYEE);
        assertThat(addResult).isFalse();
        employeeService.deleteById(EMPLOYEE.getId());
        addResult = employeeService.addNewEmployee(EMPLOYEE);
        assertThat(addResult).isTrue();
    }

}