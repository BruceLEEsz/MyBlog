package dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateSessionFactory;
import dao.UserManageDao;
import domain.User;

/**
 * �û����ݹ���ʵ����
 */
public class UserManageDaoImpl implements UserManageDao {

	// �����û�����
	@Override
	public void add(User user) {

		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// �����û�����
		session.save(user);

	}

	// ���û�����ѯ�û�����
	@Override
	public User get_by_name(String user_name) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.User where user_name = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ��������
		query.setParameter(0, user_name);
		// ��ȡ��ѯ���
		User u = (User) query.uniqueResult();
		return u;
	}

	// �������ѯ�û�����
	@Override
	public User get_by_eamil(String user_email) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.User where user_email = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ��������
		query.setParameter(0, user_email);
		// ��ȡ��ѯ���
		User u = (User) query.uniqueResult();
		return u;
	}

	// �û��޸�
	@Override
	public void modify(User user) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// �û��޸�
		session.update(user);

	}

	// ����ָ��user_nickname���û�
	@Override
	public User get_by_nickname(String user_nickname) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.User where user_nickname = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ��������
		query.setParameter(0, user_nickname);
		// ��ȡ��ѯ���
		User u = (User) query.uniqueResult();

		return u;

	}

}
