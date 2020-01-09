package web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * ��¼�������� ����¼��ע�᷽���⣬����������Ҫ�ȵ�¼������ת����¼���档
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// ��ȡsession����
		Map<String, Object> session = ActionContext.getContext().getSession();
		// ȡ�õ�¼��ʾ
		Object currentuser = session.get("currentuser");
		// ��δ��¼��ת����¼����
		if (currentuser == null) {
			ActionContext.getContext().put("message", "���ȵ�¼");
			return "returnlogin";
		}
		// ���򣬷���
		else {
			return invocation.invoke();
		}
	}

}
