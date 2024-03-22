package fr.eni.efay.ihm;

import fr.eni.efay.bll.interfaces.ProductService;
import fr.eni.efay.bo.Product;
import fr.eni.efay.bo.State;
import fr.eni.efay.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"currentUser"})
public class SearchController {
    @Autowired
    ProductService productService;

    @GetMapping("/search")
    public String search(@ModelAttribute("search") String search, @ModelAttribute(name = "currentUser") User user, Model model){
        List<Product> products =  productService.search(search);
        model.addAttribute("search", search);
        model.addAttribute("products", products.stream()
                .filter(p -> p.getState() == State.Available)
                .filter(p -> !p.getUser_id().equals(user)).toList()); //Filter result to remove user's own products
        return "search";
    }
}
