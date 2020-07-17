import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * File Description/檔案描述:
 *
 * @author JamesChang
 * @version 1.0
 * @since 2020/6/29下午 04:21
 **/
public class EmailTest {
    public static void main(String[] args) throws MessagingException {
        String host = "smtp.gmail.com";
        int port = 587;
        final String username = "james89926282@gmail.com";
        final String password = "Jameschangyogwcrhokyzzplwp";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("test@testmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tpcs_service@wezoomtek.com.tw"));
        message.setSubject("測試寄信.");
        message.setText("Dear All, \n\n JamesChang 測試 測試 測試 測試 測試 測試 GMail 測試 !");

        Transport transport = session.getTransport("smtp");
        transport.connect(host, port, username, password);

        Transport.send(message);

        System.out.println("寄送email結束.");
    }
}
