package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import Utils.MailUtil;
import Utils.Mail.ConfirmEmail;
import Utils.Mail.ModifyPwEmail;
import domain.User;
import service.UserManageService;
import service.impl.UserManageSeviceImpl;

/**
 * 用户管理Action类，用于处理账户相关的请求
 */
public class AuthorManageAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	// 用户对象，接收页面传入的参数
	private User user = new User();
	// 用户管理service对象
	private UserManageService ums = new UserManageSeviceImpl();

	// 注册方法
	public String register() {
		// 调用service层实现注册逻辑
		User u = ums.register(user);
		// 注册成功，返回提示信息
		ActionContext.getContext().getSession().put("currentuser", u);
		ActionContext.getContext().getContextMap().put("message", "注册成功！我们已发送一封确认邮件到您的注册邮箱。");
		return "chainindex";

	}

	// 登录方法
	public String login() {
		// 调用service层实现登录功能
		User u = ums.login(user);
		// 登录成功
		ActionContext.getContext().getSession().put("currentuser", u);
		// 检查账户确认情况
		if (!u.getConfirmed()) {
			// 未确认，转到主页，给予提示信息
			new MailUtil().sendto(u.getUser_email(), new ConfirmEmail(u));
			ActionContext.getContext().getContextMap().put("message", "账户未确认！我们已重新发送了一封确认邮件到您的注册邮箱。");
		}
		return "chainindex";
	}

	// 注销方法
	public String logout() {
		// 清空session对象，注销账户
		ActionContext.getContext().getSession().clear();

		return "login";

	}

	// 账户验证方法
	public String confirm() {
		// 获取验证参数
		String user_name = ServletActionContext.getRequest().getParameter("user_name");
		Boolean modifypw = new Boolean(ServletActionContext.getRequest().getParameter("modifypw"));
		// 通过session对象获取当前user对象
		User u = (User) ActionContext.getContext().getSession().get("currentuser");
		// 调用service实现账户验证功能
		ums.confirm(u, user_name);
		// 验证成功，返回
		if (!modifypw)
			return "tosetinfo";
		else
			return "tosetpw";

	}

	// 账户个人信息修改方法
	public String modifyinfo() {
		// 调用service实现信息修改逻辑
		ums.modfiyinfo(user);
		return "chainindex";

	}

	// 密码修改
	public String modifypw() {
		/// 获取参数
		User u = (User) ActionContext.getContext().getSession().get("currentuser");
		Boolean hadsendmail = new Boolean(ServletActionContext.getRequest().getParameter("hadsendmail"));
		if (!hadsendmail) {
			// 确认邮件未发送，发送邮件
			new MailUtil().sendto(u.getUser_email(), new ModifyPwEmail(u));
		} else {
			// 否则，调用service层修改密码
			ums.modifypw(u, user);
		}

		return "chainindex";
	}

	// 用户重置密码前的操作
	public String findpw() {
		// 取得参数
		String email = ServletActionContext.getRequest().getParameter("email");
		// 调用service层
		ums.findpw(email);

		return "tologin";
	}

	// 重置密码
	public String resetpw() {
		// 调用service层
		ums.resetpw(user);
		return "tologin";
	}

	// 设置协管员方法
	public String setmoderator() {
		// 取得参数
		String user_nickname = ServletActionContext.getRequest().getParameter("user_nickname");
		// 调用service层
		ums.setmoderator(user_nickname);
		// 设置返回参数
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";

	}

	// 设置普通用户方法
	public String setuser() {
		// 取得参数
		String user_nickname = ServletActionContext.getRequest().getParameter("user_nickname");
		// 调用service层
		ums.setuser(user_nickname);
		// 设置返回参数
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";

	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
