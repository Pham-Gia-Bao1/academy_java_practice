// IEmployeeService.java
package vn.techzen.academy_pnv_12.service;

import vn.techzen.academy_pnv_12.model.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    void save(Employee employee);
    void deleteById(int id);
}