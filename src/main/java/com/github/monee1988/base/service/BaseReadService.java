package com.github.monee1988.base.service;

import java.util.List;

import com.github.monee1988.base.dao.BaseReadDao;
import com.github.monee1988.base.entity.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.monee1988.mybatis.entity.Page;

public class BaseReadService<T extends BaseBean<T>,MR extends BaseReadDao<T>> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MR readMapper;

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
}
