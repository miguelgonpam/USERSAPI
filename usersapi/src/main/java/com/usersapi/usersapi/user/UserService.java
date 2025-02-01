package com.usersapi.usersapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersById(Integer id){
        return userRepository.findAll().stream()
            .filter(user -> id == user.getId())
            .collect(Collectors.toList());
    }

    public List<User> getUsersByName(String name){
        return userRepository.findAll().stream()
            .filter(user -> name.equals(user.getName()))
            .collect(Collectors.toList());
    }

    public List<User> getUsersByUsername(String username){
        return userRepository.findAll().stream()
            .filter(user -> username.equals(user.getUsername()))
            .collect(Collectors.toList());
    }

    public User getUserByUsername(String username){
        List<User>lis = userRepository.findAll().stream()
            .filter(user -> username.equals(user.getUsername()))
            .collect(Collectors.toList());
        if(lis.size()!= 0){
            return lis.getFirst();
        }else{
            return null;
        }
        
    }

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    public User updateUser(User newuser){
        Optional<User> old_user = userRepository.findById(newuser.getId());

        if (old_user.isPresent()){
            User userToupdate = old_user.get();
            userToupdate.setId(newuser.getId());
            userToupdate.setName(newuser.getName());
            userToupdate.setSurnames(newuser.getSurnames());
            userToupdate.setUsername(newuser.getUsername());
            userToupdate.setBirth_date(newuser.getBirth_date());
            userToupdate.setPwd(newuser.getPwd());
            userToupdate.setOld_pwd(newuser.getOld_pwd());
            
            userRepository.save(userToupdate);
            return userToupdate;
        }
        return null;
    }

    @Transactional
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
