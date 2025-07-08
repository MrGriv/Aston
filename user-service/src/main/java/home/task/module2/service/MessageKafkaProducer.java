package home.task.module2.service;

import home.task.module2.enums.OperationType;

public interface MessageKafkaProducer {
    void sendInfoToKafka(OperationType operationType, String email);
}
