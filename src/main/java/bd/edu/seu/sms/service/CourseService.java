package bd.edu.seu.sms.service;

import bd.edu.seu.sms.model.Course;

import bd.edu.seu.sms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course){
        return courseRepository.save(course);
    }
    public List<Course> findAllcourse(){
        return courseRepository.findAll();
    }

    public Course findcourseById(String id){
        return courseRepository.findById(id).orElse(null);
    }
    public void deletecourseById(String id){
        courseRepository.deleteById(id);
    }

    public void update(Course updatedCourse,String id){
       Course course = courseRepository.findById(id).orElse(null);
        if(course!=null){

            if (course != null) {
                course.setCourseCode(updatedCourse.getCourseCode());
                course.setCourseName(updatedCourse.getCourseName());
                course.setDescription(updatedCourse.getDescription());
                course.setCredits(updatedCourse.getCredits());
                course.setDepartment(updatedCourse.getDepartment());

                courseRepository.save(course);
            }

        }
    }
}
