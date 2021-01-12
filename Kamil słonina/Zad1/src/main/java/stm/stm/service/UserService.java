package stm.stm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stm.stm.entity.Task;
import stm.stm.entity.User;
import stm.stm.repozytorium.TaskRepository;
import stm.stm.repozytorium.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;


    public User insertUser(User user) {
        return userRepository.save(user);
    }


    public List<User> selectUsers() {
        return userRepository.findAll();
    }


    public List<User> getUserByEmailOrId(Integer userId,String email) {
        return userRepository.findByUserIdOrEmail(userId,email);
    }

    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    public boolean activateUser(int userId){

        Optional<User> userOptional = getUserById(userId);
        if(userOptional.isPresent()){
            User userToActivate = userOptional.get();
            if(userToActivate.getStatus()){
                userToActivate.setStatus(false);
            }else{
                userToActivate.setStatus(true);
            }
            userRepository.save(userToActivate);
        }
        return userOptional.get().getStatus();

    }


    public boolean deleteUserById(int userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            for (Task task : taskRepository.findTasksByUserId(userId)) {
                taskRepository.delete(task);
            }
            userRepository.delete(optionalUser.get());
            return true;
        }
        return false;
    }

}
