package com.snoweagle.console.dal.persistence;

import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;

/**
 * Created by wangshaobo on 2015/7/14.
 */
public class BaseDao {
    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;
}
