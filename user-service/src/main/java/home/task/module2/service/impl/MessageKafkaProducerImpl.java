package home.task.module2.service.impl;

import home.task.module2.dto.MessageDto;
import home.task.module2.enums.OperationType;
import home.task.module2.service.MessageKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class MessageKafkaProducerImpl implements MessageKafkaProducer {
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Override
    public void sendInfoToKafka(OperationType operationType, String email) {
        try {
            kafkaTemplate.send("messages", new MessageDto(operationType.name(), email))
                    .get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            throw new KafkaException("Kafka timeout: message not delivered in 5 seconds", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new KafkaException("Kafka sending was interrupted", e);
        } catch (ExecutionException e) {
            throw new KafkaException("Failed to send message to Kafka", e.getCause());
        } catch (java.util.concurrent.TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
