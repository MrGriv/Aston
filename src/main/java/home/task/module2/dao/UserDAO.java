package home.task.module2.dao;

import home.task.module2.User;

import java.util.List;

/**
 * DAO Интерфейс для User для взаимодействия с базой данных
 */
public interface UserDAO {

    /**
     * Добавление пользователя
     *
     * @param user - пользователь для добавления
     *
     * @return возвращает пользователя с id, при успешном добавлении в бд
     */
    User add(User user);

    /**
     * Обновление пользователя
     *
     * @param user - пользователь для обновления
     *
     * @return возвращает пользователя, при успешном обновлении
     */
    User update(User user);

    /**
     * Получение пользователя
     *
     * @param id - id пользователя
     *
     * @return возвращает пользователя, при успешном нахождении в бд
     */
    User get(Long id);

    /**
     * Получение всех пользователей
     *
     * @return возвращает всех пользователей, при успешном получении данных из бд
     */
    List<User> getAll();

    /**
     * Удаление пользователя
     *
     * @param id - id пользователя
     */
    void delete(Long id);
}
