package ag.selm.manager.controller;

import ag.selm.manager.controller.payload.NewProductPayload;
import ag.selm.manager.controller.payload.UpdateProductPayload;
import ag.selm.manager.entity.Product;
import ag.selm.manager.service.ProductService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("catalogue/products/{productId:\\d+}")
// Lombook: для автоматической генерации конструктора, который принимает в качестве аргументов
// все конечные (final) поля и поля, помеченные аннотацией @NonNull, инициализируя их.
@RequiredArgsConstructor
public class ProductController {
    /*
    * Контроллер занимается обработкой запросов связаных с конкретным товаром
    * */
    private final ProductService productService;

    // ModelAttribute используется для привязки данных из HTTP запроса к объектам модели
    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        // Метод будет возвращать Optional, что бы бороться с Null и NullPointerException
        return this.productService.findProduct(productId).orElseThrow();
    }

    // Страница товара
    // регулярное выражение означает что должно быть передано целове число
    @GetMapping
    public String getProduct() {
        return "catalogue/products/product";
    }

    // Страница редактирования товара
    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute("product") Product product, UpdateProductPayload  payload) {
         this.productService.updateProduct(product.getId(), payload.title(), payload.details());
         return "redirect:/catalogue/products/%d".formatted(product.getId());
    }
}
