package by.fly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.function.Consumer;

@Service
public class MailService {

    @Autowired
    private MailSender mailSender;

    public void sendMail(String to, String subject, String body, Consumer<Exception> errorHandler) {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<?> future = executor.submit(() -> doSendMail(to, subject, body));
        try {
            future.get(30, TimeUnit.SECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException x) {
            errorHandler.accept(x);
        } finally {
            executor.shutdown();
        }
    }

    private void doSendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
