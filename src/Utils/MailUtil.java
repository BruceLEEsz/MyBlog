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
 * �ʼ����á����͹����ࡣ
 */
public class MailUtil {
	// �ʼ����͵�ַ
	private String from = "linapz@qq.com";
	// �ʼ���������ַ
	private String host = "smtp.qq.com";
	// �ʼ����յ�ַ
	private String to = "";
	// ��������
	private Properties properties = System.getProperties();
	// �Ự����
	private Session session = Session.getDefaultInstance(properties, new Authenticator() {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("linapz@qq.com", "syqzovqzrjclbjed");
		}
	});
	// �ʼ�ģ��ӿ�
	private Mail mail;

	// ���ͷ���
	public void sendto(String to, Mail mail) {
		// ���ý��յ�ַ
		this.to = to;
		// ���÷����ʼ�
		this.mail = mail;
		// ��������
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
