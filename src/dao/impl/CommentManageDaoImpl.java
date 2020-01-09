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
 * 评论数据管理实现类
 */
public class CommentManageDaoImpl implements CommentManageDao {

	// 新增评论方法
	@Override
	public void add(Comment comment, Long blog_id) {

		// 获取blog数据管理对象
		BlogManageDao bmd = new BlogManageDaoImpl();
		// 获取当前user对象
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		// 获取当前blog对象
		Blog blog = bmd.get_by_id(blog_id);
		// 设置评论与用户和博客关联
		blog.getComments().add(comment);
		comment.setBlog(blog);
		comment.setUser(currentuser);
	}

	// 评论删除方法
	@Override
	public void del(Comment comment, Long blog_id) {
		// 新建博客数据管理类对象
		BlogManageDao bmd = new BlogManageDaoImpl();
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 获取当前博客对象
		Blog blog = bmd.get_by_id(blog_id);
		// 删除关系
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
		// 删除评论
		session.delete(c);

	}

	// 查询指定blog_id博客下的评论
	@Override
	public Set<Comment> get_by_blog_id(Long blog_id) {

		// 获取blog数据管理对象
		BlogManageDao bmd = new BlogManageDaoImpl();
		// 获取blog对象
		Blog blog = bmd.get_by_id(blog_id);
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 获取评论集
		session.saveOrUpdate(blog);
		Set<Comment> comments = blog.getComments();

		return comments;
	}

	// 查询指定comment_id的评论
	@Override
	public Comment get_by_comment_id(Long comment_id) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.Comment where comment_id = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter(0, comment_id);
		// 获取查询结果
		Comment c = (Comment) query.uniqueResult();
		return c;
	}

	// 查询指定blog_id下的评论数据总条数
	@Override
	public int getAllRowCount(Long blog_id) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.Comment where blog_id = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 参数设置
		query.setParameter(0, blog_id);
		// 获取查询结果
		int allrowcount = query.list().size();
		return allrowcount;
	}

	// 分页查询指定blog_id下的评论
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> get_by_page(Long blog_id, int offset, int pageSize) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.Comment where blog_id = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter(0, blog_id);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		// 获取查询结果
		List<Comment> comments = query.list();
		return comments;

	}

}
