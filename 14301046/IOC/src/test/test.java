package test;

import factory.XMLBeanFactory;
import test.boss;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			XMLBeanFactory XmlbeanFactory = new XMLBeanFactory("bean.xml");
			boss boss = (boss) XmlbeanFactory.getBean("boss");
			
			System.out.println(boss.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
