package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.ProcessManageService;
import service.impl.ProcessManageServiceImpl;

/**
 * ���̿���Action�࣬���ڷ��ؽ�����Ҫҳ��ʱ��Ҫ��ʾ�Ķ���
 */
public class ProcessManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	// ���̿���ҵ���߼������
	private ProcessManageService pms = new ProcessManageServiceImpl();

	// ��ʾ����չʾҳ��
	public String blog() {
		// ������ȡ
		String page1 = ServletActionContext.getRequest().getParameter("page");
		Integer page2 = (Integer) ServletActionContext.getRequest().getAttribute("page");
		Integer page = (page1 != null ? new Integer(page1) : page2);
		Long blog_id = new Long(ServletActionContext.getRequest().getParameter("blog_id"));
		// ����service��
		pms.showblog(blog_id, page);
		return "toblog";

	}

	// ��ʾ������Ϣҳ��
	public String main() {
		// ������ȡ
		String u_nickname1 = (String) ServletActionContext.getRequest().getAttribute("user_nickname");
		String u_nickname2 = ServletActionContext.getRequest().getParameter("user_nickname");
		String user_nickname = (u_nickname1 != null ? u_nickname1 : u_nickname2);
		String page1 = ServletActionContext.getRequest().getParameter("page");
		Integer page2 = (Integer) ServletActionContext.getRequest().getAttribute("page");
		Integer page = (page1 != null ? new Integer(page1) : page2);
		// ����service��
		pms.showmain(user_nickname, page);
		return "tomain";

	}

	// ��ʾ��ҳ��
	public String index() {
		// ����service��
		pms.showindex();

		return "toindex";

	}

}
