package bd.edu.seu.sms.repository;

import bd.edu.seu.sms.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    Optional<Student> findByEmail(String email);

}
