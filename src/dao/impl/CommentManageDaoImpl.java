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
import domain.Blog;
import domain.Comment;
import domain.User;

/**
 * �������ݹ���ʵ����
 */
public class CommentManageDaoImpl implements CommentManageDao {

	// �������۷���
	@Override
	public void add(Comment comment, Long blog_id) {

		// ��ȡblog���ݹ������
		BlogManageDao bmd = new BlogManageDaoImpl();
		// ��ȡ��ǰuser����
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		// ��ȡ��ǰblog����
		Blog blog = bmd.get_by_id(blog_id);
		// �����������û��Ͳ��͹���
		blog.getComments().add(comment);
		comment.setBlog(blog);
		comment.setUser(currentuser);
	}

	// ����ɾ������
	@Override
	public void del(Comment comment, Long blog_id) {
		// �½��������ݹ��������
		BlogManageDao bmd = new BlogManageDaoImpl();
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��ȡ��ǰ���Ͷ���
		Blog blog = bmd.get_by_id(blog_id);
		// ɾ����ϵ
		Iterator<Comment> it = blog.getComments().iterator();
		while (it.hasNext()) {
			Comment c = it.next();
			if (c.getComment_id().equals(comment.getComment_id())) {
				blog.getComments().remove(c);
				break;
			}
		}
		Comment c = get_by_comment_id(comment.getComment_id());
		c.setBlog(null);
		c.setUser(null);
		// ɾ������
		session.delete(c);

	}

	// ��ѯָ��blog_id�����µ�����
	@Override
	public Set<Comment> get_by_blog_id(Long blog_id) {

		// ��ȡblog���ݹ������
		BlogManageDao bmd = new BlogManageDaoImpl();
		// ��ȡblog����
		Blog blog = bmd.get_by_id(blog_id);
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ��ȡ���ۼ�
		session.saveOrUpdate(blog);
		Set<Comment> comments = blog.getComments();

		return comments;
	}

	// ��ѯָ��comment_id������
	@Override
	public Comment get_by_comment_id(Long comment_id) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.Comment where comment_id = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ���ò���
		query.setParameter(0, comment_id);
		// ��ȡ��ѯ���
		Comment c = (Comment) query.uniqueResult();
		return c;
	}

	// ��ѯָ��blog_id�µ���������������
	@Override
	public int getAllRowCount(Long blog_id) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.Comment where blog_id = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ��������
		query.setParameter(0, blog_id);
		// ��ȡ��ѯ���
		int allrowcount = query.list().size();
		return allrowcount;
	}

	// ��ҳ��ѯָ��blog_id�µ�����
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> get_by_page(Long blog_id, int offset, int pageSize) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// hql���
		String hql = "from domain.Comment where blog_id = ?";
		// �½���ѯ����
		Query query = session.createQuery(hql);
		// ���ò���
		query.setParameter(0, blog_id);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		// ��ȡ��ѯ���
		List<Comment> comments = query.list();
		return comments;

	}

}
