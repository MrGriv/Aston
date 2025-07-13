package home.module5.service.impl;

import home.module5.service.EmailService;
import home.task.module2.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Value("${email-from}")
    private String from;

    @Value("${texts.add}")
    private String addText;

    @Value("${texts.delete}")
    private String deleteText;

    @Override
    public void send(MessageDto message) {
        String text;

        switch (message.operationType()) {
            case "ADD" -> text = addText;
            case "DELETE" -> text = deleteText;
            default -> {
                return;
            }
        }

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(message.email());
        msg.setSubject("Статус");
        msg.setText(text);

        mailSender.send(msg);
    }
}
