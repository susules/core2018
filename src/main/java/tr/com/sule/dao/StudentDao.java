package tr.com.sule.dao;

import org.springframework.stereotype.Repository;
import tr.com.sule.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author scinkir 10.10.2018
 */
@Repository
//Spring Annotation to indicate that this component handles storing data to a data store
public class StudentDao implements IStudentDao {

    /*@PersistenceContext: A persistence context handles a set of entities which hold data to be persisted
    in some persistence store (e.g. a database). In particular, the context is aware of the different states
     an entity can have     (e.g. managed, detached) in relation to both the context and the underlying persistence store.
     */
    //EntityManager Interface used to interact with the persistence context
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Student getStudentById(int studentId) {
        return entityManager.find(Student.class, studentId);
    }

    @Override
    public long insert(Student student) {
        entityManager.persist(student);
        return student.getId();
    }

    @Override
    public List<Student> getAllStudent() {
        String hql = "FROM Student as s ORDER BY s.id";
        return (List<Student>) entityManager.createQuery(hql).getResultList();
    }
}

