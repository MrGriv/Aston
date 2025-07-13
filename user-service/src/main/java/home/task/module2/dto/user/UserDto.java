package home.task.module2.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * DTO запись пользователя (User)
 */
@Getter
@Builder
@RequiredArgsConstructor
@Schema(description = "Запись полного DTO пользователя")
public class UserDto {
        @Schema(description = "Id пользователя", example = "7")
        private final Long id;

        @Schema(description = "Имя пользователя", example = "Ivan")
        private final String name;

        @Schema(description = "Email пользователя", example = "test@mail.com")
        private final String email;

        @Schema(description = "Возраст пользователя", example = "18")
        private final Integer age;

        @Schema(description = "Время создания пользователя", example = "2025-07-12")
        private final LocalDate createdAt;
}
