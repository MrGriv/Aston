package home.task.module2.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Запись для описания ошибки для отправки как ответ
 *
 * @param errors - сообщения об ошибках
 * @param statusCode - http код ошибки
 * @param timestamp - время ошибки
 */
@Schema(description = "Запись ошибки")
public record ErrorResponse(
        @Schema(description = "Список ошибок")
        Map<String, String> errors,
        @Schema(description = "Статус ошибки", example = "404")
        Integer statusCode,
        @Schema(description = "Время ошибки", example = "2025-07-12T15:00:59.495Z")
        LocalDateTime timestamp) {
}
