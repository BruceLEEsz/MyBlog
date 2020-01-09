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
 * ���̿���ҵ���߼�ʵ����
 */
public class ProcessManageServiceImpl implements ProcessManageService {
	// �������ݹ��������
	BlogManageDao bmd = new BlogManageDaoImpl();
	// �������ݹ��������
	CommentManageDao cmd = new CommentManageDaoImpl();
	// �û����ݹ��������
	UserManageDao umd = new UserManageDaoImpl();

	// ��ʾ����չʾҳ��
	@Override
	public void showblog(Long blog_id, Integer page) {
		// ������ҳҳ����Ϣ�����
		Page page_info = new Page();
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();

		// ����ǰ����������Ϣ
		// ȡ�õ�ǰ�����û�����
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		// ����ǰ�û�ת��Ϊ�־û�����
		//session.clear();
		//session.saveOrUpdate(currentuser);
		// ȡ�õ�ǰ�û�Ȩ��
		currentuser.getRole().getRole_authority();

		// ��������Ϣ
		// ȡ��Ҫչʾ�Ĳ��Ͷ���
		Blog blog = bmd.get_by_id(blog_id);
		// ��ȡ�������߶�����Ϣ
		String nickname = blog.getUser().getUser_nickname();
		System.out.println(nickname);

		// ����������Ϣ
		// ȡ��Ҫչʾ��������������������
		int allRows = cmd.getAllRowCount(blog_id);
		// ������ҳ��
		int totalPage = page_info.getTotalPages(5, allRows);
		// �õ���ǰҳ��
		int currentPage = page_info.getCurPage(page);
		// ����ƫ����
		int offset = page_info.getCurrentPageOffset(5, currentPage);
		// ��װ����
		page_info.setAllRows(allRows);
		page_info.setCurrentPage(currentPage);
		page_info.setTotalpage(totalPage);
		// ����Dao��ȡ�����۶��󣬾�����Ϣ
		List<Comment> comments = cmd.get_by_page(blog_id, offset, 5);
		Iterator<Comment> it = comments.iterator();
		while (it.hasNext()) {
			Comment c = it.next();
			User u = c.getUser();
			String user_nickname = u.getUser_nickname();
			System.out.println(user_nickname);
		}

		// ��װ���ز���
		ActionContext.getContext().put("page_info", page_info);
		ActionContext.getContext().put("blog", blog);
		ActionContext.getContext().put("comments", comments);
		ServletActionContext.getRequest().setAttribute("comments", comments);

		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// ��ʾ������Ϣҳ��
	@Override
	public void showmain(String user_nickname, Integer page) {
		// ������ҳҳ����Ϣ����
		Page page_info = new Page();
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();

		// ����ǰ��������
		// ȡ�õ�ǰ��������
		User currentuser = (User) ActionContext.getContext().getSession().get("currentuser");
		User cuser = umd.get_by_nickname(currentuser.getUser_nickname());
		// ȡ�õ�ǰ����������Ѽ������������Ϣ
		Set<User> cu_friends = cuser.getFriends();
		Iterator<User> cuit = cu_friends.iterator();
		while (cuit.hasNext()) {
			User u = cuit.next();
			String u_nickname = u.getUser_nickname();
			System.out.println(u_nickname);
		}

		// ����Ҫ��ʾ�û�������Ϣ
		// ȡ��Ҫ��ʾ�û�����
		User user = umd.get_by_nickname(user_nickname);
		// ����Ȩ����Ϣ
		user.getRole().getRole_authority();
		// ȡ���û����Ѽ������������Ϣ
		Set<User> friends = user.getFriends();
		Iterator<User> uit = friends.iterator();
		while (uit.hasNext()) {
			User u = uit.next();
			String u_nickname = u.getUser_nickname();
			System.out.println(u_nickname);
		}

		// ����Ҫ��ʾ�û��Ĳ�����Ϣ
		// ���㲩������������
		int allRows = bmd.getAllRowCount(user_nickname);
		// ������ҳ��
		int totalPage = page_info.getTotalPages(4, allRows);
		// �õ���ǰҳ��
		int currentPage = page_info.getCurPage(page);
		/// ����ƫ����
		int offset = page_info.getCurrentPageOffset(4, currentPage);
		// ��װ����
		page_info.setAllRows(allRows);
		page_info.setCurrentPage(currentPage);
		page_info.setTotalpage(totalPage);
		// ȡ�ò������� ��
		List<Blog> blogs = bmd.query_by_page(user_nickname, offset, 4);
		// �����Ͷ���
		Iterator<Blog> bit = blogs.iterator();
		while (bit.hasNext()) {
			Blog b = bit.next();
			System.out.println(b);
		}

		// ��װ���ز���
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("page_info", page_info);
		ServletActionContext.getRequest().setAttribute("blogs", blogs);
		ServletActionContext.getRequest().setAttribute("friends", friends);
		ServletActionContext.getRequest().setAttribute("cufriends", cu_friends);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// ��ʾ��ҳ��
	@Override
	public void showindex() {
		// ȡ��session����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ����Dao�㣬ȡ�ò������ݼ�������������
		List<Blog> blogs = bmd.query();
		Iterator<Blog> it = blogs.iterator();
		while (it.hasNext()) {
			Blog b = it.next();
			User u = b.getUser();
			String user_nickname = u.getUser_name();
			System.out.println(user_nickname);
		}
		// ��װ���ز���
		ServletActionContext.getRequest().setAttribute("blogs", blogs);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

	}

}
