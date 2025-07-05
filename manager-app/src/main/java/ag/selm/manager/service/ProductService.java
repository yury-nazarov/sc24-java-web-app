package ag.selm.manager.service;

import ag.selm.manager.entity.Product;

import java.util.List;


public interface ProductService {
    List<Product> findAllProducts();
}
