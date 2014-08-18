package com.ibusiness.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.dao.CommonDao;
import com.ibusiness.common.entity.ConfServiceModuleEntity;
/**
 * 业务模块组件service
 * 
 * @author JiangBo
 *
 */
@Service
@Transactional
public class ServiceModuleService extends CommonDao<ConfServiceModuleEntity> {

    /**
     * 更新
     * @param entity
     */
    public <T> void update(T entity) {
        super.save(entity);
    }

    /**
     * 插入
     * @param entity
     */
    public <T> void insert(T entity) {
        super.saveInsert(entity);
    }
    /**
     * 删除
     * @param entity
     */
    public <T> void delete(T entity) {
        super.remove(entity);

    }
}
