package com.github.monee1988.base.service;

import java.util.List;

import com.github.monee1988.base.dao.BaseReadDao;
import com.github.monee1988.base.dao.BaseWriteDao;
import com.github.monee1988.base.entity.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.monee1988.mybatis.entity.Page;

public class BaseService<T extends BaseBean<T>,MR extends BaseReadDao<T>,MW extends BaseWriteDao<T>> {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected MR readMapper;
	
	@Autowired
	protected MW writeMapper;
	
	protected T getDataById(String id) {
		return readMapper.getDataById(id);
	}
	
	protected List<T> listData( T t){
		return readMapper.listData(t);
	}
	
	protected Page<T> findPage(Page<T> page, T t) {
		
		t.setPage(page);
		page.setList(listData(t));
		
		return page;
	}
	
	protected void saveFormData(T t,Long adminId) throws Exception{
		
		try {
			if(t.isNewData()){
				t.preInsert(adminId);
				writeMapper.saveData(t);
			}else{
				t.preUpdate(adminId);
				writeMapper.updateData(t);;
			}
		} catch (Exception e) {
			logger.error("行程系统->系统异常:" + e.fillInStackTrace());
			throw e;
		}
	}
	
	
	protected void deleteDatasByIds(List<String> idList) throws Exception{
		
		try {
			writeMapper.deleteByIds(idList);
		} catch (Exception e) {
			logger.error("行程系统->系统异常:" + e.fillInStackTrace());
			throw e;
		}
	}

	
}
