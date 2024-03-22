package fr.eni.efay.ihm;

import fr.eni.efay.bll.interfaces.CategoryService;
import fr.eni.efay.bll.interfaces.ProductService;
import fr.eni.efay.bo.Category;
import fr.eni.efay.bo.Product;
import fr.eni.efay.bo.State;
import fr.eni.efay.bo.User;
import fr.eni.efay.dal.interfaces.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"currentUser"})
public class LandingController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @ModelAttribute("currentUser")
    public User chargerMembre() {
        return new User();
    }

    @GetMapping("/")
    public String index(@ModelAttribute(name = "currentUser") User user) {
        if(user.equals(new User())) return "view-landing";
        else return "redirect:/main";
    }

    @GetMapping("/main")
    public String logged(@ModelAttribute(name = "currentUser") User user, Model model){
        List<Category> categorys = categoryService.getAll();
        List<Product> products = productService.getAll();
        model.addAttribute("categorys", categorys.subList(0, 6));
        products= products.stream().filter(p -> !p.getUser_id().equals(user) && p.getState() == State.Available).toList();
        if(products.size() > 5) products = products.subList(0, 5);
        model.addAttribute("products", products);
        return "view-landing-logged";
    }

}
