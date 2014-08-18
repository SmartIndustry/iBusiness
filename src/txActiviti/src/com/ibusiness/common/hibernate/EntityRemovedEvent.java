package com.ibusiness.common.hibernate;

/**
 * 删除泛型实体事件
 * 
 * @author JiangBo
 *
 */
public class EntityRemovedEvent extends EntityEvent {
    private static final long serialVersionUID = 0L;

    public EntityRemovedEvent(Object entity) {
        super(entity);
    }

    public boolean isRemoved() {
        return true;
    }
}
