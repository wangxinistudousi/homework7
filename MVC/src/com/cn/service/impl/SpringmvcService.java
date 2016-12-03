package com.cn.service.impl;

import java.util.Map;

import com.cn.annotation.Service;


public interface SpringmvcService {
    int insert(Map map);
    
    int delet(Map map);
    
    int update(Map map);
    
    int select(Map map);
}