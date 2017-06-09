package com.github.monee1988.base.dao;

import com.github.monee1988.base.entity.BaseBean;

/**
 * Created by monee1988 on 17-6-9.
 */
public interface BaseWRDao<T extends BaseBean<T>> extends BaseWriteDao<T>,BaseReadDao<T> {
}
