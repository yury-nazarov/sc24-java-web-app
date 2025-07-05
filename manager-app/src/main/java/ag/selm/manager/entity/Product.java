package ag.selm.manager.entity;

// Товар

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // описывает базовые методы геттеры, сеттеры
@NoArgsConstructor // конструктор без аргументов
@AllArgsConstructor // конструктор для всех аргументов
public class Product {
    private Integer id;

    private String title;

    private String details;
}
