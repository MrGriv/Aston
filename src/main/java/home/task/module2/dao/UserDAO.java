package home.task.module2.dao;

import home.task.module2.User;

public interface UserDAO {

    /**
     * Добавление пользователя
     * @param user - пользователь для добавления
     */
    void add(User user);

    /**
     * Обновление пользователя
     * @param user - пользователь для обновления
     */
    void update(User user);

    /**
     * Получение пользователя
     * @param id - id пользователя
     */
    User get(Long id);

    /**
     * Получение всех пользователей
     */
    void getAll();

    /**
     * Удаление пользователя
     * @param id - id пользователя
     */
    void delete(Long id);
}
