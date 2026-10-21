package bd.edu.seu.sms.service;
import bd.edu.seu.sms.repository.CourseRepository;
import bd.edu.seu.sms.repository.InstructorRepository;
import bd.edu.seu.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {


    private final StudentRepository studentRepository;

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;


    public DashboardService(StudentRepository studentRepository, InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;

    }

    public long getTotalStudents() {
        return studentRepository.count();
    }

    public long getTotalInstructors() {
        return instructorRepository.count();
    }
    public long getTotalCourses() {
        return courseRepository.count();
    }
}

