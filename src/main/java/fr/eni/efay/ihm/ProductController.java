package fr.eni.efay.ihm;

import fr.eni.efay.bll.interfaces.*;
import fr.eni.efay.bo.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


@Controller()
@SessionAttributes({"currentUser"})
@RequestMapping("/product")
public class ProductController {
    ProductService productService;
    ImageService imageService;
    CategoryService categoryService;
    HistoryService historyService;
    UserService userService;

    public ProductController(ProductService productService, ImageService imageService, CategoryService categoryService, HistoryService historyService, UserService userService){
        this.productService = productService;
        this.imageService = imageService;
        this.categoryService = categoryService;
        this.historyService = historyService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String productForm(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "view-productForm";
    }

    @PostMapping("/create")
    public String productValid(@ModelAttribute(name = "product_name") String name,
                               @ModelAttribute(name = "product_category") String categoryId,
                               @ModelAttribute(name = "product_price") int price,
                               @ModelAttribute(name = "product_description") String description,
                               @RequestParam(name = "product_pictures") MultipartFile file,
                               @ModelAttribute(name = "currentUser") User user){

        if(name.isEmpty()
                || categoryId.isEmpty()
                || price == 0
                || description.isEmpty()
                || file.isEmpty()
                || user.equals(new User())) return "redirect:/product/create";

        Image myImage = imageService.uploadImage(file);
        if(myImage.equals(new Image())) return "redirect:/product/create";
        Product product = new Product(name, description, price, State.Available, categoryService.findById(Integer.parseInt(categoryId)), user, myImage);
        productService.insert(product);

        return "redirect:/product/" + product.getProduct_id();
    }

    @GetMapping("/{productId}")
    public String displayProductById(@PathVariable(value = "productId") Long product_id, Model model) {
        Product product = productService.findById(product_id);
        User user = product.getUser_id();

        if (user != null) {
            model.addAttribute("product", product);
            model.addAttribute("user", user);

            List<Product> products = productService.getAllBySellerId(user.getId());
            model.addAttribute("nbProductsAvailable", products.stream().filter(p -> p.getState() != State.Deleted).toList().size());

            model.addAttribute("currentURL", "productView");
            return "/view-product-details";
        } else {
            throw new EmptyResultDataAccessException("Ce produit ou cette utilisateur n'existe pas",1);
        }
    }
    @RequestMapping("/product-error")
    public String productError(Model model) {
        model.addAttribute("productError", true);
        return "error";
    }

    @GetMapping("/buy/{productId}")
    public String buyProduct(@PathVariable(value = "productId") Long product_id, @ModelAttribute(name = "currentUser") User user ,Model model) {
        Product product;
        try {
            product = productService.findById(product_id);
        } catch (Exception e) {
            return "redirect:/main";
        }

        User updateUser = userService.findById(user.getId());

        if (product != null && !product.getUser_id().equals(user) && product.getState().equals(State.Available) && updateUser.getSold() >= product.getPrice()){
            History history = new History(LocalDate.now(), product, user);
            historyService.insert(history);
            productService.changeState(State.Selled, product.getProduct_id());
            userService.removeMoney(updateUser, product.getPrice());
            userService.addMoney(product.getUser_id(), product.getPrice());
            user.setSold(updateUser.getSold());
            model.addAttribute("productInfos", productService.findById(product.getProduct_id()));
            return "redirect:/settings";
        } else {
            return "redirect:/product/"+product_id;
        }
    }
}