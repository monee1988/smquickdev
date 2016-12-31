package com.github.monee1988.base.dao;

import com.github.monee1988.base.entity.BaseBean;

import java.util.List;


public interface BaseWriteDao<T extends BaseBean<T>> extends BaseDao{
	
	public void saveData(T object);

	public void updateData(T object);

	public void deleteByIds(List<String> idList);
}
