package home.task.module2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер для обработки ошибок
 */
@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Обработчик исключения NotFoundException
     *
     * @param e - ошибка NotFoundException
     *
     * @return возвращается запись описания ошибки с сообщением и http статусом 404
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        Map<String, String> error = Map.of("error", e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse(error, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        log.error(e.getLocalizedMessage());

        return errorResponse;
    }

    /**
     * Обработчик исключения MethodArgumentNotValidException
     *
     * @param e ошибка MethodArgumentNotValidException
     *
     * @return возвращается запись описания ошибки с сообщениями и конкретными полями, где была не пройдена
     * валидация и http статусом 400
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
            log.error("'{}' ошибка валидации. Сообщение: {}.", error.getField(), error.getDefaultMessage());
        }

        return new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }
}
