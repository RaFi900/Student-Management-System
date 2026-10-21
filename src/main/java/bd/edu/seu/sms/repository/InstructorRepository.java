package bd.edu.seu.sms.repository;

import bd.edu.seu.sms.model.Instructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends MongoRepository<Instructor ,String> {
}
