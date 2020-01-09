package service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Utils.HibernateSessionFactory;
import dao.FriendManageDao;
import dao.UserManageDao;
import dao.impl.FriendManageDaoImpl;
import dao.impl.UserManageDaoImpl;
import domain.User;
import service.FriendManageService;

/**
 * 好友管理业务逻辑实现类
 */
public class FriendManageServiceImpl implements FriendManageService {
	// 好友数据管理类对象
	private FriendManageDao fmd = new FriendManageDaoImpl();
	// 用户数据管理类对象
	private UserManageDao umd = new UserManageDaoImpl();

	// 新增好友方法
	@Override
	public void add(String user_nickname) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 取得要增加为好友的用户
		User user = umd.get_by_nickname(user_nickname);
		// 调用Dao层
		fmd.add(user);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 删除好友方法
	@Override
	public void delete(String user_nickname) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 取得要删除的用户对象
		User user = umd.get_by_nickname(user_nickname);
		// 调用Dao层
		fmd.delete(user);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

}