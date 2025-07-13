package home.task.module2.controller;

import home.task.module2.assembler.UserDtoModelAssembler;
import home.task.module2.dto.user.UserDto;
import home.task.module2.dto.user.UserNew;
import home.task.module2.dto.user.UserUpdate;
import home.task.module2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер ввода данных для CRUD операций с пользователем
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Контроллер для работы с пользователями", description = "CRUD операции с пользователями ")
public class UserController {

    /**
     * Интерфейс UserService
     */
    private final UserService userService;
    private final UserDtoModelAssembler userAssembler;

    /**
     * Ввод данных для добавления пользователя
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание пользователя")
    public ResponseEntity<EntityModel<UserDto>> add(@RequestBody @Valid UserNew userNew) {
        EntityModel<UserDto> model = userAssembler.toModel(userService.add(userNew));
        return ResponseEntity
                .created(model.getRequiredLink("self").toUri())
                .body(model);
    }

    /**
     * Ввод данных для обновления пользователя
     */
    @PatchMapping("/{userId}")
    @Operation(summary = "Обновление пользователя по id")
    public EntityModel<UserDto> update(@RequestBody @Valid UserUpdate userUpdate,
                          @Parameter(description = "id пользователя", example = "1")
                          @PathVariable Long userId) {
        return userAssembler.toModel(userService.update(userId, userUpdate));
    }

    /**
     * Получение пользователя по id
     */
    @GetMapping("/{userId}")
    @Operation(summary = "Получение пользователя по id")
    public EntityModel<UserDto> get(@Parameter(description = "id пользователя", example = "1")
                                    @PathVariable Long userId) {
        return userAssembler.toModel(userService.get(userId));
    }

    /**
     * Получение всех пользователей
     */
    @GetMapping
    @Operation(summary = "Получение всех пользователей")
    public CollectionModel<EntityModel<UserDto>> getAll() {
        return userAssembler.toCollectionModel(userService.getAll());
    }

    /**
     * Ввод данных для удаления пользователя по id
     */
    @DeleteMapping("/{userId}")
    @Operation(summary = "Удаление пользователя по id")
    private ResponseEntity<Void> delete(@Parameter(description = "id пользователя", example = "1")
                                        @PathVariable Long userId) {
        userService.delete(userId);

        return ResponseEntity.noContent().build();
    }
}
