package dao;

import domain.User;

/**
 * 好友数据管理接口
 */
public interface FriendManageDao {
	void add(User user);

	void delete(User user);
}
