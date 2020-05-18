package com.example.productscrud.controller;

import com.example.productscrud.domain.Product;
import com.example.productscrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        List<Product> productList=productService.listAll();
        model.addAttribute("listProducts",productList);
        return "index";}

        @GetMapping("/new")
        public String showNewProductPage(Model model)
        {Product product=new Product();
        model.addAttribute("theproduct",product);
            return "new_product";}

            @PostMapping("/save")
            public String saveProduct(Product product, BindingResult bindingResult)
            {
                if(bindingResult.hasErrors())
                    return "error";
                productService.save(product);
                return "redirect:/";
            }

            @GetMapping("/edit/{id}")
            public String showEditPage(@PathVariable int id, Model model)
            {
                Optional<Product> product=productService.findProduct(id);
                if(product.isPresent())
                {model.addAttribute("product_to_edit",product.get());
                    return "edit_product";}
                else
                    return "error";
            }

            @GetMapping("/delete/{id}")
            public String deleteProduct(@PathVariable long id)
            {productService.delete(id);
                return "redirect:/";
            }

}
