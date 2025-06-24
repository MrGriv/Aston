package home.task.module2.mapper;

import home.task.module2.dto.UserDto;
import home.task.module2.dto.UserNew;
import home.task.module2.dto.UserUpdate;
import home.task.module2.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * Маппер для пользователя
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Метод для маппинга нового пользователя в сущность
     *
     * @param userNew - новый пользователь
     *
     * @return возвращается сущность
     */
    User ofUserNew(UserNew userNew);

    /**
     * Метод для маппинга сущности пользователя в DTO
     *
     * @param entity - сущность
     *
     * @return возвращается DTO пользователя
     */
    UserDto ofEntity(User entity);

    /**
     * Метод для маппинга сущностей пользователей в DTO
     *
     * @param entities - список сущностей
     *
     * @return возвращается список с DTO пользователей
     */
    List<UserDto> ofListEntities(List<User> entities);

    /**
     * Метод для обновления сущности через DTO
     *
     * @param user - обновляемая сущность
     * @param userUpdate - DTO с обновленными данными пользователя
     */
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void userOfUpdateUser(@MappingTarget User user, UserUpdate userUpdate);
}
