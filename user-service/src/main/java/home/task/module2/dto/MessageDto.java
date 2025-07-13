package home.task.module2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO запись сообщения для брокера сообщений
 *
 * @param operationType - тип операции
 * @param email - email
 */
public record MessageDto(
        @NotBlank
        @Size(min = 2, max = 30, message = "Длина типа оператора может быть от 2 до 30 символов")
        String operationType,
        @NotBlank
        @Email
        @Size(min = 6, max = 255, message = "Длина почты может быть от 6 до 255 символов")
        String email) {
}
