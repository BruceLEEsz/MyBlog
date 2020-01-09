package web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import domain.User;

/**
 * 流程控制拦截器。 登录后，主要功能的使用需要账户状态为“确认”。
 */
public class ProcessIntercepter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获取session对象
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 取得当前用户
		User currentuser = (User) session.get("currentuser");
		// 若账户未确认，转到首页并给予提示信息
		if (!currentuser.getConfirmed()) {
			ActionContext.getContext().put("message", "账户未确认!");
			return "chainindex";
		}
		// 否则，放行
		else {
			return invocation.invoke();
		}
	}

}
