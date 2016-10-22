
public class HttpServer {
	public static void main(String[] args) {  
        try {  
        	System.out.println("Serving...");;
            processor.openServer();  
        } catch (Exception e) {  
        	e.printStackTrace();
            try {
            	processor.closeServer();  
            } catch (Exception e1) {  
                e1.printStackTrace(); 
            }  
        }  
    }  
}
