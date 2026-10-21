package bd.edu.seu.sms.service;

import bd.edu.seu.sms.model.User;
import bd.edu.seu.sms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findUserById(String id){
        return userRepository.findById(id).orElse(null);
    }
    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }
    public void update(User updatedUser,String id){
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            user.setRole((updatedUser.getRole()));
            user.setEmail(updatedUser.getEmail());
            user.setName(updatedUser.getName());
            user.setPassword(updatedUser.getPassword());
            user.setMobile(updatedUser.getMobile());

            userRepository.save(user);
        }
    }
}
