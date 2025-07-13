package home.module5.controller;

import home.module5.service.EmailService;
import home.task.module2.dto.MessageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для отправки email на почту пользователя
 */
@RestController
@RequestMapping("emails")
@RequiredArgsConstructor
public class EmailController {
    /**
     * Интерфейс для отправки текста на почту пользователя
     */
    private final EmailService emailService;

    /**
     * Метод для отправки email на почту пользователя
     *
     * @param messageDto - DTO запись сообщения
     */
    @PostMapping
    public void sendToEmail(@RequestBody @Valid MessageDto messageDto) {
        emailService.send(messageDto);
    }
}
