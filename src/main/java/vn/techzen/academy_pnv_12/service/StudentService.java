package vn.techzen.academy_pnv_12.service;

import vn.techzen.academy_pnv_12.model.Student;
import vn.techzen.academy_pnv_12.repository.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    // Constructor injection để tiêm StudentRepository
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student); // Gọi phương thức lưu sinh viên từ repository
    }
}
