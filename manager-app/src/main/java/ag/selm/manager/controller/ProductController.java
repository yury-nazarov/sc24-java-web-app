package ag.selm.manager.controller;

import ag.selm.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor // Создает конструктор который содержит аргументы для final свойств
public class ProductController {

    private final ProductService productService;
}
