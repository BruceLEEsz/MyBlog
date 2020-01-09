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
 * ���ѹ���ҵ���߼�ʵ����
 */
public class FriendManageServiceImpl implements FriendManageService {
	// �������ݹ��������
	private FriendManageDao fmd = new FriendManageDaoImpl();
	// �û����ݹ��������
	private UserManageDao umd = new UserManageDaoImpl();

	// �������ѷ���
	@Override
	public void add(String user_nickname) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ȡ��Ҫ����Ϊ���ѵ��û�
		User user = umd.get_by_nickname(user_nickname);
		// ����Dao��
		fmd.add(user);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// ɾ�����ѷ���
	@Override
	public void delete(String user_nickname) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ȡ��Ҫɾ�����û�����
		User user = umd.get_by_nickname(user_nickname);
		// ����Dao��
		fmd.delete(user);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

}
