package service.impl;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import Utils.HibernateSessionFactory;
import Utils.Exception.PostBlogException;
import dao.BlogManageDao;
import dao.impl.BlogManageDaoImpl;
import domain.Blog;
import domain.User;
import service.BlogManageService;

/**
 * ���͹���ҵ���߼�ʵ����
 */
public class BlogManageServiceImpl implements BlogManageService {
	
	// �������ݹ��������
	private BlogManageDao bmd = new BlogManageDaoImpl();

	// �������ͷ���
	@Override
	public void post(Blog blog) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��������
		Transaction transaction = session.beginTransaction();
		// ���ͱ��ⲻΪ��
		if (!blog.getTitle().equals("")) {
			// ��������ʱ��
			blog.setAdd_time(new Date());
			// ����Dao��
			bmd.add(blog);
		}
		// ���ͱ���Ϊ��
		else {
			// ���ò������׳�������Ϣ
			ActionContext.getContext().put("blog", blog);
			ServletActionContext.getRequest().setAttribute("modify", true);
			throw new PostBlogException("�����벩�ͱ���");
		}

		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

	}

	// ��blog_id��ȡblog����
	@Override
	public void qurey_by_id(Long blog_id) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ����Dao�㣬��ѯblog����
		Blog blog = bmd.get_by_id(blog_id);
		// ���ò���
		ActionContext.getContext().put("blog", blog);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// �޸Ĳ��ͷ���
	@Override
	public void modify(Blog blog, Long blog_id) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ��ѯblog����
		Blog beforeblog = bmd.get_by_id(blog_id);
		// ����blog����
		beforeblog.setTitle(blog.getTitle());
		beforeblog.setBlog_content(blog.getBlog_content());
		beforeblog.setAdd_time(new Date());
		// �ύDao���޸�
		bmd.alter(beforeblog);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

	}

	// ɾ�����ͷ���
	@Override
	public String delete(Long blog_id) {

		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ������Dao�㣬��ѯҪɾ����blog����
		Blog blog = bmd.get_by_id(blog_id);
		User user = blog.getUser();
		session.saveOrUpdate(user);
		String user_nickname = user.getUser_nickname();
		// ����Dao�㣬ʵ��ɾ���߼�
		bmd.delete(blog);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

		return user_nickname;

	}

}
