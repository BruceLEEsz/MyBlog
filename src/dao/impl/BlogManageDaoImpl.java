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
 * 博客数据管理实现类
 */
public class BlogManageDaoImpl implements BlogManageDao {

	// 新增方法
	@Override
	public void add(Blog blog) {
		// 用户数据管理类对象
		UserManageDao umd = new UserManageDaoImpl();
		// 获取当前user对象
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		User u = umd.get_by_name(currentuser.getUser_name());
		// 新增博客
		u.getBlogs().add(blog);
		blog.setUser(u);

	}

	// 以blog_id查询方法
	@Override
	public Blog get_by_id(Long blog_id) {

		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.Blog where blog_id = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter(0, blog_id);
		// 获取对象
		Blog blog = (Blog) query.uniqueResult();
		// 返回参数
		return blog;

	}

	// 修改博客方法
	@Override
	public void alter(Blog blog) {

		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(blog);

	}

	// 删除博客方法
	@Override
	public void delete(Blog blog) {
		// 获取评论数据管理对象
		CommentManageDao cmd = new CommentManageDaoImpl();
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 持久化blog对象
		session.saveOrUpdate(blog);
		// 获取用户对象
		User user = blog.getUser();
		// 获取评论集
		Set<Comment> comments = blog.getComments();
		Iterator<Comment> it = comments.iterator();
		// 删除评论
		while (it.hasNext()) {
			Comment c = it.next();
			it.remove();
			cmd.del(c, blog.getBlog_id());
		}
		// 删除博客
		user.getBlogs().remove(blog);
		session.delete(blog);
	}

	// 按时间降序查询博客
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> query() {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.Blog order by add_time desc";
		// 创建查询对象
		Query query = session.createQuery(hql);
		// 获取查询结果
		List<Blog> blogs = query.list();
		return blogs;

	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<Blog> query_by_page(String user_nickname, int offset, int pageSize) {
		// 新建用户数据管理类对象
		UserManageDao umd = new UserManageDaoImpl();
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 获取用户对象
		User user = umd.get_by_nickname(user_nickname);
		// hql语句
		String hql = "from domain.Blog where user_id = ?";
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 设置分页参数
		query.setParameter(0, user.getUser_id());
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		// 获取查询结果
		List<Blog> blogs = query.list();
		return blogs;
	}

	// 查询指定用户的博客数据总条数
	@Override
	public int getAllRowCount(String user_nickname) {
		// 得到总条数
		int allRows = 0;
		// 用户数据管理类对象
		UserManageDao umd = new UserManageDaoImpl();
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// hql语句
		String hql = "from domain.Blog where user_id = ?";
		// 获取用户对象
		User user = umd.get_by_nickname(user_nickname);
		// 新建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter(0, user.getUser_id());
		// 取得查询结果
		allRows = query.list().size();
		return allRows;

	}

}
