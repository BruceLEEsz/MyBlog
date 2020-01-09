package dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.opensymphony.xwork2.ActionContext;

import Utils.HibernateSessionFactory;
import dao.FriendManageDao;
import domain.User;

/**
 * �������ݹ���ʵ����
 */
public class FriendManageDaoImpl implements FriendManageDao {

	// ��������
	@Override
	public void add(User user) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��ȡ��ǰ��������
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		session.saveOrUpdate(currentuser);
		// ��������
		currentuser.getFriends().add(user);
	}

	// ����ɾ������
	@Override
	public void delete(User user) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// �½�sql��ѯ����
		SQLQuery query = session
				.createSQLQuery("select * from tb_user where user_nickname= '" + user.getUser_nickname() + "'");
		query.addScalar("user_id", StandardBasicTypes.LONG);
		Long id = (Long) query.uniqueResult();
		session.createSQLQuery("delete from tb_friend where friends_id=" + id).executeUpdate();
	}

}
