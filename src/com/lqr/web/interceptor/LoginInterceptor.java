package com.lqr.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author CSDN_LQR
 * @工程 FrameSSH
 * @包名 com.lqr.web.interceptor
 * @TODO 登录过滤器
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Object obj = ActionContext.getContext().getSession().get("loginStaff");
		if (obj == null) {
			// 还没登录，需要跳转到登录页面
			Object object = invocation.getAction();// 得到的Action可能不继承ActionSupport
			if (object instanceof ActionSupport) {
				ActionSupport as = (ActionSupport) object;
				as.addFieldError("", "请先登录");
				return "login";
			}
		}

		// 放行
		return invocation.invoke();
	}

}
