package cn.chenxhusky.FileSpace.util;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.chenxhusky.FileSpace.po.User;

@SuppressWarnings("serial")
public class LoginInterceptor extends MethodFilterInterceptor {

	private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	@Override
	protected String doIntercept(ActionInvocation invoker) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession(false).getAttribute("user");
		
		if(user == null){
			logger.info("用户没有登录！");
			return "re";
		} else {
			logger.info("用户登录成功!");
			return invoker.invoke();
		}
	}

}
