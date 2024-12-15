package vn.techzen.academy_pnv_12.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.dto.ApiResponse;
import vn.techzen.academy_pnv_12.dto.JsonResponse;
import vn.techzen.academy_pnv_12.exception.AppException;
import vn.techzen.academy_pnv_12.exception.ErrorCode;
import vn.techzen.academy_pnv_12.model.Department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Quản lý"),
                    new Department(2, "Kế toán"),
                    new Department(3, "Sale Marketing"),
                    new Department(4, "Sản xuất")
            )
    );

    @GetMapping
    public ResponseEntity<?> getDepartments() {
        return JsonResponse.ok(departments);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id) {
        return departments.stream().filter(e -> e.getId() == id).findFirst().map(JsonResponse::ok).orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));

    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        department.setId(departments.size() + 1);
        departments.add(department);
        return JsonResponse.created(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") int id, @RequestBody Department department) {
        return departments.stream().filter(e -> e.getId().equals(id)).findFirst().map(e -> {
            e.setName(department.getName());
            return JsonResponse.ok(e);
        }).orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") int id) {
        return departments.stream().filter(e -> e.getId() == id).findFirst().map(e -> {
            departments.remove(e);
            return JsonResponse.ok(e);
        }).orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

}
