package porxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;

public class ProxyFactoryBean implements BeanFactoryAware, FactoryBean, InvocationHandler{
	private Object target;
	private Class[] proxyInterfaces;
	private List interceptorNames=new ArrayList();
	private List realInterceptorNames=null;
	private BeanFactory factory;
	
	public boolean isSingleton() {
		return true;
	}
	
	public void setTarget(Object target) {
			this.target=target;
	}
	
	public void setProxyInterfaces(String proxyInterfaces) {
		try {
			this.proxyInterfaces=new Class[]{Class.forName(proxyInterfaces)};
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
				}
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {


		Object result=method.invoke(target,args);
		
		if(realInterceptorNames==null) {
			realInterceptorNames=new ArrayList();
			for(Object o: interceptorNames) {
				realInterceptorNames.add(factory.getBean(o.toString()));
				}
			}
		return result;
		}
	
	public void setInterceptorNames(List interceptorNames) {
		this.interceptorNames=interceptorNames;
		}
	
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		this.factory=arg0;
	}
	
	public Object getObject() throws Exception {
		System.out.println(Proxy.newProxyInstance(target.getClass().getClassLoader(),proxyInterfaces,this));
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),proxyInterfaces,this);
	}
	
	public Class getObjectType() {
		System.out.println(target.getClass());
		return target.getClass();
		}
}