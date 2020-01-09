package service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UserManageDao;
import dao.impl.UserManageDaoImpl;
import domain.User;
import service.InfoCheck;

/**
 * 用户注册信息验证实现类
 */
public class InfoCheckImpl implements InfoCheck {

	private UserManageDao umd = new UserManageDaoImpl();

	// 用户名验证方法
	@Override
	public boolean checkname(String name) {
		// 查询用户名是否存在
		User u = umd.get_by_name(name);

		if (u != null) {
			return false;
		} else
			return true;

	}

	// 邮箱验证方法
	@Override
	public String checkmail(String mail) {

		User u = umd.get_by_eamil(mail);

		if (u != null)
			return "1";
		else if (!ismail(mail))
			return "2";
		else
			return "success";

	}

	// 密码验证方法
	@Override
	public boolean checkpassword(String pw, String pw2) {
		if (!pw.equals(pw2))
			return false;
		else
			return true;

	}

	// 验证是否符合邮箱格式
	public boolean ismail(String mail) {
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(mail);
		return matcher.matches();
	}

}
