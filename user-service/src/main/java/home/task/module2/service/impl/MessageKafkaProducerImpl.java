package home.task.module2.service.impl;

import home.task.module2.dto.MessageDto;
import home.task.module2.enums.OperationType;
import home.task.module2.service.MessageKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageKafkaProducerImpl implements MessageKafkaProducer {
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Override
    public void sendInfoToKafka(OperationType operationType, String email) {
        kafkaTemplate.send("messages", new MessageDto(operationType.name(), email));
    }
}
