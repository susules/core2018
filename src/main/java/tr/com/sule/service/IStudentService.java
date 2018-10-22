package tr.com.sule.service;

import tr.com.sule.domain.Student;

import java.util.List;

/**
 * @author scinkir 10.10.2018
 */
public interface IStudentService {

    long insert(Student student);

    Student getStudentById(int studentId);

    List<Student> getAllStudent();


}
