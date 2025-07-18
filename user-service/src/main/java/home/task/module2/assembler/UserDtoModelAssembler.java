package home.task.module2.assembler;

import home.task.module2.controller.UserController;
import home.task.module2.dto.user.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Класс для преобразования объектов UserDto в модели с HATEOAS-ссылками
 */
@Component
public class UserDtoModelAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {
    /**
     * Преобразует один объект UserDto в EntityModel<UserDto>, добавляя HATEOAS-ссылки.
     *
     * @param userDto - DTO запись пользователя
     *
     * @return EntityModel обертку вокруг DTO с поддержкой ссылок
     */
    @Override
    public EntityModel<UserDto> toModel(UserDto userDto) {
        return EntityModel.of(userDto,
                linkTo(methodOn(UserController.class).get(userDto.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("List of all users"));
    }

    /**
     * Преобразует коллекцию UserDto в CollectionModel<EntityModel<UserDto>>, добавляя ссылки на уровне коллекции
     *
     * @param entities - список UserDto
     *
     * @return CollectionModel обертку для списка из EntityModel
     */
    @Override
    public CollectionModel<EntityModel<UserDto>> toCollectionModel(Iterable<? extends UserDto> entities) {
        CollectionModel<EntityModel<UserDto>> collectionModel = RepresentationModelAssembler.super
                .toCollectionModel(entities);
        collectionModel.add(linkTo(methodOn(UserController.class).getAll()).withSelfRel());

        return collectionModel;
    }
}
