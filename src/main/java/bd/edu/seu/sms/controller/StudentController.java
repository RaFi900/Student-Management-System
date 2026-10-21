package bd.edu.seu.sms.controller;

import bd.edu.seu.sms.model.Gender;
import bd.edu.seu.sms.model.Role;
import bd.edu.seu.sms.model.Student;
import bd.edu.seu.sms.model.User;
import bd.edu.seu.sms.service.MailService;
import bd.edu.seu.sms.service.StudentService;
import bd.edu.seu.sms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final UserService userService;
    private final MailService mailService;

    public StudentController(StudentService studentService, UserService userService, MailService mailService) {
        this.studentService = studentService;
        this.userService = userService;
        this.mailService = mailService;
    }


    @GetMapping("/students")
    public String getStudent(Model model){
        List<Student> StudentsList = studentService.findAllStudent();
        model.addAttribute("studentList", StudentsList);
        model.addAttribute("students", new Student());
        model.addAttribute("Gender", Gender.values());
        return "student";
    }

    @PostMapping("/students")
    public String submitStudents(Student students,Model model) {
        Optional<Student> clientOptional = studentService.findByEmail(students.getEmail());
        if (clientOptional.isPresent()) {
            model.addAttribute("emailError", "Email already exists.");
            model.addAttribute("studentList", studentService.findAllStudent());
            model.addAttribute("students", students);
            return "student";
        }
        if (!students.getMobile().startsWith("01")||students.getMobile().chars().count()!=11) {
            model.addAttribute("error", "Invalid Phone Number");
            return "student";
        }

        String generatedPassword = generateRandomPassword();
        students.setPassword(generatedPassword);

        List<String> roleList = new ArrayList<>();
        roleList.add(Role.ROLE_STUDENT.toString());
        students.setRole(roleList);

        User user = User.fromStudentUser(students);
        userService.createUser(user);
        studentService.createStudent(students);

        mailService.sendPasswordToStudent(students.getEmail(), students.getName(), generatedPassword);

        return "redirect:/students";
    }


    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute("Student")  Student Student, BindingResult result) {
        if (result.hasErrors()) {
            return "student";
        }
        studentService.update(Student,id);
        return "redirect:/students";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudentForm(@PathVariable("id") String id, Model model) {
        Student Student = studentService.findStudentById(id);
        model.addAttribute("students", Student);
        model.addAttribute("studentList",studentService.findAllStudent());
        model.addAttribute("showUpdateModal", true);
        model.addAttribute("genders", Gender.values());
        return "student";
    }

    @PostMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    private String generateRandomPassword() {
        int length = 8;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }
}
