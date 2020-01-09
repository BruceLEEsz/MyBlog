package dao;

import domain.Role;

/**
 * 角色数据管理接口
 */
public interface RoleManageDao {

	Role getUser();
	void add_user(Role role);
	Role getModerator();

}
