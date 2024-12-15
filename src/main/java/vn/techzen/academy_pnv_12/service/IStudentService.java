package vn.techzen.academy_pnv_12.service;

import vn.techzen.academy_pnv_12.model.Student;

import java.util.List;

public interface IStudentService {
     List<Student> findAll() ;
        void save(Student student);
     Student findById(int id) ;
}
