package com.cn.service.impl;

import java.util.Map;

import com.cn.annotation.Service;
@Service("SpringmvcServiceImpl")
public class SpringmvcServiceImpl implements SpringmvcService {

    public int insert(Map map) {
        System.out.println("SpringmvcServiceImpl:"+"insert");
        return 0;
    }

    public int delet(Map map) {
        System.out.println("SpringmvcServiceImpl:"+"delet");
        return 0;
    }

    public int update(Map map) {
        System.out.println("SpringmvcServiceImpl:"+"update");
        return 0;
    }

    public int select(Map map) {
        System.out.println("SpringmvcServiceImpl:"+"select");
        return 0;
    }

}