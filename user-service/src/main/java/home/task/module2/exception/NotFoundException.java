package home.task.module2.exception;

/**
 * Класс ошибки, когда объект не найден в бд
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
