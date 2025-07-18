package home.module5.service;

import home.task.module2.dto.MessageDto;

import java.util.concurrent.CountDownLatch;

/**
 * Прослушивание сообщений из kafka
 */
public interface MessageKafkaConsumer {
    /**
     * Метод для прослушивания сообщений из kafka
     *
     * @param message - DTO запись сообщения
     */
    void listen(MessageDto message);

    /**
     * Метод получения потокобезопасного счетчика
     *
     * @return - возвращает счетчик
     */
    CountDownLatch getLatch();
}
