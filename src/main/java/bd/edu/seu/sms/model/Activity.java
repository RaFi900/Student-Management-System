package bd.edu.seu.sms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "activities")
public class Activity {
    @Id
    private String id;
    private String description;
    private LocalDateTime timestamp;

    public Activity() {}

    public Activity(String description) {
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    // getters and setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}