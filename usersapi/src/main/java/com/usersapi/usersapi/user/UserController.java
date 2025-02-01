package com.usersapi.usersapi.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usersapi.usersapi.util.Codif;
import com.usersapi.usersapi.util.LoginRequest;

import java.util.List;

@RestController
@RequestMapping //(path = "/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/api/v1/user")
    public List<User> getUsers(
        @RequestParam(required = false) Integer id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String username){
        if(username != null){
            return userService.getUsersByUsername(username);
        }else if(id != null){
            return userService.getUsersById(id);
        }else if(name != null){
            return userService.getUsersByName(name);
        }else{
            return userService.getUsers();
        }
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){
        String username=loginRequest.getUsername();
        String pwd=loginRequest.getPwd();
        User user = userService.getUserByUsername(username);
        if(pwd != null && username != null){
            String pass = Codif.hash(pwd);
            if(pass.equals(user.getPwd())){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        
    }


    @PostMapping("/api/v1/user")
    public ResponseEntity<User> addUser(@RequestBody User user){ //@RequestBody para JSON y @ModelAttribte para XML (forms de html)
        user.setPwd(Codif.hash(user.getPwd())); //hashear la contraseña
        user.setBirth_date(Codif.fecha_nac(user.getBirth_date())); //aplicar formato para PSQL a la fecha de nacimiento
        
        User createdUser = userService.addUser(user); //compobar si es nulo?
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User uptUser = userService.updateUser(user);
        if(uptUser != null){
            return new ResponseEntity<>(uptUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

}
