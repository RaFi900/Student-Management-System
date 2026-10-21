package bd.edu.seu.sms.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordToStudent(String toEmail, String studentName, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Student Portal Password");
        message.setText("Hello " + studentName + ",\n\n"
                + "Welcome to our platform! We're excited to have you on board.\n\n"
                + "Here are your login details:\n"
                + "Password: " + password + "\n\n"
                + "Please keep this information secure. If you face any issues or need help, feel free to reach out to our support team.\n\n"
                + "Wishing you a great learning experience!\n"
                + "Best regards,\n"
                + "Your Academic Support Team");

        mailSender.send(message);
    }
}
