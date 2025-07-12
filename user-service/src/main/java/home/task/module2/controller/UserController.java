package home.task.module2.controller;

import home.task.module2.dto.user.UserDto;
import home.task.module2.dto.user.UserNew;
import home.task.module2.dto.user.UserUpdate;
import home.task.module2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер ввода данных для CRUD операций с пользователем
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    /**
     * Интерфейс UserService
     */
    private final UserService userService;

    /**
     * Ввод данных для добавления пользователя
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto add(@RequestBody @Valid UserNew userNew) {
        return userService.add(userNew);
    }

    /**
     * Ввод данных для обновления пользователя
     */
    @PatchMapping("/{userId}")
    public UserDto update(@RequestBody @Valid UserUpdate userUpdate, @PathVariable Long userId) {
        return userService.update(userId, userUpdate);
    }

    /**
     * Получение пользователя по id
     */
    @GetMapping("/{userId}")
    public UserDto get(@PathVariable Long userId) {
        return userService.get(userId);
    }

    /**
     * Получение всех пользователей
     */
    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    /**
     * Ввод данных для удаления пользователя по id
     */
    @DeleteMapping("/{userId}")
    private void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
