package home.task.module2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * DTO запись пользователя с полями для обновления
 *
 * @param name - имя пользователя
 * @param email - почта пользователя
 * @param age - возраст пользователя
 */
public record UserUpdate(
        @Size(min = 1, max = 255, message = "Длина имени может быть от 1 до 255 символов")
        String name,

        @Email
        @Size(min = 6, max = 255, message = "Длина почты может быть от 6 до 255 символов")
        String email,

        @PositiveOrZero(message = "Возраст не может быть отрицательным числом")
        Integer age
) {
}
