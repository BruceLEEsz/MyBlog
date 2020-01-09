package Utils.Mail;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import domain.User;

/**
 * 注册确认邮件实现类
 */
public class ConfirmEmail implements Mail {

	public ConfirmEmail(User u) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String url = basePath + "authmanage_confirm?user_name=" + u.getUser_name() + "&modifypw=false";
		SUBJECT.append("账户注册确认");
		CONTEXT.append("<h1>尊敬的<strong>" + u.getUser_name() + "</strong>:</h1><br/>");
		CONTEXT.append("<p>欢迎您加入MyBlog。请尽快点击<a href=\"" + url + "\">" + url + "</a>，输入基本信息，完成注册。</p>");
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
