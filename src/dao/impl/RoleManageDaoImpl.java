package dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import Utils.HibernateSessionFactory;
import dao.RoleManageDao;
import domain.Role;

/**
 * ��ɫ���ݹ���ʵ����
 */
public class RoleManageDaoImpl implements RoleManageDao {

	// �����û���ɫ����
	@Override
	public Role getUser() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql����
		String hql = "from domain.Role where role_name = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ���ò���
		query.setParameter(0, "user");
		// ��ȡ������
		Role r_user = (Role) query.uniqueResult();
		return r_user;
	}

	// ������ɫ
	@Override
	public void add_user(Role role) {
		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(role);
	}

	// ����Moderator��ɫ����
	@Override
	public Role getModerator() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql����
		String hql = "from domain.Role where role_name = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ��������
		query.setParameter(0, "moderator");
		// ��ȡ��ѯ���
		Role moderator = (Role) query.uniqueResult();
		return moderator;

	}

}
