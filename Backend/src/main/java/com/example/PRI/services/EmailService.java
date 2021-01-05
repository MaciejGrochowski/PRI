package com.example.PRI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    @Value("${mail.username}")
    String serverUsername;

    @Value("${mail.password}")
    String serverPassword;

    @Value("${url.application}")
    String url;

    @Autowired
    TemplateEngine templateEngine;

    public EmailService() {
    }

    public void sendMail(String sendTo, String title, String text){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(serverUsername, serverPassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(serverUsername));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(sendTo)
            );
            message.setSubject(title);
//            message.setContent(text, "text/html");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(text, "utf-8", "html");
            message.setContent(text, "text/html; charset=utf-8");


            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendPasswordRemainder(String username, String email, String password) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("pageToChangePassword", url+ "/" + username+ "/" + password);
        String body = templateEngine.process("passwordRemaind", context);



        StringBuilder title = new StringBuilder()
                .append("[JaNieTakiOrk] ")
                .append(" Przypomnienie hasła dla konta ")
                .append(username);
        sendMail(email, title.toString(), body);
    }

    public void sendWelcomeMail(String username, String email, String password) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("password", password);
        context.setVariable("pathToLogin", url + "/" + "login");
        String body = templateEngine.process("welcomeEmail", context);


        StringBuilder title = new StringBuilder()
                .append("[JaNieTakiOrk] ")
                .append(" Witaj ")
                .append(username);
        sendMail(email, title.toString(), body);
    }
}