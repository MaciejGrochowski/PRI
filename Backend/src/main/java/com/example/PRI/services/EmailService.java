package com.example.PRI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@Service
public class EmailService {
    @Value("${mail.username}")
    String serverUsername;

    @Value("${mail.password}")
    String serverPassword;

    @Value("${url.application}")
    String url;

    @Value("${server.image.folder}")
    String imagePath;

    @Autowired
    TemplateEngine templateEngine;

    public EmailService() {
    }

    private Properties setProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.transport.protocol", "smtp");

        return props;
    }

    private Session testConnection(Properties prop){
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(serverUsername, serverPassword);
                    }
                });
        return session;
    }
    public void sendMail(String sendTo, String title, Context ctx, String template) throws MessagingException {
        Properties prop = setProperties();
        Session session = testConnection(prop);

        MimeMessage message = new MimeMessage(session);
        final MimeMessageHelper mimeMessage = new MimeMessageHelper(message, true, "UTF-8");


        mimeMessage.setFrom(new InternetAddress(serverUsername));
        mimeMessage.setTo(InternetAddress.parse(sendTo));

        mimeMessage.setSubject(title);

        final String htmlContent = templateEngine.process(template, ctx);

        mimeMessage.setText(htmlContent, true);
        try {
            Resource fav = new FileSystemResource(new File(imagePath + File.separator + "jaNieTakiFav.png"));
            Resource logo = new FileSystemResource(new File(imagePath + File.separator + "jaNieTakieLogo.png"));
            mimeMessage.addInline("logo", logo);
            mimeMessage.addInline("fav", fav);
        }catch(Exception ex){
            System.out.println("Błąd z nazwami obrazków");
        }
            Transport.send(message);
            System.out.println("Done");

    }

    public void sendPasswordRemainder(String username, String email, String activateCode) throws MessagingException {
        final Context ctx = new Context();
        ctx.setVariable("username", username);
        ctx.setVariable("pageToChangePassword", url+ "/newPassword/" + username+ "/" + activateCode);
        StringBuilder title = new StringBuilder()
                .append("[JaNieTakiOrk] ")
                .append(" Przypomnienie hasła dla konta ")
                .append(username);
        sendMail(email, title.toString(),  ctx, "passwordRemaind");
    }

    public void sendWelcomeMail(String username, String email, String password, String uuid) throws MessagingException {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("password", password);
        //context.setVariable("pathToLogin", url + "/" + "login");
        context.setVariable("pathToActivateAccount", url + "/activate-user/" + username + "/" + uuid);
        StringBuilder title = new StringBuilder()
                .append("[JaNieTakiOrk] ")
                .append(" Witaj ")
                .append(username);
        sendMail(email, title.toString(), context, "welcomeEmail");
    }
}