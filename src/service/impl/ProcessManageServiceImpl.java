package service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import Utils.HibernateSessionFactory;
import dao.BlogManageDao;
import dao.CommentManageDao;
import dao.UserManageDao;
import dao.impl.BlogManageDaoImpl;
import dao.impl.CommentManageDaoImpl;
import dao.impl.UserManageDaoImpl;
import domain.Blog;
import domain.Comment;
import Utils.Page;
import domain.User;
import service.ProcessManageService;

/**
 * 流程控制业务逻辑实现类
 */
public class ProcessManageServiceImpl implements ProcessManageService {
	// 博客数据管理类对象
	BlogManageDao bmd = new BlogManageDaoImpl();
	// 评论数据管理类对像
	CommentManageDao cmd = new CommentManageDaoImpl();
	// 用户数据管理类对象
	UserManageDao umd = new UserManageDaoImpl();

	// 显示博客展示页面
	@Override
	public void showblog(Long blog_id, Integer page) {
		// 创建分页页面信息类对象
		Page page_info = new Page();
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();

		// 处理当前操作对象信息
		// 取得当前操作用户对象
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		// 将当前用户转换为持久化对象
		//session.clear();
		//session.saveOrUpdate(currentuser);
		// 取得当前用户权限
		currentuser.getRole().getRole_authority();

		// 处理博客信息
		// 取得要展示的博客对象
		Blog blog = bmd.get_by_id(blog_id);
		// 获取博客作者对象及信息
		String nickname = blog.getUser().getUser_nickname();
		System.out.println(nickname);

		// 处理评论信息
		// 取得要展示博客下评论数据总条数
		int allRows = cmd.getAllRowCount(blog_id);
		// 计算总页数
		int totalPage = page_info.getTotalPages(5, allRows);
		// 得到当前页码
		int currentPage = page_info.getCurPage(page);
		// 计算偏移量
		int offset = page_info.getCurrentPageOffset(5, currentPage);
		// 封装参数
		page_info.setAllRows(allRows);
		page_info.setCurrentPage(currentPage);
		page_info.setTotalpage(totalPage);
		// 调用Dao层取得评论对象，具体信息
		List<Comment> comments = cmd.get_by_page(blog_id, offset, 5);
		Iterator<Comment> it = comments.iterator();
		while (it.hasNext()) {
			Comment c = it.next();
			User u = c.getUser();
			String user_nickname = u.getUser_nickname();
			System.out.println(user_nickname);
		}

		// 封装返回参数
		ActionContext.getContext().put("page_info", page_info);
		ActionContext.getContext().put("blog", blog);
		ActionContext.getContext().put("comments", comments);
		ServletActionContext.getRequest().setAttribute("comments", comments);

		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 显示个人信息页面
	@Override
	public void showmain(String user_nickname, Integer page) {
		// 创建分页页面信息对象
		Page page_info = new Page();
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();

		// 处理当前操作对象
		// 取得当前操作对象
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		User cuser = umd.get_by_nickname(currentuser.getUser_nickname());
		// 取得当前操作对象好友集，处理好友信息
		Set<User> cu_friends = cuser.getFriends();
		Iterator<User> cuit = cu_friends.iterator();
		while (cuit.hasNext()) {
			User u = cuit.next();
			String u_nickname = u.getUser_nickname();
			System.out.println(u_nickname);
		}

		// 处理要显示用户对象信息
		// 取得要显示用户对象
		User user = umd.get_by_nickname(user_nickname);
		// 处理权限信息
		user.getRole().getRole_authority();
		// 取得用户好友集，处理好友信息
		Set<User> friends = user.getFriends();
		Iterator<User> uit = friends.iterator();
		while (uit.hasNext()) {
			User u = uit.next();
			String u_nickname = u.getUser_nickname();
			System.out.println(u_nickname);
		}

		// 处理要显示用户的博客信息
		// 计算博客数据总条数
		int allRows = bmd.getAllRowCount(user_nickname);
		// 计算总页数
		int totalPage = page_info.getTotalPages(4, allRows);
		// 得到当前页码
		int currentPage = page_info.getCurPage(page);
		/// 计算偏移量
		int offset = page_info.getCurrentPageOffset(4, currentPage);
		// 封装参数
		page_info.setAllRows(allRows);
		page_info.setCurrentPage(currentPage);
		page_info.setTotalpage(totalPage);
		// 取得博客数据 集
		List<Blog> blogs = bmd.query_by_page(user_nickname, offset, 4);
		// 处理博客对象
		Iterator<Blog> bit = blogs.iterator();
		while (bit.hasNext()) {
			Blog b = bit.next();
			System.out.println(b);
		}

		// 封装返回参数
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("page_info", page_info);
		ServletActionContext.getRequest().setAttribute("blogs", blogs);
		ServletActionContext.getRequest().setAttribute("friends", friends);
		ServletActionContext.getRequest().setAttribute("cufriends", cu_friends);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 显示主页面
	@Override
	public void showindex() {
		// 取得session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 调用Dao层，取得博客数据集，处理博客数据
		List<Blog> blogs = bmd.query();
		Iterator<Blog> it = blogs.iterator();
		while (it.hasNext()) {
			Blog b = it.next();
			User u = b.getUser();
			String user_nickname = u.getUser_name();
			System.out.println(user_nickname);
		}
		// 封装返回参数
		ServletActionContext.getRequest().setAttribute("blogs", blogs);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

	}

}
