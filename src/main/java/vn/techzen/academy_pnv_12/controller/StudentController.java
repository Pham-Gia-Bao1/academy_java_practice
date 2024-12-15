package vn.techzen.academy_pnv_12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.dto.ApiResponse;
import vn.techzen.academy_pnv_12.exception.AppException;
import vn.techzen.academy_pnv_12.exception.ErrorCode;
import vn.techzen.academy_pnv_12.model.Student;
import vn.techzen.academy_pnv_12.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor injection
    public StudentController() {
        this(null);
    }

    // Constructor injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student != null && student.getId() == id) {
            return ResponseEntity.ok(ApiResponse.<Student>builder().data(student).build());
        }
        throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student.setId((int) (Math.random() * 100000));
        studentService.save(student); // Sử dụng phương thức `save` thay vì thêm trực tiếp vào danh sách
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
}
