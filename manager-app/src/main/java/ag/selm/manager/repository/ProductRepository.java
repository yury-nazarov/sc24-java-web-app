package ag.selm.manager.repository;

import ag.selm.manager.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
}
