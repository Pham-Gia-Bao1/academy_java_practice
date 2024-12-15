package vn.techzen.academy_pnv_12.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.dto.JsonResponse;
import vn.techzen.academy_pnv_12.exception.AppException;
import vn.techzen.academy_pnv_12.exception.ErrorCode;
import vn.techzen.academy_pnv_12.model.Employee;
import vn.techzen.academy_pnv_12.model.Gender;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
                "0123456789",
                UUID.randomUUID().toString() // Sample departmentId
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Trần Thị B",
                LocalDate.of(1992, 2, 2),
                Gender.FEMALE,
                12000.0,
                "0987654321",
                UUID.randomUUID().toString()
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Lê Văn C",
                LocalDate.of(1995, 3, 3),
                Gender.MALE,
                15000.0,
                "0123456789",
                UUID.randomUUID().toString()
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Nguyễn Thị D",
                LocalDate.of(1998, 4, 4),
                Gender.FEMALE,
                18000.0,
                "0987654321",
                UUID.randomUUID().toString()
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Trần Văn E",
                LocalDate.of(2000, 5, 5),
                Gender.MALE,
                20000.0,
                "0123456789",
                UUID.randomUUID().toString()
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Lê Thị F",
                LocalDate.of(2002, 6, 6),
                Gender.FEMALE,
                22000.0,
                "0987654321",
                UUID.randomUUID().toString()
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Nguyễn Văn G",
                LocalDate.of(2005, 7, 7),
                Gender.MALE,
                25000.0,
                "0123456789",
                UUID.randomUUID().toString()
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Trần Thị H",
                LocalDate.of(2008, 8, 8),
                Gender.FEMALE,
                28000.0,
                "0987654321",
                UUID.randomUUID().toString()
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Lê Văn I",
                LocalDate.of(2010, 9, 9),
                Gender.MALE,
                30000.0,
                "0123456789",
                UUID.randomUUID().toString()
        ));
        employees.add(new Employee(
                UUID.randomUUID(),
                "Nguyễn Thị J",
                LocalDate.of(2012, 10, 10),
                Gender.FEMALE,
                32000.0,
                "0987654321",
                UUID.randomUUID().toString()
        ));
        return employees;
    }
    @GetMapping
    public ResponseEntity<?> getEmployees(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "dobFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(value = "dobTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(value = "gender", required = false) Gender gender,
            @RequestParam(value = "salaryRange", required = false, defaultValue = "") String salaryRange,
            @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
            @RequestParam(value = "departmentId", required = false, defaultValue = "") String departmentId
    ) {
        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> name.isEmpty() || e.getName().trim().toLowerCase().contains(name.toLowerCase()))
                .filter(e -> dobFrom == null || !e.getDob().isBefore(dobFrom))
                .filter(e -> dobTo == null || !e.getDob().isAfter(dobTo))
                .filter(e -> gender == null || e.getGender() == gender)
                .filter(e -> phone.isEmpty() || e.getPhone().contains(phone))
                .filter(e -> departmentId.isEmpty() || e.getDepartmentId().equals(departmentId))
                .filter(e -> {
                    if (salaryRange.isEmpty()) return true;
                    return switch (salaryRange) {
                        case "lt5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() <= 10000000;
                        case "10-20" -> e.getSalary() > 10000000 && e.getSalary() <= 20000000;
                        case "gt20" -> e.getSalary() > 20000000;
                        default -> true;
                    };
                })
                .toList();

        return JsonResponse.ok(filteredEmployees);
    }
    // API to get details of a single employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") UUID id) {
        return employees
                .stream()
                .filter(e -> e.getId().equals(id)) // Find the employee with the matching ID
                .findFirst()
                .map(JsonResponse::ok) // Wrap the employee in a successful JsonResponse
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST)); // Throw an error if not found
    }
    // API create a new employee
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return JsonResponse.created(employee);
    }
    // API to update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        // Validate input
        if (employee == null) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
        // Update employee
        Employee updatedEmployee = employees
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName() != null ? employee.getName() : e.getName());
                    e.setDob(employee.getDob() != null ? employee.getDob() : e.getDob());
                    e.setGender(employee.getGender() != null ? employee.getGender() : e.getGender());
                    e.setSalary(employee.getSalary() != null ? employee.getSalary() : e.getSalary());
                    e.setPhone(employee.getPhone() != null ? employee.getPhone() : e.getPhone());
                    return e;
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));

        // Return response
        return ResponseEntity.ok(updatedEmployee);
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
                    return JsonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }
}