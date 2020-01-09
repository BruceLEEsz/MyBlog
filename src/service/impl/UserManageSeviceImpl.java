package service.impl;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import Utils.HibernateSessionFactory;
import Utils.MailUtil;
import Utils.Exception.LoginException;
import Utils.Exception.ModifyInfoException;
import Utils.Exception.ModifyPwException;
import Utils.Exception.RegisterException;
import Utils.Mail.ConfirmEmail;
import Utils.Mail.ResetPwEmail;
import dao.RoleManageDao;
import dao.UserManageDao;
import dao.impl.RoleManageDaoImpl;
import dao.impl.UserManageDaoImpl;
import domain.Role;
import domain.User;
import service.InfoCheck;
import service.UserManageService;

/**
 * 用户管理业务逻辑实现类
 */
public class UserManageSeviceImpl implements UserManageService {
	// 用户相关信息检查实现类对象
	InfoCheck ic = new InfoCheckImpl();
	// 用户数据管理类对象
	UserManageDao umd = new UserManageDaoImpl();
	// 角色数据管理类对象
	RoleManageDao rmd = new RoleManageDaoImpl();

	// 实现注册功能
	@Override
	public User register(User user) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 检查用户名是否被注册
		if (!ic.checkname(user.getUser_name())) {
			throw new RegisterException("用户名已被注册");
		}
		// 检查邮箱是否被注册，邮箱格式是否正确
		switch (ic.checkmail(user.getUser_email())) {
		case "1":
			throw new RegisterException("邮箱已被注册");
		case "2":
			throw new RegisterException("邮箱格式不正确");
		default:
			break;
		}
		// 检查两次密码输入是否一致
		if (!ic.checkpassword(user.getUser_password(), user.getUser_password2())) {
			throw new RegisterException("两次密码输入不一致");
		}
		// 验证通过，调用数据层，新增客户。
		user.setConfirmed(false);
		user.setAdd_time(new Date());
		umd.add(user);
		Role r_user = rmd.getUser();
		r_user.getUsers().add(user);
		user.setRole(r_user);
		rmd.add_user(r_user);
		// 向用户邮箱发送确认邮件
		new MailUtil().sendto(user.getUser_email(), new ConfirmEmail(user));
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

		return user;

	}

	// 实现登录功能
	@Override
	public User login(User user) {
		User u = null;
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 检查登录名是否为邮箱
		if (ic.ismail(user.getUser_loginname())) {
			u = umd.get_by_eamil(user.getUser_loginname());
			// 检查邮箱是否注册
			if (u == null)
				throw new LoginException("邮箱不存在");
			// 检查密码是否匹配
			else if (u != null && !u.getUser_password().equals(user.getUser_password()))
				throw new LoginException("密码错误");
		} else {
			// 检查用户名是否注册
			if (ic.checkname(user.getUser_loginname()))
				throw new LoginException("用户名不存在");
			else {
				u = umd.get_by_name(user.getUser_loginname());
				// 检查密码是否匹配
				if (!u.getUser_password().equals(user.getUser_password()))
					throw new LoginException("密码错误");
			}
		}
		// 登录成功，取得用户权限数据
		u.getRole().getRole_authority();
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

		return u;
	}

	// 注册验证功能
	@Override
	public void confirm(User u, String user_name) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 执行验证
		if (u != null && u.getUser_name().equals(user_name)) {
			// 验证成功，设置账户为确认状态，调用Dao层保存
			if (!u.getConfirmed()) {
				u.setConfirmed(true);
				umd.modify(u);
			}
		} else
			throw new LoginException("请先登录。");
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 个人基本信息修改功能
	@Override
	public void modfiyinfo(User user) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 通过session获得当前user对像
		User u = (User) ActionContext.getContext().getSession().get("currentuser");
		User u2 = umd.get_by_nickname(user.getUser_nickname());
		// 检查要修改的昵称是否已存在。是则抛出异常。
		if (u2 != null && !u.getUser_name().equals(u2.getUser_name())) {
			throw new ModifyInfoException("昵称已存在！");
		} else if (user.getUser_nickname().equals("")) {
			throw new ModifyInfoException("昵称不能为空！");
		} else {
			// 信息修改
			u.setUser_nickname(user.getUser_nickname());
			u.setUser_age(user.getUser_age());
			u.setUser_gender(user.getUser_gender());
			u.setUser_birthday(user.getUser_birthday());
			u.setUser_address(user.getUser_address());
			u.setUser_hobby(user.getUser_hobby());
		}
		// 提交到Dao层处理
		session.clear();
		umd.modify(u);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 密码修改功能
	@Override
	public void modifypw(User currentuser, User user) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 检查两次输入的密码是否一致
		if (!ic.checkpassword(user.getUser_password(), user.getUser_password2())) {
			// 否，抛出异常
			throw new ModifyPwException("两次密码输入不一致");
		} else {
			// 是，设置参数，调用Dao保存修改
			currentuser.setUser_password(user.getUser_password());
			umd.modify(currentuser);
		}
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 设置协管员功能
	@Override
	public void setmoderator(String user_nickname) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 调用Dao层，取得要设置的用户对象
		User user = umd.get_by_nickname(user_nickname);
		// 取得Moderator角色对象
		Role moderator = rmd.getModerator();
		// 参数设置
		Role r_user = rmd.getUser();
		r_user.getUsers().remove(user);
		moderator.getUsers().add(user);
		user.setRole(moderator);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();
	}

	// 设置普通用户功能
	@Override
	public void setuser(String user_nickname) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 调用Dao层取得要修改的用户对象
		User user = umd.get_by_nickname(user_nickname);
		// 取得Moderator及User角色对象
		Role moderator = rmd.getModerator();
		Role r_user = rmd.getUser();
		// 参数设置
		moderator.getUsers().remove(user);
		r_user.getUsers().add(user);
		user.setRole(r_user);
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

	}

	// 重置密码
	@Override
	public void resetpw(User user) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 开启会话
		Transaction transaction = session.beginTransaction();
		// 通过邮箱获取用户对象
		User u = umd.get_by_eamil(user.getUser_email());
		// 检查两次密码输入是否一致
		if (ic.checkpassword(user.getUser_password(), user.getUser_password2())) {
			// 是，提交修改，返回提示信息
			u.setUser_password(user.getUser_password());
			ActionContext.getContext().put("message", "密码修改成功");
		} else {
			// 否，返回提示信息，抛出异常
			ServletActionContext.getRequest().setAttribute("resetpw", true);
			throw new ModifyPwException("两次密码输入不一致");
		}
		// 事务提交
		transaction.commit();
		// 资源关闭
		session.clear();
		session.close();

	}

	// 用户重置密码前的操作
	@Override
	public void findpw(String email) {
		// 获取session对象
		Session session = HibernateSessionFactory.getSession();
		// 事务开启
		Transaction transaction = session.beginTransaction();
		// 通过邮箱获取用户对象
		User u = umd.get_by_eamil(email);
		// 用户是否存在
		if (u != null) {
			// 存在调用Mail工具类发送邮件
			u.getUser_nickname();
			u.getUser_email();
			new MailUtil().sendto(email, new ResetPwEmail(u));
			ActionContext.getContext().put("message", "我们已发送了确认邮件到你的邮箱");
		} else {
			// 不存在，抛出异常，给出提示信息
			throw new LoginException("邮箱未注册");
		}
		// 事务提交
		transaction.commit();
		/// 资源关闭
		session.clear();
		session.close();
	}
}
