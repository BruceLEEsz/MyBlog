package service.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Utils.HibernateSessionFactory;
import dao.CommentManageDao;
import dao.impl.CommentManageDaoImpl;
import domain.Comment;
import service.CommentManageService;

/**
 * ���۹���ҵ���߼�ʵ����
 */
public class CommentManageServiceImpl implements CommentManageService {
	
	// �������ݹ��������
	private CommentManageDao cmd = new CommentManageDaoImpl();

	// ���۷�����
	@Override
	public void post(Comment comment, Long blog_id) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// �������۷���ʱ��
		comment.setAdd_time(new Date());
		// �ύDao
		cmd.add(comment, blog_id);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// ����ɾ������
	@Override
	public void delete(Comment comment, Long blog_id) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ����dao��
		cmd.del(comment, blog_id);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

	}

}
