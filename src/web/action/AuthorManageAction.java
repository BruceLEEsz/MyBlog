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
 * �û�����Action�࣬���ڴ����˻���ص�����
 */
public class AuthorManageAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	// �û����󣬽���ҳ�洫��Ĳ���
	private User user = new User();
	// �û�����service����
	private UserManageService ums = new UserManageSeviceImpl();

	// ע�᷽��
	public String register() {
		// ����service��ʵ��ע���߼�
		User u = ums.register(user);
		// ע��ɹ���������ʾ��Ϣ
		ActionContext.getContext().getSession().put("currentuser", u);
		ActionContext.getContext().getContextMap().put("message", "ע��ɹ��������ѷ���һ��ȷ���ʼ�������ע�����䡣");
		return "chainindex";

	}

	// ��¼����
	public String login() {
		// ����service��ʵ�ֵ�¼����
		User u = ums.login(user);
		// ��¼�ɹ�
		ActionContext.getContext().getSession().put("currentuser", u);
		// ����˻�ȷ�����
		if (!u.getConfirmed()) {
			// δȷ�ϣ�ת����ҳ��������ʾ��Ϣ
			new MailUtil().sendto(u.getUser_email(), new ConfirmEmail(u));
			ActionContext.getContext().getContextMap().put("message", "�˻�δȷ�ϣ����������·�����һ��ȷ���ʼ�������ע�����䡣");
		}
		return "chainindex";
	}

	// ע������
	public String logout() {
		// ���session����ע���˻�
		ActionContext.getContext().getSession().clear();

		return "login";

	}

	// �˻���֤����
	public String confirm() {
		// ��ȡ��֤����
		String user_name = ServletActionContext.getRequest().getParameter("user_name");
		Boolean modifypw = new Boolean(ServletActionContext.getRequest().getParameter("modifypw"));
		// ͨ��session�����ȡ��ǰuser����
		User u = (User) ActionContext.getContext().getSession().get("currentuser");
		// ����serviceʵ���˻���֤����
		ums.confirm(u, user_name);
		// ��֤�ɹ�������
		if (!modifypw)
			return "tosetinfo";
		else
			return "tosetpw";

	}

	// �˻�������Ϣ�޸ķ���
	public String modifyinfo() {
		// ����serviceʵ����Ϣ�޸��߼�
		ums.modfiyinfo(user);
		return "chainindex";

	}

	// �����޸�
	public String modifypw() {
		/// ��ȡ����
		User u = (User) ActionContext.getContext().getSession().get("currentuser");
		Boolean hadsendmail = new Boolean(ServletActionContext.getRequest().getParameter("hadsendmail"));
		if (!hadsendmail) {
			// ȷ���ʼ�δ���ͣ������ʼ�
			new MailUtil().sendto(u.getUser_email(), new ModifyPwEmail(u));
		} else {
			// ���򣬵���service���޸�����
			ums.modifypw(u, user);
		}

		return "chainindex";
	}

	// �û���������ǰ�Ĳ���
	public String findpw() {
		// ȡ�ò���
		String email = ServletActionContext.getRequest().getParameter("email");
		// ����service��
		ums.findpw(email);

		return "tologin";
	}

	// ��������
	public String resetpw() {
		// ����service��
		ums.resetpw(user);
		return "tologin";
	}

	// ����Э��Ա����
	public String setmoderator() {
		// ȡ�ò���
		String user_nickname = ServletActionContext.getRequest().getParameter("user_nickname");
		// ����service��
		ums.setmoderator(user_nickname);
		// ���÷��ز���
		ServletActionContext.getRequest().setAttribute("user_nickname", user_nickname);
		ServletActionContext.getRequest().setAttribute("page", 1);
		return "chainmain";

	}

	// ������ͨ�û�����
	public String setuser() {
		// ȡ�ò���
		String user_nickname = ServletActionContext.getRequest().getParameter("user_nickname");
		// ����service��
		ums.setuser(user_nickname);
		// ���÷��ز���
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
