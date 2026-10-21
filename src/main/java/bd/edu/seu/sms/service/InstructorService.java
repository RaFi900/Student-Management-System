package bd.edu.seu.sms.service;

import bd.edu.seu.sms.model.Instructor;
import bd.edu.seu.sms.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor createInstructor(Instructor instructor){
        return instructorRepository.save(instructor);
    }
    public List<Instructor> findAllInstructor(){
        return instructorRepository.findAll();
    }

    public Instructor findInstructorById(String id){
        return instructorRepository.findById(id).orElse(null);
    }
    public void deleteInstructorById(String id){
        instructorRepository.deleteById(id);
    }

    public void update(Instructor updatedInstructor, String id){
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor != null) {
            instructor.setName(updatedInstructor.getName());
            instructor.setEmail(updatedInstructor.getEmail());
            instructor.setPhone(updatedInstructor.getPhone());
            instructor.setDepartment(updatedInstructor.getDepartment());


            instructorRepository.save(instructor);
        }

    }
}
