package tr.com.sule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.sule.dao.IStudentDao;
import tr.com.sule.domain.Student;

import java.util.List;

/**
 * @author scinkir 10.10.2018
 */
@Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentDao studentDao;

    //@PersistenceContext(unitName = "secondary")
    // private EntityManager entityManager;

    //@Transactional(value = "secondaryTransactionManager")
    @Transactional
    public long insert(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentDao.getAllStudent();

    }
}
