package ag.selm.manager.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewProductPayload(
        // Название не может быть пустым
        @NotNull(message = "{catalogue.products.create.errors.title_is_null}")
        // Длина названия от 3 до 50 символов
        @Size(min = 3, max = 50, message = "{catalogue.products.create.errors.title_size_is_invalid}")
        String title,

        @Size(max = 1000, message = "{catalogue.products.create.errors.details_size_is_invalid}")
        String details) {


}
