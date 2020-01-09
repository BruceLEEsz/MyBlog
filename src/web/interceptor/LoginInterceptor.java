package web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 登录拦截器。 除登录、注册方法外，其余请求都需要先登录，否则转到登录界面。
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获取session对象
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 取得登录表示
		Object currentuser = session.get("currentuser");
		// 若未登录，转到登录界面
		if (currentuser == null) {
			ActionContext.getContext().put("message", "请先登录");
			return "returnlogin";
		}
		// 否则，放行
		else {
			return invocation.invoke();
		}
	}

}