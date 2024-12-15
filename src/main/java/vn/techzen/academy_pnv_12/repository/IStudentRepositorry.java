package vn.techzen.academy_pnv_12.repository;

import vn.techzen.academy_pnv_12.model.Student;

import java.util.List;

public interface IStudentRepositorry {
      List<Student> findAll();
      Student findById(int id);
      Student save(Student student);
}
