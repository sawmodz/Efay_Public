package fr.eni.efay.ihm;

import fr.eni.efay.bo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"currentUser"})
public class LoginController {

	@ModelAttribute("currentUser")
	public User chargerMembre() {
		return new User();
	}

	@GetMapping("/login")
	public String login(@ModelAttribute(name = "currentUser") User user) {
		if(!user.equals(new User())) return "redirect:/main";
		return "view-login";
	}
	
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "error";
	}
	
}
