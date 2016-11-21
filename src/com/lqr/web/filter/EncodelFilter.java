package com.lqr.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author CSDN_LQR
 * @工程 FrameSSH
 * @包名 com.lqr.web.filter
 * @TODO 全局中文过滤器（适用get和post请求参数的中文乱码问题，从此不同在servlet中对参数做处理）
 */
public class EncodelFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		// 解决POST请求参数乱码问题
		// request.setCharacterEncoding("UTF-8");

		req = new MyRequest(req);

		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

/**
 * @author CSDN_LQR
 * @工程 day16_02_globalFilter
 * @包名 com.itheima.filter
 * @TODO 使用装饰模式包装HttpServletRequest，解决getParamter中文乱码问题
 */
class MyRequest extends HttpServletRequestWrapper {

	private HttpServletRequest req;
	private boolean flag = true;// 标记是否getParameterMap方法还未被调用过（如果在同个servlet中调用了2次getParameter等方法2次，没有用flag做标记的话，会对参数进行2次编码，结果第2次得到的参数会是乱码）

	public MyRequest(HttpServletRequest request) {
		super(request);
		req = request;
	}

	@Override
	public String getParameter(String name) {
		return getParameterMap().get(name)[0];
	}

	@Override
	public String[] getParameterValues(String name) {
		return getParameterMap().get(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {

		Map<String, String[]> map = req.getParameterMap();

		if (flag) {
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				String[] value = entry.getValue();
				for (int i = 0; i < value.length; i++) {
					try {
						value[i] = new String(value[i].getBytes("iso-8859-1"),
								"UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
			flag = false;
		}

		return map;
	}

}
