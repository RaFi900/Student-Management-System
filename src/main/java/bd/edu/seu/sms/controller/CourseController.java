package bd.edu.seu.sms.controller;

import bd.edu.seu.sms.model.Course;
import bd.edu.seu.sms.model.Instructor;
import bd.edu.seu.sms.model.Student;
import bd.edu.seu.sms.repository.CourseRepository;
import bd.edu.seu.sms.repository.InstructorRepository;
import bd.edu.seu.sms.repository.StudentRepository;
import bd.edu.seu.sms.service.CourseService;
import bd.edu.seu.sms.service.InstructorService;
import bd.edu.seu.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;

    private final InstructorService instructorService;

    private final StudentService studentService;

    public CourseController(CourseService courseService, InstructorService instructorService, StudentService studentService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }


    @GetMapping("/courses")
    public String getAllCourses(Model model) {
        List<Course> courseList = courseService.findAllcourse();
        List<Instructor> instructors = instructorService.findAllInstructor();
        List<Student> students = studentService.findAllStudent();

        model.addAttribute("courseList", courseList);
        model.addAttribute("course", new Course());
        model.addAttribute("instructors", instructors);
        model.addAttribute("students", students);

        return "course";
    }

    @PostMapping("/course")
    public String addCourse(Course course) {
        courseService.createCourse(course);
        return "redirect:/courses";
    }

    @PostMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id") String id) {
        courseService.deletecourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("/editCourse/{id}")
    public String editInstructorForm(@PathVariable("id") String id, Model model) {
        Course course = courseService.findcourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("instructorList", courseService.findAllcourse());
        model.addAttribute("showUpdateModal", true);
        return "course";
    }

    @PostMapping("/updateCourse/{id}")
    public String update(@PathVariable("id") String id,
                                   @ModelAttribute("course") Course course,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "course";
        }
        courseService.update(course, id);
        return "redirect:/courses";
    }
}
