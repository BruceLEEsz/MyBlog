package service;
/**
 * �û�ע����Ϣ��֤�ӿ�
 */
public interface InfoCheck {
	boolean checkname(String s);

	String checkmail(String s);

	boolean checkpassword(String s, String s2);

	boolean ismail(String mail);

}
