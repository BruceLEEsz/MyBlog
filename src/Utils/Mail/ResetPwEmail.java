package Utils.Mail;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import domain.User;

/**
 * ��������ȷ���ʼ�ʵ����
 */
public class ResetPwEmail implements Mail {
	public ResetPwEmail(User u) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String url = basePath + "setpw.jsp?resetpw=true&email=" + u.getUser_email();
		SUBJECT.append("�˻���������ȷ��");
		CONTEXT.append("<h1>�𾴵�<strong>" + u.getUser_name() + "</strong>:</h1><br/>");
		CONTEXT.append("<p>���������������롣����<a href=\"" + url + "\">" + url + "</a>�������޸�ҳ�档</p>");
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
