package com.hiyzg.shop.util;

import lombok.Cleanup;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Sam on 2019/3/12.
 */
public class MailUtil {
    private static Properties properties = new Properties();
    private static String HOST;
    private static String PROTOCOL;
    private static String FROM;
    private static String PASSWORD;

    static {
        try {
            properties.load(MailUtil.class.getResourceAsStream("/mail.properties"));
            HOST = properties.getProperty("mail.host");
            PROTOCOL = properties.getProperty("mail.protocol");
            FROM = properties.getProperty("mail.from");
            PASSWORD = properties.getProperty("mail.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static MimeMessage createSimpleMail(Session session, String email, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(FROM));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(content, "text/html;charset=UTF-8");
        return mimeMessage;
    }

    public static boolean sendSimpleMail(String email, String subject, String content) {
        try {
            Session session = Session.getInstance(properties);
            @Cleanup
            Transport transport = session.getTransport();
            transport.connect(FROM, PASSWORD);
            MimeMessage message = createSimpleMail(session, email, subject, content);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
