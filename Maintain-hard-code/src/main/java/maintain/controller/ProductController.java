package maintain.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import maintain.model.Product;
import maintain.model.ProductIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("product")
public class ProductController {

    private ProductIO productIO;

    public ProductController(ProductIO productIO) {
        this.productIO = productIO;
    }

    @GetMapping("/showList")
    public String products(Model model) {
        List<Product> products = productIO.readProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("action", "add");
        return "product";
    }
    @GetMapping("/edit/{code}")
    public String editProductForm(@PathVariable String code, Model model) {
        Product product = productIO.getProductByCode(code);
        model.addAttribute("product", product);
        model.addAttribute("action", "edit");
        return "product";
    }

    @PostMapping("/update")
    public String saveProduct(@ModelAttribute Product product, @RequestParam String action, Model model) {
        if (action.equals("add")) {
            productIO.addProduct(product);
        } else if (action.equals("edit")) {
            productIO.updateProduct(product);
        }
        model.addAttribute("products", productIO.readProducts());
        return "redirect:/product/showList";
    }

    @GetMapping("delete/{code}")
    public String deleteProductForm(@PathVariable String code, Model model) {
        Product product = productIO.getProductByCode(code);
        model.addAttribute("product", product);
        return "delConfirm";
    }

    @PostMapping("/delete/{code}")
    public String deleteProduct(@PathVariable String code, Model model) {
        productIO.deleteProduct(code);
        model.addAttribute("products", productIO.readProducts());
        return "redirect:/product/showList";
    }
}

