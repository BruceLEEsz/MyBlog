package service;
/**
 * 用户注册信息验证接口
 */
public interface InfoCheck {
	boolean checkname(String s);

	String checkmail(String s);

	boolean checkpassword(String s, String s2);

	boolean ismail(String mail);

}
