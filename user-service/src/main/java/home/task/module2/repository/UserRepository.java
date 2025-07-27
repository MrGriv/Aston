package home.task.module2.repository;

import home.task.module2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Интерфейс для взаимодействия с бд для CRUD операций с пользователем
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Метод для блокировки конкретного пользователя до конца текущей транзакции
     *
     * @param userId - id пользователя
     *
     * @return возвращает Optional пользователя (пустой Optional, если пользователь не найден)
     */
    @Query(value =
            "SELECT * " +
            "FROM users AS u " +
            "WHERE u.id = :userId " +
            "FOR UPDATE;", nativeQuery = true)
    Optional<User> findByIdForUpdate(@Param("userId") Long userId);
}
