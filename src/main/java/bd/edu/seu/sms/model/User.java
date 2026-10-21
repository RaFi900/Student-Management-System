package bd.edu.seu.sms.model;
import bd.edu.seu.sms.audit.AuditableDocument;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


public class User extends AuditableDocument {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
    private String mobile;
    private List<String> role;

    public User(String id, String name, String email, String password, String mobile, List<String> role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.role = role;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public static User fromStudentUser(Student student) {
        return new User(
                null,
                student.getName(),
                student.getEmail(),
                student.getPassword(),
                student.getMobile(),
                student.getRole()
        );
    }
}
