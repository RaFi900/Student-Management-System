package bd.edu.seu.sms.controller;

import bd.edu.seu.sms.model.Course;
import bd.edu.seu.sms.model.CourseAdvising;
import bd.edu.seu.sms.model.Instructor;
import bd.edu.seu.sms.model.Student;
import bd.edu.seu.sms.service.CourseAdvisingService;
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
public class CourseAdvisingController {


        private final CourseAdvisingService courseAdvisingService;
        private final StudentService studentService;
        private final InstructorService instructorService;
        private final CourseService courseService;

        public CourseAdvisingController(CourseAdvisingService courseAdvisingService,
                                        StudentService studentService,
                                        InstructorService instructorService, CourseService courseService) {
            this.courseAdvisingService = courseAdvisingService;
            this.studentService = studentService;
            this.instructorService = instructorService;
            this.courseService = courseService;
        }

        // Show all course advisings
        @GetMapping("/course_advising")
        public String getAllCourseAdvising(Model model) {
            List<CourseAdvising> advisingList = courseAdvisingService.findAllCourseAdvising();
            List<Student> students = studentService.findAllStudent();
            List<Instructor> instructors = instructorService.findAllInstructor();
            List<Course> advisedCourses = courseService.findAllcourse();

            model.addAttribute("advisingList", advisingList);
            model.addAttribute("courseAdvising", new CourseAdvising());
            model.addAttribute("students", students);
            model.addAttribute("instructors", instructors);
            model.addAttribute("advisedCourses", advisedCourses);

            return "course_advising";
        }


        @PostMapping("/course-advising")
        public String addCourseAdvising(@ModelAttribute("courseAdvising") CourseAdvising courseAdvising) {
            courseAdvisingService.createCourseAdvising(courseAdvising);
            return "redirect:/course_advising";
        }


        @PostMapping("/deleteCourseAdvising/{id}")
        public String deleteCourseAdvising(@PathVariable("id") String id) {
            courseAdvisingService.deleteCourseAdvisingById(id);
            return "redirect:/course_advising";
        }

        @GetMapping("/editCourseAdvising/{id}")
        public String editCourseAdvisingForm(@PathVariable("id") String id, Model model) {
            CourseAdvising advising = courseAdvisingService.findCourseAdvisingById(id);
            model.addAttribute("courseAdvising", advising);
            model.addAttribute("advisingList", courseAdvisingService.findAllCourseAdvising());
            model.addAttribute("students", studentService.findAllStudent());
            model.addAttribute("instructors", instructorService.findAllInstructor());
            model.addAttribute("showUpdateModal", true);
            return "course_advising";
        }


    @PostMapping("/update-course-advising/{id}")
    public String updateCourseAdvising(@PathVariable("id") String id,
                                       @ModelAttribute("courseAdvising") CourseAdvising advising,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "course_advising";
        }
        courseAdvisingService.update(advising, id);
        return "redirect:/course-advising";
    }



}
