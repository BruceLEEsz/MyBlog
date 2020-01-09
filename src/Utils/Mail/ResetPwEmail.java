package Utils.Mail;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import domain.User;

/**
 * 重置密码确认邮件实现类
 */
public class ResetPwEmail implements Mail {
	public ResetPwEmail(User u) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String url = basePath + "setpw.jsp?resetpw=true&email=" + u.getUser_email();
		SUBJECT.append("账户密码重置确认");
		CONTEXT.append("<h1>尊敬的<strong>" + u.getUser_name() + "</strong>:</h1><br/>");
		CONTEXT.append("<p>您已请求重置密码。请点击<a href=\"" + url + "\">" + url + "</a>，进入修改页面。</p>");
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
