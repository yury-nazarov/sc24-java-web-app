package ag.selm.manager.repository;

import ag.selm.manager.entity.Product;
import org.springframework.stereotype.Repository;


import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    // Пустой список товаров
    // Collections.synchronizedList - потокобезопасная версия списка
    private final List<Product> products = Collections.synchronizedList(new LinkedList<> ());

    // Конструктор класса без аргументов
    public InMemoryProductRepository() {
        IntStream.range(1, 4).forEach(i -> this.products.add(
                new Product(i,
                        "Товар №%d".formatted(i),
                        "Описание товара №%d".formatted(i)
                )));
    }

    public List<Product> findAll() {
        // Возвращаем не модифицируемую копию списка (что бы к нему не получили доступ)
        return Collections.unmodifiableList(this.products);
    }

}
