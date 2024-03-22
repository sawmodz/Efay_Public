package fr.eni.efay.ihm;

import fr.eni.efay.bll.interfaces.FeedbackService;
import fr.eni.efay.bll.interfaces.HistoryService;
import fr.eni.efay.bll.interfaces.ProductService;
import fr.eni.efay.bll.interfaces.UserService;
import fr.eni.efay.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@SessionAttributes({"currentUser"})
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    ProductService productService;

    @Autowired
    HistoryService historyService;


    @ModelAttribute("currentUser")
    public User chargerMembre() {
        return new User();
    }

    @GetMapping("/user/{username}")
    public String user(@PathVariable String username, Model model) {
        User user;
        try {
            user = userService.findByPseudo(username);
        } catch (Exception e) {
            user = new User();
        }

        model.addAttribute("user", user);
        model.addAttribute("currentURL", "userView");

        if(user.getUsername() != null) {
            List<Feedback> feedbacks = feedbackService.getAllBySellerId(user.getId());
            model.addAttribute("avis", feedbacks);

            if(feedbacks.isEmpty()){
                model.addAttribute("note", 0);
            }else{
                int note = 0;
                for (Feedback feedback : feedbacks) {
                    note += feedback.getNote();
                }
                model.addAttribute("note", note / feedbacks.size());
            }

            List<Product> products = productService.getAllBySellerId(user.getId());

            List<Product> productsSelled = products.stream().filter(product -> product.getState() == State.Selled).toList();
            List<Product> productsAvailable = products.stream().filter(product -> product.getState() == State.Available).toList();

            model.addAttribute("products", productsSelled);
            model.addAttribute("productsAvailable", productsAvailable);
        }

        return "view-user";
    }

    @GetMapping("/settings")
    public String settings(@ModelAttribute(name = "currentUser") User user, Model model){

        List<Feedback> feedbacks = feedbackService.getAllBySellerId(user.getId());
        model.addAttribute("avis", feedbacks);

        if(feedbacks.isEmpty()){
            model.addAttribute("note", 0);
        }else{
            int note = 0;
            for (Feedback feedback : feedbacks) {
                note += feedback.getNote();
            }
            model.addAttribute("note", note / feedbacks.size());
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("address", user.getAddress());


        List<History> histories = historyService.getAllByUserId(user.getId());
        List<Product> products = productService.getAllBySellerId(user.getId());

        List<Product> productsSelledByUser = products.stream().filter(product -> product.getState() == State.Selled).toList();

        model.addAttribute("history", histories);
        model.addAttribute("productSelled", productsSelledByUser);


        return "view-settings";
    }

    @PostMapping("/settings/update")
    public String settingsValid(@ModelAttribute(name = "username") String username,
                                @ModelAttribute(name = "email") String email,
                                @ModelAttribute(name = "address") String address,
                                @ModelAttribute(name = "currentUser") User user){

        if(username.isEmpty()
                || email.isEmpty()
                || address.isEmpty()
                || user.equals(new User())) return "redirect:/settings";

        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);

        userService.update(user);

        return "redirect:/settings";
    }
}
