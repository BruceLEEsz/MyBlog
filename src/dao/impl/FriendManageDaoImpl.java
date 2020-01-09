package dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.opensymphony.xwork2.ActionContext;

import Utils.HibernateSessionFactory;
import dao.FriendManageDao;
import domain.User;

/**
 * 好友数据管理实现类
 */
public class FriendManageDaoImpl implements FriendManageDao {

	// 新增方法
	@Override
	public void add(User user) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 获取当前操作对象
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		session.saveOrUpdate(currentuser);
		// 新增好友
		currentuser.getFriends().add(user);
	}

	// 好友删除方法
	@Override
	public void delete(User user) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 新建sql查询对象
		SQLQuery query = session
				.createSQLQuery("select * from tb_user where user_nickname= '" + user.getUser_nickname() + "'");
		query.addScalar("user_id", StandardBasicTypes.LONG);
		Long id = (Long) query.uniqueResult();
		session.createSQLQuery("delete from tb_friend where friends_id=" + id).executeUpdate();
	}

}
