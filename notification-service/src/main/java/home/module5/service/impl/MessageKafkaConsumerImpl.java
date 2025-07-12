package home.module5.service.impl;

import home.module5.service.EmailService;
import home.module5.service.MessageKafkaConsumer;
import home.task.module2.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageKafkaConsumerImpl implements MessageKafkaConsumer {
    private final EmailService emailService;

    @Override
    @KafkaListener(topics = "${message-topic}")
    public void listen(MessageDto message) {
        emailService.send(message);
    }
}
