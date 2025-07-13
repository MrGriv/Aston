package home.module5.service.impl;

import home.module5.NotificationServiceApp;
import home.module5.service.EmailService;
import home.module5.service.MessageKafkaConsumer;
import home.task.module2.config.KafkaProducerConfig;
import home.task.module2.enums.OperationType;
import home.task.module2.service.MessageKafkaProducer;
import home.task.module2.service.impl.MessageKafkaProducerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {NotificationServiceApp.class, KafkaProducerConfig.class, MessageKafkaProducerImpl.class})
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class KafkaIntegrationTest {
    @MockBean
    private JavaMailSender mailSender;

    @MockBean
    private EmailService emailService;

    @Autowired
    private MessageKafkaConsumer consumer;

    @Autowired
    private MessageKafkaProducer producer;

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers",
                () -> "localhost:9092");
        registry.add("spring.kafka.consumer.group-id", () -> "messages-group");
        registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
        registry.add("message-topic", () -> "messages");
        registry.add("spring.kafka.consumer.key-deserializer",
                () -> "org.apache.kafka.common.serialization.StringDeserializer");
        registry.add("spring.kafka.consumer.value-deserializer",
                () -> "org.springframework.kafka.support.serializer.JsonDeserializer");
        registry.add("spring.kafka.consumer.properties.spring.json.trusted.packages",
                () -> "*");
    }

    @Test
    @DisplayName("Корректно работающие producer и consumer")
    public void givenEmbeddedKafkaBrokerWhenProducerSendingThenMessageReceived()
            throws InterruptedException {
        producer.sendInfoToKafka(OperationType.ADD, "test@email");
        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);

        assertTrue(messageConsumed);
    }
}