package factory;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import bean.BeanDefinition;
import bean.BeanUtil;
import bean.PropertyValue;
import bean.PropertyValues;


public class XMLBeanFactory extends AbstractBeanFactory{
	public XMLBeanFactory(){}
	
	public XMLBeanFactory(String XMLPath) throws Exception {
		// TODO Auto-generated constructor stub
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File(XMLPath));
		//List<Beanref> bufList = new ArrayList<>();
		List<annotationref> reflist = new ArrayList<>();
		
		NodeList list = doc.getElementsByTagName("bean");
		
		for(int i = 0; i < list.getLength(); i++){
			Element element = (Element) list.item(i);
		
			String idValue, classValue;
			idValue = element.getAttribute("id");
			classValue = element.getAttribute("class");
			
			NodeList properties = element.getElementsByTagName("property");

			BeanDefinition bdf = new BeanDefinition();
			bdf.setBeanClassName(classValue);
			PropertyValues pvs = new PropertyValues();
			boolean found = true;
			
			annotationref buf = new annotationref(idValue, bdf);
			
			for(int j = 0; j < properties.getLength(); j++){
				Element e = (Element) properties.item(j);
				PropertyValue pv = new PropertyValue();
				String refName = e.getAttribute("ref");
				String name = e.getAttribute("name");
				String value = e.getAttribute("value");
			
				pv.setName(name);
				if(!refName.isEmpty()){
					String clsName = BeanUtil.getClassName(refName);
					if(clsName == null){
						found = false;
						buf.put(pv, refName);
					}else{
						Object object = Class.forName(clsName).newInstance();
						pv.setValue(object);
						pvs.AddPropertyValue(pv);
					}
				}else{
					pv.setValue(value);
					pvs.AddPropertyValue(pv);
				}
			}
	
			bdf.setPropertyValues(pvs);
			if(found){
				registerBeanDefinition(idValue, bdf);
			}else{
				reflist.add(buf);
			}
		}
		
		
		if(!reflist.isEmpty()){
			for (annotationref b : reflist) {
				b.setAllRefClasses(this);
			}
		}
	}
	
	
	@Override
	protected BeanDefinition GetCreatedBean(BeanDefinition beanDefinition) {
		String beanClassName = beanDefinition.getBeanClassName();
		try {
			// set BeanClass for BeanDefinition
			Class<?> beanClass = Class.forName(beanClassName);
			beanDefinition.setBeanClass(beanClass);
			
			Object bean = beanClass.newInstance();	
			
		
			List<PropertyValue> fieldDefinitionList = beanDefinition.getPropertyValues().GetPropertyValues();
	
			if(beanClass.getName().equals("test.boss")){
				for(PropertyValue propertyValue: fieldDefinitionList)
				{
					BeanUtil.getPropertyvalue(bean, propertyValue.getName(), propertyValue.getValue());
				}
			
				beanDefinition.setBean(bean);
				return beanDefinition;
			}
			
			for(PropertyValue propertyValue: fieldDefinitionList)
			{
				BeanUtil.invokeSetterMethod(bean, propertyValue.getName(), propertyValue.getValue());
			}
			
			beanDefinition.setBean(bean);
			
			return beanDefinition;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
