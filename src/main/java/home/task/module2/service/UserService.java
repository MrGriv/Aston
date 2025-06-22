package home.task.module2.service;

import home.task.module2.User;

import java.util.List;

/**
 * Сервис для CRUD операций с сущностью User
 */
public interface UserService {

    /**
     * Метод добавления пользователя
     *
     * @param user - пользователь для добавления
     *
     * @return возвращает пользователя с id, при успешном добавлении в бд
     */
    User add(User user);

    /**
     * Метод для обновления пользователя
     *
     * @param user - пользователь для обновления
     *
     * @return возвращает пользователя, при успешном обновлении
     */
    User update(User user);

    /**
     * Метод для получения пользователя
     *
     * @param id - id пользователя
     *
     * @return возвращает пользователя, при успешном нахождении в бд
     */
    User get(Long id);

    /**
     * Метод для получения всех пользователей
     *
     * @return возвращает всех пользователей, при успешном получении данных из бд
     */
    List<User> getAll();

    /**
     * Метод для удаления пользователя
     *
     * @param id - id пользователя
     */
    void delete(Long id);
}
