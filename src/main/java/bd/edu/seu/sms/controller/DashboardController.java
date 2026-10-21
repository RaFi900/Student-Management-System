package bd.edu.seu.sms.controller;

import bd.edu.seu.sms.model.Activity;
import bd.edu.seu.sms.repository.ActivityRepository;
import bd.edu.seu.sms.repository.CourseAdvisingRepository;
import bd.edu.seu.sms.repository.InstructorRepository;
import bd.edu.seu.sms.repository.StudentRepository;
import bd.edu.seu.sms.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final CourseAdvisingRepository courseAdvisingRepository;
    private final ActivityRepository activityRepository;

    public DashboardController(DashboardService dashboardService, StudentRepository studentRepository, InstructorRepository instructorRepository, CourseAdvisingRepository courseAdvisingRepository, ActivityRepository activityRepository) {
        this.dashboardService = dashboardService;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.courseAdvisingRepository = courseAdvisingRepository;
        this.activityRepository = activityRepository;

    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        model.addAttribute("totalStudents", dashboardService.getTotalStudents());
        model.addAttribute("totalInstructors", dashboardService.getTotalInstructors());
        model.addAttribute("totalCourses", dashboardService.getTotalCourses());
        List<Map<String, Object>> dataPoints = new ArrayList<>();

        Map<String, Object> studentData = new HashMap<>();
        studentData.put("category", "Students");
        studentData.put("value", studentRepository.count());
        dataPoints.add(studentData);

        Map<String, Object> instructorData = new HashMap<>();
        instructorData.put("category", "Instructors");
        instructorData.put("value", instructorRepository.count());
        dataPoints.add(instructorData);
        model.addAttribute("dataPoints", dataPoints);
        List<Activity> recentActivityEntities = activityRepository.findTop10ByOrderByTimestampDesc();
        List<String> recentActivities = recentActivityEntities.stream()
                .map(Activity::getDescription)
                .collect(Collectors.toList());

        model.addAttribute("recentActivities", recentActivities);
        model.addAttribute("totalAdvising", courseAdvisingRepository.count());

        return "/dashboard";
    }


}
