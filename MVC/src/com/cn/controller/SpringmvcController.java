package com.cn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.annotation.Controller;
import com.cn.annotation.Quatifier;
import com.cn.annotation.RequestMapping;
import com.cn.service.impl.MyService;
import com.cn.service.impl.SpringmvcService;

@Controller("dongnao")
public class SpringmvcController {
    @Quatifier("MyServiceImpl")
    MyService myservice;
    
    @Quatifier("SpringmvcServiceImpl")
    SpringmvcService smservice;
    
    @RequestMapping("insert")
    public String  insert(HttpServletRequest request,
            HttpServletResponse response,String param){
        System.out.println(request.getRequestURI()+"insert");
        myservice.insert(null);
        
        smservice.insert(null);
        return null;
    }
    @RequestMapping("delet")
    public String  delet(HttpServletRequest request,
            HttpServletResponse response,String param){
        myservice.delet(null);
        
        smservice.delet(null);
        return null;
    }
    
    @RequestMapping("select")
    public String  select(HttpServletRequest request,
            HttpServletResponse response,String param){
        myservice.select(null);
        
        smservice.select(null);
        return null;
    }
    
    @RequestMapping("update")
    public String  update(HttpServletRequest request,
            HttpServletResponse response,String param){
        myservice.update(null);
        
        smservice.update(null);
        return null;
    }
    
    
}