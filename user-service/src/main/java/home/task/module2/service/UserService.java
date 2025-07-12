package home.task.module2.service;

import home.task.module2.dto.user.UserDto;
import home.task.module2.dto.user.UserNew;
import home.task.module2.dto.user.UserUpdate;
import home.task.module2.exception.NotFoundException;

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
    UserDto add(UserNew user);

    /**
     * Метод для обновления пользователя
     *
     * @param user - пользователь для обновления
     *
     * @throws NotFoundException - если пользователя нет в бд пробрасывается ошибка
     *
     * @return возвращает пользователя, при успешном обновлении
     */
    UserDto update(Long userId, UserUpdate user);

    /**
     * Метод для получения пользователя
     *
     * @param id - id пользователя
     *
     * @throws NotFoundException - если пользователя нет в бд пробрасывается ошибка
     *
     * @return возвращает пользователя, при успешном нахождении в бд
     */
    UserDto get(Long id);

    /**
     * Метод для получения всех пользователей
     *
     * @return возвращает всех пользователей, при успешном получении данных из бд
     */
    List<UserDto> getAll();

    /**
     * Метод для удаления пользователя
     *
     * @param id - id пользователя
     *
     * @throws NotFoundException - если пользователя нет в бд пробрасывается ошибка
     */
    void delete(Long id);
}
