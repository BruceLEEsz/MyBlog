package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.FriendManageService;
import service.impl.FriendManageServiceImpl;

/**
 * 好友管理Action类，用于处理与好友管理有关的请求
 */
public class FriendManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	// 好友管理service对象
	private FriendManageService fms = new FriendManageServiceImpl();

	// 新增好友
	public String add() {
		// 取得参数
		String user_nickname = ServletActionContext.getRequest().getParameter("user_nickname");
		// 调用service层
		fms.add(user_nickname);
		// 设置返回参数
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";
	}

	// 删除好友
	public String delete() {
		// 取得参数
		String user_nickname = ServletActionContext.getRequest().getParameter("user_nickname");
		// 调用service层
		fms.delete(user_nickname);
		// 设置返回参数
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";
	}

}
