package service;

import domain.User;

/**
 * �û�����ҵ���߼��ӿ�
 */
public interface UserManageService {

	User register(User user);

	User login(User user);

	void confirm(User u, String user_name);

	void modfiyinfo(User user);

	void modifypw(User u, User user);

	void setmoderator(String user_nickname);

	void setuser(String user_nickname);

	void resetpw(User user);

	void findpw(String email);

}
