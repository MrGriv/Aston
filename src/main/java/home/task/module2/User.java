package home.task.module2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Класс пользователя с полями
 * (ID генерируется базой данных) имя,
 *  электронная почта и возраст
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    /**
     * Id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя пользователя
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Почта пользователя
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Возраст пользователя
     */
    @Column(name = "age")
    private Integer age;

    /**
     * Время создания пользователя
     */
    @Column(name = "created_at")
    private LocalDate createdAt;
}
