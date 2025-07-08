package home.task.module2.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Запись для описания ошибки для отправки как ответ
 *
 * @param errors - сообщения об ошибках
 * @param statusCode - http код ошибки
 * @param timestamp - время ошибки
 */
public record ErrorResponse(

        Map<String, String> errors,

        Integer statusCode,

        LocalDateTime timestamp) {
}
