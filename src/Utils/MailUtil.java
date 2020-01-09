package Utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

import Utils.Mail.Mail;

/**
 * 邮件配置、发送工具类。
 */
public class MailUtil {
	// 邮件发送地址
	private String from = "linapz@qq.com";
	// 邮件服务器地址
	private String host = "smtp.qq.com";
	// 邮件接收地址
	private String to = "";
	// 参数对象
	private Properties properties = System.getProperties();
	// 会话对象
	private Session session = Session.getDefaultInstance(properties, new Authenticator() {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("linapz@qq.com", "syqzovqzrjclbjed");
		}
	});
	// 邮件模板接口
	private Mail mail;

	// 发送方法
	public void sendto(String to, Mail mail) {
		// 设置接收地址
		this.to = to;
		// 设置发送邮件
		this.mail = mail;
		// 参数配置
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		MailSSLSocketFactory sf;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException gce) {
			// TODO Auto-generated catch block
			gce.printStackTrace();
		}

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(mail.getSubjet().toString());

			message.setContent(mail.getContext().toString(), "text/html;charset=UTF-8");

			Transport.send(message);

			System.out.println("successful");

			mail.clean();
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
