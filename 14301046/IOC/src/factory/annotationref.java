package factory;


import java.util.ArrayList;
import java.util.List;

import bean.BeanDefinition;
import bean.PropertyValue;
import factory.BeanFactory;

public class annotationref {
	private BeanDefinition beanDefinition;
	private String id;
	private List<Property> list = new ArrayList<>();
	
	public annotationref(String id, BeanDefinition beanDefinition){
		this.beanDefinition = beanDefinition;
		this.id = id;

	}
	
	
	public void put(PropertyValue propertyValue, String ref){
		list.add(new Property(propertyValue, ref));
	}
		public void setAllRefClasses(BeanFactory beanFactory){
		for (Property p : list) {
				
			Object object = beanFactory.getBean(p.refClassName);
		
			p.propertyValue.setValue(object);
			beanDefinition.getPropertyValues().AddPropertyValue(p.propertyValue);
		}
		
		beanFactory.registerBeanDefinition(id, beanDefinition);
	}
	
	
	class Property{
		private PropertyValue propertyValue;
		private String refClassName;
		
		public Property(PropertyValue propertyValue, String refClassName) {
			this.propertyValue =propertyValue;
			this.refClassName = refClassName;
		}
	}
	
}
