package home.task.module2.service;

import home.task.module2.enums.OperationType;

/**
 * Отправка сообщений в kafka
 */
public interface MessageKafkaProducer {
    /**
     * Метод для отправки MessageDto в kafka
     *
     * @param operationType - тип операции
     * @param email - email пользователя
     */
    void sendInfoToKafka(OperationType operationType, String email);
}
