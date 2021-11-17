package de.dueto.backend.controller;

import de.dueto.backend.model.User;
import de.dueto.backend.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/")
public class MainController {
    //@Autowired // get the bean called userRepository
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }


    @PostMapping(path="/add") // Map POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setUserName(name);
        n.setEmail(email);
        mainService.saveUser(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return mainService.getALl();
    }
}