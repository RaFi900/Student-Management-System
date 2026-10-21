package bd.edu.seu.sms.controller;

import bd.edu.seu.sms.model.Course;
import bd.edu.seu.sms.model.Instructor;
import bd.edu.seu.sms.service.CourseService;
import bd.edu.seu.sms.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InstructorController {

    private final InstructorService instructorService;
    private final CourseService courseService;

    public InstructorController(InstructorService instructorService, CourseService courseService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }


    @GetMapping("/instructors")
    public String getAllInstructors(Model model) {
        List<Instructor> instructorList = instructorService.findAllInstructor();
        List<Course> courses = courseService.findAllcourse();

        model.addAttribute("instructorList", instructorList);
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("courses", courses);

        return "instructor";
    }

    @PostMapping("/instructors")
    public String addInstructor(Instructor instructor) {
        instructorService.createInstructor(instructor);
        return "redirect:/instructors";
    }

    @PostMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable String id) {
        instructorService.deleteInstructorById(id);
        return "redirect:/instructors";
    }

    @GetMapping("/instructors/edit/{id}")
    public String editInstructorForm(@PathVariable("id") String id, Model model) {
        Instructor instructor = instructorService.findInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("instructorList", instructorService.findAllInstructor());
        model.addAttribute("showUpdateModal", true);
        return "instructor";
    }

    @PostMapping("/instructors/update/{id}")
    public String updateInstructor(@PathVariable("id") String id,
                                   @ModelAttribute("instructor") Instructor instructor,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "instructor";
        }
        instructorService.update(instructor, id);
        return "redirect:/instructors";
    }
}
