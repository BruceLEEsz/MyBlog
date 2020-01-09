package Utils.Mail;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import domain.User;

/**
 * 修改密码确认邮件实现类
 */
public class ModifyPwEmail implements Mail {

	public ModifyPwEmail(User u) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String url = basePath + "authmanage_confirm?user_name=" + u.getUser_name() + "&modifypw=true";
		SUBJECT.append("账户密码修改确认");
		CONTEXT.append("<h1>尊敬的<strong>" + u.getUser_name() + "</strong>:</h1><br/>");
		CONTEXT.append("<p>您已请求修改密码。请点击<a href=\"" + url + "\">" + url + "</a>，进入修改页面。</p>");
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
