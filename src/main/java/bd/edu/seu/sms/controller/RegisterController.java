package bd.edu.seu.sms.controller;

import bd.edu.seu.sms.model.Role;
import bd.edu.seu.sms.model.User;
import bd.edu.seu.sms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RegisterController {
 private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }
   @GetMapping("/register")
    public String showRegisterPage(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email is already registered!");
            return "register";
        }
        if (!user.getMobile().startsWith("01")||user.getMobile().chars().count()!=11) {
            model.addAttribute("error", "Invalid Phone Number");
            return "register";
        }
        List<String> roleList =new ArrayList<>();
        roleList.add(Role.ROLE_ADMIN.toString());

        user.setRole(roleList);
        userService.createUser(user);
        return "register";
    }
}
