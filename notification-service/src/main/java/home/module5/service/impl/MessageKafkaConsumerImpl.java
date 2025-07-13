package home.module5.service.impl;

import home.module5.service.EmailService;
import home.module5.service.MessageKafkaConsumer;
import home.task.module2.dto.MessageDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@RequiredArgsConstructor
public class MessageKafkaConsumerImpl implements MessageKafkaConsumer {
    private final EmailService emailService;
    @Getter
    private CountDownLatch latch = new CountDownLatch(1);

    @Override
    @KafkaListener(topics = "${message-topic}")
    public void listen(MessageDto message) {
        emailService.send(message);
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
}
