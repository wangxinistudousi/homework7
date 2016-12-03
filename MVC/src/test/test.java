package test;



import com.cn.annotation.Controller;
import com.cn.annotation.RequestMapping;
import com.cn.modeandview.ModelAndView;

import com.cn.modeandview.*;
@Controller
public class test {
	@RequestMapping("/hello")
	public ModelAndView  hello(ModelAndView mdv) {
		ModelAndView mav=mdv;
		// TODO Auto-generated constructor stub
		mav.setViewName("test");
		mav.addObject("name", mav.getModelMap("name"));
		mav.addObject("pas", mav.getMap("pas"));
		return mav;
	}
	@RequestMapping("/hello2")
	public ModelAndView  hello2(ModelAndView mdv) {
		ModelAndView mav =mdv;
		// TODO Auto-generated constructor stub
		mav.setViewName("test");
		return mav;
	}
}
