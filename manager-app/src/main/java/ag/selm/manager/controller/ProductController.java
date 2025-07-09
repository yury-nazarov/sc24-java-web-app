package ag.selm.manager.controller;

import ag.selm.manager.controller.payload.NewProductPayload;
import ag.selm.manager.controller.payload.UpdateProductPayload;
import ag.selm.manager.entity.Product;
import ag.selm.manager.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;


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

    private final MessageSource messageSource;

    // ModelAttribute используется для привязки данных из HTTP запроса к объектам модели
    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        // Метод будет возвращать Optional, что бы бороться с Null и NullPointerException
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
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
    public String updateProduct(@ModelAttribute(value = "product", binding = false ) Product product,
                                @Valid UpdateProductPayload  payload,
                                BindingResult bindingResult,
                                Model model  ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/products/edit";
        } else {
            this.productService.updateProduct(product.getId(), payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.getId());
        }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productService.deleteProduct(product.getId());
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception,
                                               Model model,
                                               HttpServletResponse response,
                                               Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error",
                this.messageSource.getMessage(
                        exception.getMessage(),
                        new Object[0],
                        exception.getMessage(),
                        locale));
        return "errors/404";
    }

}
