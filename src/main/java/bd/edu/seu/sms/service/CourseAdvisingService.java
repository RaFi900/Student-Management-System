package bd.edu.seu.sms.service;

import bd.edu.seu.sms.model.CourseAdvising ;
import bd.edu.seu.sms.repository.CourseAdvisingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseAdvisingService {
    private final CourseAdvisingRepository CourseAdvisingRepository;

    public CourseAdvisingService(CourseAdvisingRepository CourseAdvisingRepository) {
        this.CourseAdvisingRepository = CourseAdvisingRepository;
    }

    public CourseAdvising createCourseAdvising(CourseAdvising CourseAdvising){
        return CourseAdvisingRepository.save(CourseAdvising);
    }
    public List<CourseAdvising> findAllCourseAdvising(){
        return CourseAdvisingRepository.findAll();
    }

    public CourseAdvising findCourseAdvisingById(String id){
        return CourseAdvisingRepository.findById(id).orElse(null);
    }
    public void deleteCourseAdvisingById(String id){
        CourseAdvisingRepository.deleteById(id);
    }

    public CourseAdvising update(CourseAdvising advising, String id){
        CourseAdvising existing = CourseAdvisingRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setStudent(advising.getStudent());
            existing.setAdvisedCourses(advising.getAdvisedCourses());
            existing.setInstructor(advising.getInstructor());
            existing.setAdvisingDate(advising.getAdvisingDate());
            existing.setApproved(advising.isApproved());

            return CourseAdvisingRepository.save(existing);
        } else {

            throw new RuntimeException("Course Advising record not found with id: " + id);
        }
    }
}
