package com.github.monee1988.base.dao;

import com.github.monee1988.base.entity.BaseBean;

import java.util.List;


public interface BaseWriteDao<T extends BaseBean<T>> extends BaseDao{
	
	int saveData(T object);

	int updateData(T object);

	int deleteByIds(List<String> idList);
}
