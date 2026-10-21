package bd.edu.seu.sms.repository;

import bd.edu.seu.sms.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findTop10ByOrderByTimestampDesc(); // latest 10 activities
}

