package dao;

import domain.Role;

/**
 * ��ɫ���ݹ���ӿ�
 */
public interface RoleManageDao {

	Role getUser();
	void add_user(Role role);
	Role getModerator();

}
