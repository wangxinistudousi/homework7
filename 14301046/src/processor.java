import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import bjtu.header.RequestHeader;
import bjtu.headerParser.IRequestHeaderParser;
import bjtu.jsp.JspParser;
import servlet.HttpServlet;
import servlet.Servlet;
import servlet.ServletRequest;
import servlet.ServletResponse;

public class processor extends Thread{
	private static ServerSocket server;
    private Socket socket;

    public processor(Socket socket) {
        this.socket = socket;
    }
    
    public static void openServer() throws Exception {  
        server = new ServerSocket(Integer.parseInt("8080"));  
        System.out.println("start serving...");;
        while (true) {  
        	Socket socket=server.accept();
            new processor(socket).start();  
        }  
    }  
  
    public static void closeServer() throws Exception {  
	    if (server != null) {  
	        if (!server.isClosed()) {  
	            server.close();  
	        }  
	    }  
    }
    @SuppressWarnings("resource")
	public void run(){
    	InputStream in = null;  
        OutputStream out = null;  
        FileInputStream fin = null;  
        try {  
            in = socket.getInputStream(); // 获取客户端发送的字节流  
            try {
    			 new InputStreamReader(in,"GBK");
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} 
            out = socket.getOutputStream(); // 获取服务端响应的字节流  
            byte[] b = new byte[1024 * 1024]; // 设置字节缓冲区  
            in.read(b); // 读取客户端字节流（字节流的请求头） 
            String txt = new String(b,"utf-8").trim(); // 将请求头封装成String，准备交给解析器解析  
            System.out.println(txt);
            IRequestHeaderParser parser = (IRequestHeaderParser) Class.forName(  
            		"bjtu.headerParser.RequestHeaderParser")  
                    .newInstance(); // 使用工厂设计模式从请求头中加载请求头解析器的实例  
            RequestHeader header = parser.parse(txt); // 解析请求头文本，使用RequestHeader封装其内容  
            ServletRequest request = new ServletRequest();  
            request.setParameter(header.getParameter());  
            request.setHeader(header); 
            ServletResponse response=new ServletResponse();
            
            if(header.getUrl().endsWith(".jsp")){
            	new JspParser();
            	BufferedReader reader = new BufferedReader(new FileReader("./webapps"+"/"+header.getUrl()));
        		StringBuilder str = new StringBuilder();
        		String s;
        		while ((s = reader.readLine()) != null) {
        			str.append(s + "\n");
        		}
        		String className=header.getUrl().replace(".", "").replace("/", "")+"Servlet";
            	String classTxt=JspParser.parse(str.toString(), className, "req", "res");
            	System.out.println(classTxt);
            	reader.close();
            	
            	OutputStream o=new FileOutputStream(className+".java");
        		BufferedWriter javaOutput = new BufferedWriter(new OutputStreamWriter(o, "utf-8"));
        		javaOutput.write(classTxt);
        		javaOutput.close();
        		
        		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        		new ByteArrayInputStream(classTxt.getBytes(StandardCharsets.UTF_8));
        		
        		new FileOutputStream(className+".class");
        		String[] args = new String[1];
        		args[0]=className;
        		compiler.run(in, out, System.err, args); 
            	
                response.setOut(new PrintWriter(out,true));  
                response.out.println("transforming Successfully !");
                response.out.close();
                //servlet.service(request,response);
            	
            }
        } catch (Exception e) {
            e.printStackTrace();  
        } finally {  
            try {  
                if (fin != null) {  
                    fin.close();  
                }  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
                if (socket != null) {  
                    socket.close();  
                }  
            } catch (IOException e) {  
            }  
        } 
       
    }
}
