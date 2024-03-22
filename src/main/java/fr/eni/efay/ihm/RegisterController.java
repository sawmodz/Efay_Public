package fr.eni.efay.ihm;

import fr.eni.efay.bll.interfaces.RegisterService;
import fr.eni.efay.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes({"currentUser"})
public class RegisterController {

    @ModelAttribute("currentUser")
    public User chargerMembre() {
        return new User();
    }

    @Autowired
    RegisterService registerService ;

    @GetMapping("/register")
    public String registrationForm(@ModelAttribute(name = "currentUser") User user) {
        if(!user.equals(new User())) return "redirect:/main";
        return "view-register";
    }

    @PostMapping("/register")
    public String registrationFormPost(User user) {
        user.setRole("USER");
        user.setSold(0l);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        registerService.insert(user);

        return "redirect:/main";
    }
    @RequestMapping("/register-error")
    public String registerError(Model model) {
        model.addAttribute("registerError", true);
        return "error";
    }
}
