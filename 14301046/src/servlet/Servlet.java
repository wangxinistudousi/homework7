package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;

public interface Servlet {
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException; 
	
	public void destroy();

	public ServletConfig getServletConfig();
	
	public String getServletInfo();
	
	public void init(ServletConfig servletConfig) throws ServletException;
}
