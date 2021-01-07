package rest;

import domain.CustomErrorType;
import domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.UserService;

import java.util.List;

@Slf4j
@RestController
public class RestWSUser {

//    @RequestParam(required = false, defaultValue = RestConstants.DEFAULT_LIMIT) String limit


    @Autowired
    UserService userService;

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity(new CustomErrorType("User with id " + id+ " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/user/")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        if (userService.isUserExist(user)) {
            return new ResponseEntity(new CustomErrorType(
                    "Unable to create. A User with name " + user.getName() + " already exist."),
                    HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User currentUser = userService.findById(id);
        if (currentUser==null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/user/")
    public ResponseEntity<User> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}