package home.task.module2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MessageDto(
        @NotBlank
        String operationType,
        @NotBlank
        @Email
        @Size(min = 6, max = 255, message = "Длина почты может быть от 6 до 255 символов")
        String email) {
}
