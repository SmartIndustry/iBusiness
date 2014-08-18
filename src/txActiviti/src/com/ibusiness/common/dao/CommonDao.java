package com.ibusiness.common.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.hibernate.HibernateEntityDao;

/**
 * 公用DAO
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class CommonDao<T> extends HibernateEntityDao<T> {
    
}
