package vn.techzen.academy_pnv_12.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.model.Employee;
import vn.techzen.academy_pnv_12.model.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    // list of employees
    private final List<Employee> employees;

    public EmployeeController() {
        employees = createEmployees();
    }

    private List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(
                UUID.randomUUID(),
                "Nguyễn Văn A",
                LocalDate.of(1990, 1, 1),
                Gender.MALE,
                10000.0,
                "0123456789"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Trần Thị B",
                LocalDate.of(1992, 2, 2),
                Gender.FEMALE,
                12000.0,
                "0987654321"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Lê Văn C",
                LocalDate.of(1995, 3, 3),
                Gender.MALE,
                15000.0,
                "0123456789"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Nguyễn Thị D",
                LocalDate.of(1998, 4, 4),
                Gender.FEMALE,
                18000.0,
                "0987654321"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Trần Văn E",
                LocalDate.of(2000, 5, 5),
                Gender.MALE,
                20000.0,
                "0123456789"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Lê Thị F",
                LocalDate.of(2002, 6, 6),
                Gender.FEMALE,
                22000.0,
                "0987654321"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Nguyễn Văn G",
                LocalDate.of(2005, 7, 7),
                Gender.MALE,
                25000.0,
                "0123456789"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Trần Thị H",
                LocalDate.of(2008, 8, 8),
                Gender.FEMALE,
                28000.0,
                "0987654321"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Lê Văn I",
                LocalDate.of(2010, 9, 9),
                Gender.MALE,
                30000.0,
                "0123456789"
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Nguyễn Thị J",
                LocalDate.of(2012, 10, 10),
                Gender.FEMALE,
                32000.0,
                "0987654321"
        ));
        return employees;
    }

    // API get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employees);
    }

    // API get one employee
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") UUID id) {
        return employees
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return ResponseEntity.ok(employee);
    }

    // API update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return employees
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    return ResponseEntity.ok(e);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // API delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") UUID id) {
        return employees
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    employees.remove(e);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
