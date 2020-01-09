package web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.FriendManageService;
import service.impl.FriendManageServiceImpl;

/**
 * ���ѹ���Action�࣬���ڴ�������ѹ����йص�����
 */
public class FriendManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	// ���ѹ���service����
	private FriendManageService fms = new FriendManageServiceImpl();

	// ��������
	public String add() {
		// ȡ�ò���
		String user_nickname = ServletActionContext.getRequest().getParameter("user_nickname");
		// ����service��
		fms.add(user_nickname);
		// ���÷��ز���
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";
	}

	// ɾ������
	public String delete() {
		// ȡ�ò���
		String user_nickname = ServletActionContext.getRequest().getParameter("user_nickname");
		// ����service��
		fms.delete(user_nickname);
		// ���÷��ز���
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";
	}

}
