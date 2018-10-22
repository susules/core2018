package tr.com.sule.dao;

import tr.com.sule.domain.Student;

import java.util.List;

/**
 * @author scinkir 10.10.2018
 */
public interface IStudentDao {

    Student getStudentById(int studentId);

    long insert(Student student);

    List<Student> getAllStudent();
}
