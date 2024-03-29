package me.mirsowasvonegal.serverpanel.RestAPI.controller;

import me.mirsowasvonegal.serverpanel.RestAPI.model.Status;
import me.mirsowasvonegal.serverpanel.RestAPI.model.User;
import me.mirsowasvonegal.serverpanel.RestAPI.repository.UserRepository;
import me.mirsowasvonegal.serverpanel.RestAPI.utils.MD5;
import me.mirsowasvonegal.serverpanel.RestAPI.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/system")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/user")
    public Object addUser(@RequestBody User user) {
            if (user.getUsername() == null) return new Status("Bitte gebe einen Nutzernamen ein!", 500);
            if (user.getEmail() == null) return new Status("Bitte gebe eine E-Mail ein!", 500);
            if (user.getPassword() == null) return new Status("Bitte gebe ein Password ein!", 500);
            if (user.getUsername().split("").length <= 3)
                return new Status("Bitte gebe einen Nutzernamen mit min. 4 Zeichen ein!", 500);
            if (user.getEmail().split("").length <= 5)
                return new Status("Bitte gebe eine E-Mail mit min. 6 Zeichen ein!", 500);
            if (user.getPassword().split("").length <= 7)
                return new Status("Bitte gebe ein Password mit min. 8 Zeichen ein!", 500);
            if (repository.existsByUsername(user.getUsername()))
                return new Status("Dieser Nutzername ist bereits vergeben!", 500);
            if (repository.existsByEmail(user.getEmail())) return new Status("Diese E-Mail ist bereits vergeben!", 500);
            if (user.getRankname() == null) user.setRankname("Customer");
            if (user.getCredits() == null) user.setCredits(0.0);
            user.setCreated(System.currentTimeMillis());
            user.setPassword(MD5.hash(user.getPassword()));
            user.setSupportid(RandomString.generateInt(8));
            user.setRankid(1);
            user.setConfirmed(RandomString.generate(32));
            repository.save(user);
            return user;
    }

    @PutMapping("/user/{id}")
    public Object updateUser(@RequestBody User user, @PathVariable String id) {
        if (user.getUsername() != null && user.getUsername().split("").length <= 3) return new Status("Bitte gebe einen Nutzernamen mit min. 4 Zeichen ein!", 500);
        if (user.getEmail() != null && user.getEmail().split("").length <= 5) return new Status("Bitte gebe eine E-Mail mit min. 6 Zeichen ein!", 500);
        if (user.getPassword() != null && user.getPassword().split("").length <= 7) return new Status("Bitte gebe ein Password mit min. 8 Zeichen ein!", 500);
        User oldUser = repository.findUserById(id).get(0);
        if(repository.existsByUsername(user.getUsername()) && (!repository.findUserByUsername(user.getUsername()).get(0).equals(oldUser)))
            return new Status("Dieser Nutzername ist bereits vergeben!", 500);
        if(repository.existsByEmail(user.getEmail()) && (!repository.findUserByEmail(user.getEmail()).get(0).equals(oldUser)))
            return new Status("Diese E-Mail ist bereits vergeben!", 500);
        if (user.getUsername() != null) oldUser.setUsername(user.getUsername());
        if (user.getEmail() != null) oldUser.setEmail(user.getEmail());
        if (user.getPassword() != null) oldUser.setPassword(MD5.hash(user.getPassword()));
        if (user.getRankname() != null) oldUser.setRankname(user.getRankname());
        if (user.getSupportid() != null) oldUser.setSupportid(user.getSupportid());
        if (user.getCredits() != null) oldUser.setCredits(user.getCredits());
        if (user.getRankid() != 0) oldUser.setRankid(user.getRankid());
        if (user.getCity() != null) oldUser.setCity(user.getCity());
        if (user.getCountry() != null) oldUser.setCountry(user.getCountry());
        if (user.getFirstname() != null) oldUser.setFirstname(user.getFirstname());
        if (user.getLastname() != null) oldUser.setLastname(user.getLastname());
        if (user.getHousenumber() != null) oldUser.setHousenumber(user.getHousenumber());
        if (user.getStreet() != null) oldUser.setStreet(user.getStreet());
        if (user.getZipcode() != null) oldUser.setZipcode(user.getZipcode());
        repository.save(oldUser);
        return oldUser;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        return repository.findUserById(id).get(0);
    }

    @GetMapping("/getUserByUsername/{username}")
    public List<User> getUserByUsername(@PathVariable String username) {
        return repository.findUserByUsername(username);
    }

    @GetMapping("/getUserByEmail/{email}")
    public List<User> getUserByEmail(@PathVariable String email) {
        return repository.findUserByEmail(email);
    }

    @GetMapping("/user")
    public List<User> getUser() {
        return repository.findAll();
    }
}
