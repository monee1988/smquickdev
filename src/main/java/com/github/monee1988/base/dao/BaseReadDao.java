package com.github.monee1988.base.dao;

import com.github.monee1988.base.entity.BaseBean;

import java.util.List;


public interface BaseReadDao<T extends BaseBean<T>> extends BaseDao{

	List<T> listData(T object);
	
	T getDataById(String id);
}
