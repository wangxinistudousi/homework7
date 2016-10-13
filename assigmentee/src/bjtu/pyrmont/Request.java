package bjtu.pyrmont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.Globals;

public class Request implements ServletRequest {
	
	 private InputStream input;
	 private String uri;
	 private String rq;
	 
	 public Request(InputStream input) {
		 this.input = input;
	 }
	 
	 public String getUri() {
		 return uri;
	 }
	 /**
	 * 
	 * requestString形式如下：
	 * GET /index.html HTTP/1.1
	 * Host: localhost:8080
	 * Connection: keep-alive
	 * Cache-Control: max-age=0
	 * ...
	 * 该函数目的就是为了获取/index.html字符串
	 */
	 private String parseUri(String requestString) {
		 int index1, index2;
		 index1 = requestString.indexOf(' ');
		 if (index1 != -1) {
			 index2 = requestString.indexOf(' ', index1 + 1);
			 if (index2 > index1)
				 return requestString.substring(index1 + 1, index2);
		 }
		 return null;
	 }
	 //从InputStream中读取request信息，并从request中获取uri值
	 public void parse() {
	 // Read a set of characters from the socket
		 StringBuffer request = new StringBuffer(2048);
		 int i;
		 byte[] buffer = new byte[2048];
		 try {
			 i = input.read(buffer);
		 } catch (IOException e) {
			 e.printStackTrace();
			 i = -1;
		 }
		 for (int j = 0; j < i; j++) {
			 request.append((char) buffer[j]);
		 }
		 
		 rq = request.toString(); 
		// System.out.print(rq);
		 uri = parseUri(request.toString());

		 
	 }
	
	
	

	@Override
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getContentLengthLong() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<Locale> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String content = rq;

		String[] names = new String[2];
		String[] values = new String[2];
		
		for(int i=0;i<2;i++) {
			names[i]=" ";
			values[i]=" ";
		}
		
		content = content.substring(content.lastIndexOf("username"),content.length());
		
		String[] parameter=content.split("&");
		String usernameinfo = parameter[0];
		String pawinfo = parameter[1];
		
		String laststr1 = usernameinfo.substring(usernameinfo.length()-1, usernameinfo.length());
		String laststr2 = pawinfo.substring(pawinfo.length()-1, pawinfo.length());
		
		//两个框都为空
		if(laststr1.equals("=")&&laststr2.equals("=")) {
			//System.out.println(parameter[0].substring(parameter[0].length()-2, parameter[0].length()-1));
			return null;
		}
		
		//username空
		else if(laststr1.equals("=")&&(!laststr2.equals("="))) {
			if(name.equals("username")) 
				return null;
			else{
				String[] temp2 = parameter[1].split("=");
				names[1] = temp2[0]; values[1] = temp2[1];
				return values[1];
			}		
		}
		
		else if ((!laststr1.equals("="))&&laststr2.equals("=")) {
			if(name.equals("username")) {
				String[] temp1 = parameter[0].split("=");
				names[0] = temp1[0]; values[0] = temp1[1];
				return values[0];
			}else
				return null;
		}
		
		else{
		
		String[] temp1 = parameter[0].split("=");
		names[0] = temp1[0]; values[0] = temp1[1];
		String[] temp2 = parameter[1].split("=");
		names[1] = temp2[0]; values[1] = temp2[1];

		
		if (name.equals(names[0]))
			return values[0];
		else
			return values[1];
		
		}

	}

	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

}
