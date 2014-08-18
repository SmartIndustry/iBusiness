package com.ibusiness.common.rs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibusiness.common.entity.ConfServiceModuleEntity;
import com.ibusiness.common.service.ServiceModuleService;
/**
 * 业务模块组件 左边树
 * @author JiangBo
 *
 */
@Component
@Path("serviceModule")
public class ModuleResource {
    private static Logger logger = LoggerFactory.getLogger(ModuleResource.class);
    private ServiceModuleService serviceModuleService;

    @POST
    @Path("tree")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> tree(@QueryParam("parentId") String parentId) {
        String hql = "from ConfServiceModuleEntity where parentid = 0 ";
        List<ConfServiceModuleEntity> entities = serviceModuleService.find(hql);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 0);
        map.put("name", "业务模块(增删改)");
        map.put("packageName", "root");
        map.put("typeId", "root");
        map.put("open", "true");
        map.put("children",generateEntities(entities, 0));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map);
        return list;
    }
    /**
     * 循环插入节点
     * @param entities
     * @param partyStructTypeId
     * @return
     */
    private List<Map<String, Object>> generateEntities(List<ConfServiceModuleEntity> entities, long partyStructTypeId) {
        if (entities == null) {
            return null;
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            for (ConfServiceModuleEntity entity : entities) {
                list.add(generateEntity(entity, partyStructTypeId));
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return list;
    }

    /**
     * 配置节点,递归调用子节点
     * 
     * @param partyEntity
     * @param partyStructTypeId
     * @return
     */
    private Map<String, Object> generateEntity(ConfServiceModuleEntity partyEntity, long partyStructTypeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("id", partyEntity.getId());
            map.put("name", partyEntity.getModulename());
            map.put("packageName", partyEntity.getPackagename());
            map.put("typeId", partyEntity.getTypeid());
            map.put("open", "true");
            // 查询子节点
            String hql = "from ConfServiceModuleEntity where parentid = ? ";
            List<ConfServiceModuleEntity> entities = serviceModuleService.find(hql, partyEntity.getId());
            // 循环
            map.put("children", generateEntities(entities, partyStructTypeId));
            return map;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return map;
        }
    }

    // ==================================================
    @Resource
    public void setServiceModuleService(ServiceModuleService serviceModuleService) {
        this.serviceModuleService = serviceModuleService;
    }
}
