package dao;

import domain.User;

/**
 * 用户数据管理接口
 */
public interface UserManageDao {

	void add(User user);

	void modify(User user);

	User get_by_name(String user_name);

	User get_by_eamil(String user_email);

	User get_by_nickname(String user_nickname);

}
