package fr.eni.efay.ihm;

import fr.eni.efay.bll.interfaces.ProductService;
import fr.eni.efay.bll.interfaces.UserService;
import fr.eni.efay.bo.State;
import fr.eni.efay.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"currentUser"})
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("/user/give/{pseudo}")
    public String give(@PathVariable(value = "pseudo") String pseudo, @ModelAttribute("amount") String amount, @ModelAttribute(name = "currentUser") User user) {
        if(!user.getRole().equals("ADMIN")) return "redirect:/user/" + pseudo;

        User userToGive = userService.findByPseudo(pseudo);
        if(new User().equals(userToGive)) return "redirect:/user/" + pseudo;

        userService.addMoney(userToGive, Integer.parseInt(amount));

        return "redirect:/user/" + pseudo;
    }

    @GetMapping("/user/admin/remove/{pseudo}")
    public String removeAdmin(@PathVariable(value = "pseudo") String pseudo, @ModelAttribute("amount") String amount, @ModelAttribute(name = "currentUser") User user) {
        if(!user.getRole().equals("ADMIN")) return "redirect:/user/" + pseudo;

        userService.changeRole(pseudo, "USER");

        return "redirect:/user/" + pseudo;
    }

    @GetMapping("/user/admin/add/{pseudo}")
    public String giveAdmin(@PathVariable(value = "pseudo") String pseudo, @ModelAttribute("amount") String amount, @ModelAttribute(name = "currentUser") User user) {
        if(!user.getRole().equals("ADMIN")) return "redirect:/user/" + pseudo;

        userService.changeRole(pseudo, "ADMIN");

        return "redirect:/user/" + pseudo;
    }

    @GetMapping("/user/remove/{pseudo}")
    public String remove(@PathVariable(value = "pseudo") String pseudo, @ModelAttribute("amount") String amount, @ModelAttribute(name = "currentUser") User user) {
        if(!user.getRole().equals("ADMIN")) return "redirect:/user/" + pseudo;

        User userToGive = userService.findByPseudo(pseudo);
        if(new User().equals(userToGive)) return "redirect:/user/" + pseudo;

        userService.removeMoney(userToGive, Integer.parseInt(amount));

        return "redirect:/user/" + pseudo;
    }

    @GetMapping("/product/{productId}/{state}")
    public String removeProduct(@PathVariable(value = "productId") int productId, @PathVariable(value="state") String state, @ModelAttribute(name = "currentUser") User user) {
        if(!user.getRole().equals("ADMIN")) return "redirect:/product/" + productId;

        State states = State.valueOf(state);
        productService.changeState(states, productId);

        return "redirect:/product/" + productId;
    }
}
