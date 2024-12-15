// EmployeeRepository.java
package vn.techzen.academy_pnv_12.repository;

import org.springframework.stereotype.Repository;
import vn.techzen.academy_pnv_12.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    @Override
    public Employee findById(int id) {
        // If your id is UUID, you need to handle the conversion
        return employees.stream()
                .filter(employee -> employee.getId().equals(UUID.fromString(String.valueOf(id))))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void deleteById(int id) {
        employees.removeIf(employee -> employee.getId().equals(UUID.fromString(String.valueOf(id))));
    }
}