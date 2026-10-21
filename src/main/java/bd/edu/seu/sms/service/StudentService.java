package bd.edu.seu.sms.service;

import bd.edu.seu.sms.model.Student;
import bd.edu.seu.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public List<Student> findAllStudent(){
        return studentRepository.findAll();
    }

    public Student findStudentById(String id){
        return studentRepository.findById(id).orElse(null);
    }
    public void deleteStudentById(String id){
        studentRepository.deleteById(id);
    }
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public void update(Student updatedStudent,String id){
        Student student = studentRepository.findById(id).orElse(null);
        if(student!=null){
            if (student != null) {
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                student.setMobile(updatedStudent.getMobile());
                student.setAddress(updatedStudent.getAddress());
                student.setSt_id(updatedStudent.getSt_id());
                student.setDateOfBirth(updatedStudent.getDateOfBirth());
                student.setGender(updatedStudent.getGender());
                student.setDepartment(updatedStudent.getDepartment());
                student.setBatch(updatedStudent.getBatch());

                studentRepository.save(student);
            }
        }
    }
}
