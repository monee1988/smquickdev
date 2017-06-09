package com.github.monee1988.base.service;

import com.github.monee1988.base.dao.BaseWRDao;
import com.github.monee1988.base.entity.BaseBean;
import com.github.monee1988.mybatis.entity.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by monee1988 on 17-6-9.
 */
public class BaseWRService<T extends BaseBean<T>,DAO extends BaseWRDao<T>>  {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    protected DAO dao;

    protected T getDataById(String id) {
        return dao.getDataById(id);
    }

    protected List<T> listData(T t){
        return dao.listData(t);
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
                dao.saveData(t);
            }else{
                t.preUpdate(adminId);
                dao.updateData(t);;
            }
        } catch (Exception e) {
            logger.error("行程系统->系统异常:" + e.fillInStackTrace());
            throw e;
        }
    }


    protected void deleteDatasByIds(List<String> idList) throws Exception{

        try {
            dao.deleteByIds(idList);
        } catch (Exception e) {
            logger.error("行程系统->系统异常:" + e.fillInStackTrace());
            throw e;
        }
    }


}
