package pl.coderslab.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


//    @GetMapping("/register")
//    @ResponseBody
//    public String createUser() {
//        User user = new User();
//        user.setUsername("adminp");
//        user.setPassword("admin");
//        userService.saveUser(user);
//        return "admin";
//    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String save(User user) {
        userService.saveUser(user);
        return "user/registerDone";
    }
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String list(Model model,@AuthenticationPrincipal CurrentUser customUser) {
        model.addAttribute("user", userRepository.findByUsername(customUser.getUser().getUsername()));
        return "user/details";
    }

}