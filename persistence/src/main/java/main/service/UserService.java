package main.service;

import main.model.UserEntity;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public void save(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    public List<UserEntity> findByQuestionnaire(String quesId){
        return userRepository.findUsersByQuestionnaire(quesId);
    }
}
