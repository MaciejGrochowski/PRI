package com.example.PRI.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
//    @Value("${mail.username}")
    final String serverUsername = "janietakiork@gmail.com";

//    @Value("${mail.password}")
    final String serverPassword = "$^996B%*4%x@";

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
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendPasswordRemainder(String username, String email, String password) {
        StringBuilder title = new StringBuilder()
                .append("[JaNieTakiOrk]")
                .append(" Password remainder for ")
                .append(username);
        String message = "Hello " + username + "!" + "Your password is: " + password;
        sendMail(email, title.toString(), message);
    }
}