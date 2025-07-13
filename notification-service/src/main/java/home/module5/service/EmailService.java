package home.module5.service;

import home.task.module2.dto.MessageDto;

/**
 * Отправка текста на почту пользователя
 */
public interface EmailService {

    /**
     * Метод ждя отправки текста на почту пользователя
     *
     * @param messageDto - DTO запись сообщения
     */
    void send(MessageDto messageDto);
}
