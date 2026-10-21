package bd.edu.seu.sms.repository;

import bd.edu.seu.sms.model.CourseAdvising;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseAdvisingRepository extends MongoRepository<CourseAdvising,String> {
}
