package home.module7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Класс для запуска ExternalConfigurationApplication.
 * Микросервис, который предоставляет доступ к внешним файлам конфигурации
 */
@EnableConfigServer
@SpringBootApplication
public class ExternalConfigurationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExternalConfigurationApplication.class);
    }
}