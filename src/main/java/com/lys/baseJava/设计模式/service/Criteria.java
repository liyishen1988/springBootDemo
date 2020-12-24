package com.lys.baseJava.设计模式.service;

import com.lys.baseJava.设计模式.pojo.Person;

import java.util.List;

/**
 * 为标准（Criteria）创建一个接口
 */
public interface Criteria {

    public List<Person> meetCriteria(List<Person> persons);

}
