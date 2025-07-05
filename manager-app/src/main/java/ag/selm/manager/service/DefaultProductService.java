package ag.selm.manager.service;

import ag.selm.manager.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Создает конструктор который содержит аргументы для final свойств
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
}
