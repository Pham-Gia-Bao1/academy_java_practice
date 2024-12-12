package vn.techzen.academy_pnv_12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.academy_pnv_12.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Nguyen Van A", 85),
            new Student(2, "Nguyen Van B", 80),
            new Student(3, "Tran Minh C", 75)
    ));

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

            return ResponseEntity.ok(this.students);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student.setId((int) (Math.random() * 100000 ));
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

}
