package ag.selm.manager.controller;

import ag.selm.manager.controller.payload.NewProductPayload;
import ag.selm.manager.entity.Product;
import ag.selm.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor // Создает конструктор который содержит аргументы для final свойств
@RequestMapping("catalogue/products")
public class ProductController {

    private final ProductService productService;

    // Список товаров
    // @RequestMapping(value = "list", method = RequestMethod.GET)
    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/list";
    }

    // Создать товар
    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload) {
        Product product = this.productService.createProduct(payload.title(), payload.details());
        // Перенаправляем пользователя на созданую страницу
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }

    // Страница товара
    // регулярное выражение означает что должно быть передано целове число
    @GetMapping("{productId:\\d+}")
    public String getProduct(@PathVariable("productId") int productId, Model model) {
        // Метод будет возвращать Optional, что бы бороться с Null и NullPointerException
        model.addAttribute("product", this.productService.findProduct(productId).orElseThrow());
        return "catalogue/products/product";
    }
}
