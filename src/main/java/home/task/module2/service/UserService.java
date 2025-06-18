package home.task.module2.service;

public interface UserService {

    /**
     * Метод добавления пользователя
     */
    void add();

    /**
     * Метод для обновления пользователя
     */
    void update();

    /**
     * Метод для получения пользователя
     */
    void get();

    /**
     * Метод для получения всех пользователей
     */
    void getAll();

    /**
     * Метод для удаления пользователя
     */
    void delete();
}
