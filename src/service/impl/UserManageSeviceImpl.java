package service.impl;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import Utils.HibernateSessionFactory;
import Utils.MailUtil;
import Utils.Exception.LoginException;
import Utils.Exception.ModifyInfoException;
import Utils.Exception.ModifyPwException;
import Utils.Exception.RegisterException;
import Utils.Mail.ConfirmEmail;
import Utils.Mail.ResetPwEmail;
import dao.RoleManageDao;
import dao.UserManageDao;
import dao.impl.RoleManageDaoImpl;
import dao.impl.UserManageDaoImpl;
import domain.Role;
import domain.User;
import service.InfoCheck;
import service.UserManageService;

/**
 * �û�����ҵ���߼�ʵ����
 */
public class UserManageSeviceImpl implements UserManageService {
	// �û������Ϣ���ʵ�������
	InfoCheck ic = new InfoCheckImpl();
	// �û����ݹ��������
	UserManageDao umd = new UserManageDaoImpl();
	// ��ɫ���ݹ��������
	RoleManageDao rmd = new RoleManageDaoImpl();

	// ʵ��ע�Ṧ��
	@Override
	public User register(User user) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ����û����Ƿ�ע��
		if (!ic.checkname(user.getUser_name())) {
			throw new RegisterException("�û����ѱ�ע��");
		}
		// ��������Ƿ�ע�ᣬ�����ʽ�Ƿ���ȷ
		switch (ic.checkmail(user.getUser_email())) {
		case "1":
			throw new RegisterException("�����ѱ�ע��");
		case "2":
			throw new RegisterException("�����ʽ����ȷ");
		default:
			break;
		}
		// ����������������Ƿ�һ��
		if (!ic.checkpassword(user.getUser_password(), user.getUser_password2())) {
			throw new RegisterException("�����������벻һ��");
		}
		// ��֤ͨ�����������ݲ㣬�����ͻ���
		user.setConfirmed(false);
		user.setAdd_time(new Date());
		umd.add(user);
		Role r_user = rmd.getUser();
		r_user.getUsers().add(user);
		user.setRole(r_user);
		rmd.add_user(r_user);
		// ���û����䷢��ȷ���ʼ�
		new MailUtil().sendto(user.getUser_email(), new ConfirmEmail(user));
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

		return user;

	}

	// ʵ�ֵ�¼����
	@Override
	public User login(User user) {
		User u = null;
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ����¼���Ƿ�Ϊ����
		if (ic.ismail(user.getUser_loginname())) {
			u = umd.get_by_eamil(user.getUser_loginname());
			// ��������Ƿ�ע��
			if (u == null)
				throw new LoginException("���䲻����");
			// ��������Ƿ�ƥ��
			else if (u != null && !u.getUser_password().equals(user.getUser_password()))
				throw new LoginException("�������");
		} else {
			// ����û����Ƿ�ע��
			if (ic.checkname(user.getUser_loginname()))
				throw new LoginException("�û���������");
			else {
				u = umd.get_by_name(user.getUser_loginname());
				// ��������Ƿ�ƥ��
				if (!u.getUser_password().equals(user.getUser_password()))
					throw new LoginException("�������");
			}
		}
		// ��¼�ɹ���ȡ���û�Ȩ������
		u.getRole().getRole_authority();
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

		return u;
	}

	// ע����֤����
	@Override
	public void confirm(User u, String user_name) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ִ����֤
		if (u != null && u.getUser_name().equals(user_name)) {
			// ��֤�ɹ��������˻�Ϊȷ��״̬������Dao�㱣��
			if (!u.getConfirmed()) {
				u.setConfirmed(true);
				umd.modify(u);
			}
		} else
			throw new LoginException("���ȵ�¼��");
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// ���˻�����Ϣ�޸Ĺ���
	@Override
	public void modfiyinfo(User user) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ͨ��session��õ�ǰuser����
		User u = (User) ActionContext.getContext().getSession().get("currentuser");
		User u2 = umd.get_by_nickname(user.getUser_nickname());
		// ���Ҫ�޸ĵ��ǳ��Ƿ��Ѵ��ڡ������׳��쳣��
		if (u2 != null && !u.getUser_name().equals(u2.getUser_name())) {
			throw new ModifyInfoException("�ǳ��Ѵ��ڣ�");
		} else if (user.getUser_nickname().equals("")) {
			throw new ModifyInfoException("�ǳƲ���Ϊ�գ�");
		} else {
			// ��Ϣ�޸�
			u.setUser_nickname(user.getUser_nickname());
			u.setUser_age(user.getUser_age());
			u.setUser_gender(user.getUser_gender());
			u.setUser_birthday(user.getUser_birthday());
			u.setUser_address(user.getUser_address());
			u.setUser_hobby(user.getUser_hobby());
		}
		// �ύ��Dao�㴦��
		session.clear();
		umd.modify(u);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// �����޸Ĺ���
	@Override
	public void modifypw(User currentuser, User user) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// �����������������Ƿ�һ��
		if (!ic.checkpassword(user.getUser_password(), user.getUser_password2())) {
			// ���׳��쳣
			throw new ModifyPwException("�����������벻һ��");
		} else {
			// �ǣ����ò���������Dao�����޸�
			currentuser.setUser_password(user.getUser_password());
			umd.modify(currentuser);
		}
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// ����Э��Ա����
	@Override
	public void setmoderator(String user_nickname) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ����Dao�㣬ȡ��Ҫ���õ��û�����
		User user = umd.get_by_nickname(user_nickname);
		// ȡ��Moderator��ɫ����
		Role moderator = rmd.getModerator();
		// ��������
		Role r_user = rmd.getUser();
		r_user.getUsers().remove(user);
		moderator.getUsers().add(user);
		user.setRole(moderator);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();
	}

	// ������ͨ�û�����
	@Override
	public void setuser(String user_nickname) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ����Dao��ȡ��Ҫ�޸ĵ��û�����
		User user = umd.get_by_nickname(user_nickname);
		// ȡ��Moderator��User��ɫ����
		Role moderator = rmd.getModerator();
		Role r_user = rmd.getUser();
		// ��������
		moderator.getUsers().remove(user);
		r_user.getUsers().add(user);
		user.setRole(r_user);
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

	}

	// ��������
	@Override
	public void resetpw(User user) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// �����Ự
		Transaction transaction = session.beginTransaction();
		// ͨ�������ȡ�û�����
		User u = umd.get_by_eamil(user.getUser_email());
		// ����������������Ƿ�һ��
		if (ic.checkpassword(user.getUser_password(), user.getUser_password2())) {
			// �ǣ��ύ�޸ģ�������ʾ��Ϣ
			u.setUser_password(user.getUser_password());
			ActionContext.getContext().put("message", "�����޸ĳɹ�");
		} else {
			// �񣬷�����ʾ��Ϣ���׳��쳣
			ServletActionContext.getRequest().setAttribute("resetpw", true);
			throw new ModifyPwException("�����������벻һ��");
		}
		// �����ύ
		transaction.commit();
		// ��Դ�ر�
		session.clear();
		session.close();

	}

	// �û���������ǰ�Ĳ���
	@Override
	public void findpw(String email) {
		// ��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		// ������
		Transaction transaction = session.beginTransaction();
		// ͨ�������ȡ�û�����
		User u = umd.get_by_eamil(email);
		// �û��Ƿ����
		if (u != null) {
			// ���ڵ���Mail�����෢���ʼ�
			u.getUser_nickname();
			u.getUser_email();
			new MailUtil().sendto(email, new ResetPwEmail(u));
			ActionContext.getContext().put("message", "�����ѷ�����ȷ���ʼ����������");
		} else {
			// �����ڣ��׳��쳣��������ʾ��Ϣ
			throw new LoginException("����δע��");
		}
		// �����ύ
		transaction.commit();
		/// ��Դ�ر�
		session.clear();
		session.close();
	}
}
