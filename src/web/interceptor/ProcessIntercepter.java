package web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import domain.User;

/**
 * ���̿����������� ��¼����Ҫ���ܵ�ʹ����Ҫ�˻�״̬Ϊ��ȷ�ϡ���
 */
public class ProcessIntercepter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// ��ȡsession����
		Map<String, Object> session = ActionContext.getContext().getSession();
		// ȡ�õ�ǰ�û�
		User currentuser = (User) session.get("currentuser");
		// ���˻�δȷ�ϣ�ת����ҳ��������ʾ��Ϣ
		if (!currentuser.getConfirmed()) {
			ActionContext.getContext().put("message", "�˻�δȷ��!");
			return "chainindex";
		}
		// ���򣬷���
		else {
			return invocation.invoke();
		}
	}

}
