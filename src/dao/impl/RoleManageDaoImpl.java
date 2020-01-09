package dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateSessionFactory;
import dao.RoleManageDao;
import domain.Role;

/**
 * 角色数据管理实现类
 */
public class RoleManageDaoImpl implements RoleManageDao {

	// 返回用户角色对象
	@Override
	public Role getUser() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql对象
		String hql = "from domain.Role where role_name = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter(0, "user");
		// 获取插叙结果
		Role r_user = (Role) query.uniqueResult();
		return r_user;
	}

	// 新增角色
	@Override
	public void add_user(Role role) {
		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(role);
	}

	// 返回Moderator角色对象
	@Override
	public Role getModerator() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql对象
		String hql = "from domain.Role where role_name = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 参数设置
		query.setParameter(0, "moderator");
		// 获取查询结果
		Role moderator = (Role) query.uniqueResult();
		return moderator;

	}

}
