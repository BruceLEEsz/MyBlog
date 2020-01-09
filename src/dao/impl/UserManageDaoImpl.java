package dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateSessionFactory;
import dao.UserManageDao;
import domain.User;

/**
 * 用户数据管理实现类
 */
public class UserManageDaoImpl implements UserManageDao {

	// 新增用户方法
	@Override
	public void add(User user) {

		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 新增用户对象
		session.save(user);

	}

	// 以用户名查询用户方法
	@Override
	public User get_by_name(String user_name) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.User where user_name = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 参数设置
		query.setParameter(0, user_name);
		// 获取查询结果
		User u = (User) query.uniqueResult();
		return u;
	}

	// 以邮箱查询用户方法
	@Override
	public User get_by_eamil(String user_email) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.User where user_email = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 参数设置
		query.setParameter(0, user_email);
		// 获取查询结果
		User u = (User) query.uniqueResult();
		return u;
	}

	// 用户修改
	@Override
	public void modify(User user) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 用户修改
		session.update(user);

	}

	// 插叙指定user_nickname的用户
	@Override
	public User get_by_nickname(String user_nickname) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.User where user_nickname = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 参数设置
		query.setParameter(0, user_nickname);
		// 获取查询结果
		User u = (User) query.uniqueResult();

		return u;

	}

}