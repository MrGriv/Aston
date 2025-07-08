package home.task.module2.dto.user;

import java.time.LocalDate;

/**
 * DTO запись пользователя
 *
 * @param id - id пользователя
 * @param name - имя пользователя
 * @param email - почта пользователя
 * @param age - возраст пользователя
 * @param createdAt - время создания пользователя
 */
public record UserDto(
        Long id,
        String name,
        String email,
        Integer age,
        LocalDate createdAt
) {
}
