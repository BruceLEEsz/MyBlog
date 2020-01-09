package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.ProcessManageService;
import service.impl.ProcessManageServiceImpl;

/**
 * 流程控制Action类，用于返回进入主要页面时需要显示的对象。
 */
public class ProcessManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	// 流程控制业务逻辑类对象
	private ProcessManageService pms = new ProcessManageServiceImpl();

	// 显示博客展示页面
	public String blog() {
		// 参数获取
		String page1 = ServletActionContext.getRequest().getParameter("page");
		Integer page2 = (Integer) ServletActionContext.getRequest().getAttribute("page");
		Integer page = (page1 != null ? new Integer(page1) : page2);
		Long blog_id = new Long(ServletActionContext.getRequest().getParameter("blog_id"));
		// 调用service层
		pms.showblog(blog_id, page);
		return "toblog";

	}

	// 显示个人信息页面
	public String main() {
		// 参数获取
		String u_nickname1 = (String) ServletActionContext.getRequest().getAttribute("user_nickname");
		String u_nickname2 = ServletActionContext.getRequest().getParameter("user_nickname");
		String user_nickname = (u_nickname1 != null ? u_nickname1 : u_nickname2);
		String page1 = ServletActionContext.getRequest().getParameter("page");
		Integer page2 = (Integer) ServletActionContext.getRequest().getAttribute("page");
		Integer page = (page1 != null ? new Integer(page1) : page2);
		// 调用service层
		pms.showmain(user_nickname, page);
		return "tomain";

	}

	// 显示主页面
	public String index() {
		// 调用service层
		pms.showindex();

		return "toindex";

	}

}
