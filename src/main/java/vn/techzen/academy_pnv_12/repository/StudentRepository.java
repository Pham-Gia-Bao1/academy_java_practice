package vn.techzen.academy_pnv_12.repository;

import org.springframework.stereotype.Repository;
import vn.techzen.academy_pnv_12.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRepository implements IStudentRepositorry {
    private final List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Nguyen Van A", 85),
            new Student(2, "Nguyen Van B", 80),
            new Student(3, "Tran Minh C", 75)
    ));
    @Override
    public List<Student> findAll() {
        return students;
    }
    @Override
    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id){
                return student;
            }
        }
        return null;
    }
    @Override
    public Student save(Student student) {
        student.setId((int)(Math.random() * students.size()));
        students.add(student);
        return student;
    }
}
