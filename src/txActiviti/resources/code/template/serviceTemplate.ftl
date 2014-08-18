package ${bussiPackage}.service.${entityPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.dao.CommonDao;
import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;

/**   
 * @Title: Service
 * @Description: ${ftl_description}
 * @author JiangBo
 *
 */
@Service
@Transactional
public class ${entityName}Service extends CommonDao<${entityName}Entity> {
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
    public void remove(${entityName}Entity entity) {
        super.remove(entity);
    }
}
