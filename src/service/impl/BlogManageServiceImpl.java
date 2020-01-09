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
 * 博客管理业务逻辑实现类
 */
public class BlogManageServiceImpl implements BlogManageService {
	
	// 博客数据管理类对象
	private BlogManageDao bmd = new BlogManageDaoImpl();

	// 新增博客方法
	@Override
	public void post(Blog blog) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 博客标题不为空
		if (!blog.getTitle().equals("")) {
			// 设置新增时间
			blog.setAdd_time(new Date());
			// 调用Dao层
			bmd.add(blog);
		}
		// 博客标题为空
		else {
			// 设置参数，抛出错误信息
			ActionContext.getContext().put("blog", blog);
			ServletActionContext.getRequest().setAttribute("modify", true);
			throw new PostBlogException("请输入博客标题");
		}

		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

	}

	// 以blog_id获取blog对象
	@Override
	public void qurey_by_id(Long blog_id) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 调用Dao层，查询blog对象
		Blog blog = bmd.get_by_id(blog_id);
		// 放置参数
		ActionContext.getContext().put("blog", blog);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 修改博客方法
	@Override
	public void modify(Blog blog, Long blog_id) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 查询blog对象
		Blog beforeblog = bmd.get_by_id(blog_id);
		// 重置blog属性
		beforeblog.setTitle(blog.getTitle());
		beforeblog.setBlog_content(blog.getBlog_content());
		beforeblog.setAdd_time(new Date());
		// 提交Dao层修改
		bmd.alter(beforeblog);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

	}

	// 删除博客方法
	@Override
	public String delete(Long blog_id) {

		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 调用用Dao层，查询要删除的blog对象
		Blog blog = bmd.get_by_id(blog_id);
		User user = blog.getUser();
		session.saveOrUpdate(user);
		String user_nickname = user.getUser_nickname();
		// 调用Dao层，实现删除逻辑
		bmd.delete(blog);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

		return user_nickname;

	}

}
