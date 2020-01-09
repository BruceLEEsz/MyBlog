package Utils.Mail;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import domain.User;

/**
 * ע��ȷ���ʼ�ʵ����
 */
public class ConfirmEmail implements Mail {

	public ConfirmEmail(User u) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String url = basePath + "authmanage_confirm?user_name=" + u.getUser_name() + "&modifypw=false";
		SUBJECT.append("�˻�ע��ȷ��");
		CONTEXT.append("<h1>�𾴵�<strong>" + u.getUser_name() + "</strong>:</h1><br/>");
		CONTEXT.append("<p>��ӭ������MyBlog���뾡����<a href=\"" + url + "\">" + url + "</a>�����������Ϣ�����ע�ᡣ</p>");
	}

	@Override
	public StringBuffer getSubjet() {
		// TODO Auto-generated method stub
		return SUBJECT;
	}

	@Override
	public StringBuffer getContext() {
		// TODO Auto-generated method stub
		return CONTEXT;
	}

	@Override
	public void clean() {
		SUBJECT.delete(0, SUBJECT.length());
		CONTEXT.delete(0, CONTEXT.length());

	}
}
