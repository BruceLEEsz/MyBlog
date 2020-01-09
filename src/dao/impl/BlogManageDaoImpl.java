package dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;

import Utils.HibernateSessionFactory;
import dao.BlogManageDao;
import dao.CommentManageDao;
import dao.UserManageDao;
import domain.Blog;
import domain.Comment;
import domain.User;

/**
 * �������ݹ���ʵ����
 */
public class BlogManageDaoImpl implements BlogManageDao {

	// ��������
	@Override
	public void add(Blog blog) {
		// �û����ݹ��������
		UserManageDao umd = new UserManageDaoImpl();
		// ��ȡ��ǰuser����
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		User u = umd.get_by_name(currentuser.getUser_name());
		// ��������
		u.getBlogs().add(blog);
		blog.setUser(u);

	}

	// ��blog_id��ѯ����
	@Override
	public Blog get_by_id(Long blog_id) {

		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.Blog where blog_id = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ���ò���
		query.setParameter(0, blog_id);
		// ��ȡ����
		Blog blog = (Blog) query.uniqueResult();
		// ���ز���
		return blog;

	}

	// �޸Ĳ��ͷ���
	@Override
	public void alter(Blog blog) {

		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(blog);

	}

	// ɾ�����ͷ���
	@Override
	public void delete(Blog blog) {
		// ��ȡ�������ݹ������
		CommentManageDao cmd = new CommentManageDaoImpl();
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// �־û�blog����
		session.saveOrUpdate(blog);
		// ��ȡ�û�����
		User user = blog.getUser();
		// ��ȡ���ۼ�
		Set<Comment> comments = blog.getComments();
		Iterator<Comment> it = comments.iterator();
		// ɾ������
		while (it.hasNext()) {
			Comment c = it.next();
			it.remove();
			cmd.del(c, blog.getBlog_id());
		}
		// ɾ������
		user.getBlogs().remove(blog);
		session.delete(blog);
	}

	// ��ʱ�併���ѯ����
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> query() {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.Blog order by add_time desc";
		// ������ѯ����
		Query query = session.createQuery(hql);
		// ��ȡ��ѯ���
		List<Blog> blogs = query.list();
		return blogs;

	}

	// ��ҳ��ѯ
	@SuppressWarnings("unchecked")
	public List<Blog> query_by_page(String user_nickname, int offset, int pageSize) {
		// �½��û����ݹ��������
		UserManageDao umd = new UserManageDaoImpl();
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��ȡ�û�����
		User user = umd.get_by_nickname(user_nickname);
		// hql���
		String hql = "from domain.Blog where user_id = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ���÷�ҳ����
		query.setParameter(0, user.getUser_id());
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		// ��ȡ��ѯ���
		List<Blog> blogs = query.list();
		return blogs;
	}

	// ��ѯָ���û��Ĳ�������������
	@Override
	public int getAllRowCount(String user_nickname) {
		// �õ�������
		int allRows = 0;
		// �û����ݹ��������
		UserManageDao umd = new UserManageDaoImpl();
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.Blog where user_id = ?";
		// ��ȡ�û�����
		User user = umd.get_by_nickname(user_nickname);
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ���ò���
		query.setParameter(0, user.getUser_id());
		// ȡ�ò�ѯ���
		allRows = query.list().size();
		return allRows;

	}

}
