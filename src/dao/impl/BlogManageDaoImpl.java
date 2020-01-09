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


public class BlogManageDaoImpl implements BlogManageDao {

	
	@Override
	public void add(Blog blog) {
		
		UserManageDao umd = new UserManageDaoImpl();
		
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		User u = umd.get_by_name(currentuser.getUser_name());
	
		u.getBlogs().add(blog);
		blog.setUser(u);

	}


	@Override
	public Blog get_by_id(Long blog_id) {

		
		Session session = HibernateSessionFactory.getSession();
	
		String hql = "from domain.Blog where blog_id = ?";

		Query query = session.createQuery(hql);
	
		query.setParameter(0, blog_id);

		Blog blog = (Blog) query.uniqueResult();

		return blog;

	}


	@Override
	public void alter(Blog blog) {

		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(blog);

	}

	
	@Override
	public void delete(Blog blog) {
		
		CommentManageDao cmd = new CommentManageDaoImpl();
		
		Session session = HibernateSessionFactory.getSession();

		session.saveOrUpdate(blog);
	
		User user = blog.getUser();
	
		Set<Comment> comments = blog.getComments();
		Iterator<Comment> it = comments.iterator();
		
		while (it.hasNext()) {
			Comment c = it.next();
			it.remove();
			cmd.del(c, blog.getBlog_id());
		}
	
		user.getBlogs().remove(blog);
		session.delete(blog);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> query() {
		
		Session session = HibernateSessionFactory.getSession();
	
		String hql = "from domain.Blog order by add_time desc";
	
		Query query = session.createQuery(hql);

		List<Blog> blogs = query.list();
		return blogs;

	}


	@SuppressWarnings("unchecked")
	public List<Blog> query_by_page(String user_nickname, int offset, int pageSize) {
		
		UserManageDao umd = new UserManageDaoImpl();
	
		Session session = HibernateSessionFactory.getSession();

		User user = umd.get_by_nickname(user_nickname);
		
		String hql = "from domain.Blog where user_id = ?";
	
		Query query = session.createQuery(hql);

		query.setParameter(0, user.getUser_id());
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
	
		List<Blog> blogs = query.list();
		return blogs;
	}

	
	@Override
	public int getAllRowCount(String user_nickname) {

		int allRows = 0;
	
		UserManageDao umd = new UserManageDaoImpl();
	
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "from domain.Blog where user_id = ?";
	
		User user = umd.get_by_nickname(user_nickname);
	
		Query query = session.createQuery(hql);
		
		query.setParameter(0, user.getUser_id());
		
		allRows = query.list().size();
		return allRows;

	}

}
