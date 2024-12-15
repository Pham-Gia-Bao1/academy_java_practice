package vn.techzen.academy_pnv_12.repository;

import vn.techzen.academy_pnv_12.model.Employee;
import java.util.List;

public interface IEmployeeRepository {
    List<Employee> findAll();
    Employee findById(int id);
    void save(Employee employee);
    void deleteById(int id);
}