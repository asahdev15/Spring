package service;

import domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("userService")
public class UserService {

    @Value("${abc.boolCheck}")
    private boolean checkBool;

    private static final AtomicLong counter = new AtomicLong();
     
    private static List<User> users;
     
    static{
        users = populateDummyUsers();
    }

    private static List<User> populateDummyUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(counter.incrementAndGet(),"Sam",30, 70000, "sam@email.com", "sam address"));
        users.add(new User(counter.incrementAndGet(),"Tom",40, 50000, "Tom@email.com", "Tom address"));
        users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000, "Jerome@email.com", "Jerome address"));
        users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000, "Silvia@email.com", "Silvia address"));
        return users;
    }

    public List<User> findAllUsers() {
        System.out.println("---- checkBool :" + checkBool);
        return users;
    }
     
    public User findById(long id) {
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
     
    public User findByName(String name) {
        for(User user : users){
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }
     
    public void saveUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }
 
    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }
 
    public void deleteUserById(long id) {
         
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    public boolean isUserExist(User user) {
        return findByName(user.getName())!=null;
    }
 

 
    public void deleteAllUsers() {
        users.clear();
    }
 
}