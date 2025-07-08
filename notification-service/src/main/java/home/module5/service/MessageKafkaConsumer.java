package home.module5.service;

import home.task.module2.dto.MessageDto;

public interface MessageKafkaConsumer {
    void listen(MessageDto message);
}
