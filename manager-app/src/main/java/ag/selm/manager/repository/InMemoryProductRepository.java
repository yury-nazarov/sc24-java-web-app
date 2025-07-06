package ag.selm.manager.repository;

import ag.selm.manager.entity.Product;
import org.springframework.stereotype.Repository;


import java.util.*;
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

    public Product save(Product product) {
        // Для вычисления уникально id - получаем товар с максимальным идентификатором и увеличиваем его на еденицу
        product.setId(this.products.stream()
                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId) // .max возвращает optional, пре образовываем в идентификатор
                .orElse(0) + 1 // Если товара еще нет, то ставим 0
                // +1 увеличивает полученый идентификатор на 1
        );
        // Добавляем товар в список товаров
        this.products.add(product);
        return product;
    }

    public Optional<Product> findById(Integer productId) {
        return this.products.stream()
                .filter(product -> Objects.equals(productId, product.getId())).findFirst();
    }

}
