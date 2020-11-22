package be.teachngo.controller;

import be.teachngo.data.User;
import be.teachngo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/newUser")
    public User getUsers(@RequestParam("token") String token, @RequestParam("login") String login) {
        User user = userRepository.findByLogin(login);
        if (token != null
                && user != null
                && !user.isActive()
                && user.getToken().equals(token)) {

            user.setActive(true);
            user.setToken(null);// it will be not possible to activate it again with the previous token
            user = userRepository.save(user);
        }
        user.setPassword("XXXXX");
        return user;
    }
}
