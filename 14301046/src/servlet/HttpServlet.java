package servlet;

import javax.servlet.ServletConfig;

public class HttpServlet implements Servlet{
	public void service(ServletRequest req, ServletResponse res) { 
		if(req.getHeader().getMethod().equals("GET")){
			doGet(req, res);
		}else if(req.getHeader().getMethod().equals("POST")){
			doPost(req, res);
		}else{
			res.out.write("不支持的请求方法");
		}
    }  
    
    public void doGet(ServletRequest req, ServletResponse res) {  
    }  
          
    public void doPost(ServletRequest req, ServletResponse res) {  
    }
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	public void init(ServletConfig servletConfig) {
		// TODO Auto-generated method stub
		
	}  
}