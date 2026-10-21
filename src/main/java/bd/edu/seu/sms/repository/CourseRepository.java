package bd.edu.seu.sms.repository;

import bd.edu.seu.sms.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
}
