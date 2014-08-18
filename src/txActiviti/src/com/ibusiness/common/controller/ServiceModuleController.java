package com.ibusiness.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.common.entity.ConfServiceModuleEntity;
import com.ibusiness.common.hibernate.IbPropertyFilter;
import com.ibusiness.common.page.IbPage;
import com.ibusiness.common.service.ServiceModuleService;
import com.ibusiness.common.util.CommonUtils;
import com.mossle.core.spring.MessageHelper;

/**
 * 业务模块组件用controller。业务模块下包括各个业务模块的内容。
 * 每个业务模块下都有1.存储控制器 2.表单设计器 3.流程设计器
 * 
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("serviceModule")
public class ServiceModuleController {
    private MessageHelper messageHelper;
    private ServiceModuleService serviceModuleService;
    
    @RequestMapping("serviceModule-show")
    public String show(@RequestParam Map<String, Object> parameterMap, Model model) {
        model.addAttribute("parentId", 0);
        // 返回JSP
        return "../jsp/common/serviceModule-show";
    }
    /**
     * 菜单跳转控制方法
     * 
     * @param parameterMap
     * @param model
     * @return
     */
    @RequestMapping("serviceModule-action")
    public String moduleAction(Model model, @RequestParam(value = "packageName", required = false) String packageName,
            @RequestParam(value = "typeId", required = false) String typeId) {
        if ("root".equals(packageName)) {
            // 返回添加业务组件管理页面
            return "redirect:/serviceModule/serviceModule-list.do";
        }
        if ("Table".equals(typeId)) {
            // 跳转到表存储页面
            return "redirect:/table/conf-table-show.do?packageName=" + packageName;
        }
        if ("Form".equals(typeId)) {
            // 跳转到表单页面
            return "redirect:/table/conf-table-show.do?packageName=" + packageName;
        }
        if ("Bpm".equals(typeId)) {
            // 跳转到表单页面
            return "redirect:/table/conf-table-show.do?packageName=" + packageName;
        }
        // 返回JSP
        return "../jsp/common/serviceModule-show";
    }
    
    @RequestMapping("serviceModule-list")
    public String list(@ModelAttribute IbPage page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 父结点ID为0的是业务分类模块
        parameterMap.put("filter_EQS_parentid", "0");
        // 查询条件Filter过滤器
        List<IbPropertyFilter> propertyFilters = IbPropertyFilter.buildFromMap(parameterMap);
        // 根据条件查询数据
        page = serviceModuleService.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "../jsp/common/serviceModule-list";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("serviceModule-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        ConfServiceModuleEntity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = serviceModuleService.get(id);
        } else {
            entity = new ConfServiceModuleEntity();
        }
        model.addAttribute("model", entity);
        
        return "../jsp/common/serviceModule-input";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("serviceModule-save")
    public String save(@ModelAttribute ConfServiceModuleEntity entity, RedirectAttributes redirectAttributes) throws Exception {
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            serviceModuleService.insert(entity);
            // 表
            ConfServiceModuleEntity tableEntity = new ConfServiceModuleEntity();
            tableEntity.setId(UUID.randomUUID().toString());
            tableEntity.setParentid(entity.getId());
            tableEntity.setPackagename(entity.getPackagename());
            tableEntity.setModulename("表存储设计器");
            tableEntity.setTypeid("Table");
            // 表单
            ConfServiceModuleEntity formEntity = new ConfServiceModuleEntity();
            formEntity.setId(UUID.randomUUID().toString());
            formEntity.setParentid(entity.getId());
            formEntity.setPackagename(entity.getPackagename());
            formEntity.setModulename("表单设计器");
            formEntity.setTypeid("Form");
            // 流程
            ConfServiceModuleEntity bpmEntity = new ConfServiceModuleEntity();
            bpmEntity.setId(UUID.randomUUID().toString());
            bpmEntity.setParentid(entity.getId());
            bpmEntity.setPackagename(entity.getPackagename());
            bpmEntity.setModulename("流程设计器");
            bpmEntity.setTypeid("Bpm");
            // 插入
            serviceModuleService.insert(tableEntity);
            serviceModuleService.insert(formEntity);
            serviceModuleService.insert(bpmEntity);
        } else {
            serviceModuleService.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/serviceModule/serviceModule-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("serviceModule-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        // 删除指定业务模块
        serviceModuleRemoveList(selectedItem);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
        return "redirect:/serviceModule/serviceModule-list.do";
    }
    /**
     * 删除指定业务模块
     * 
     * @param selectedItem
     */
    private void serviceModuleRemoveList(List<String> selectedItem) {
        List<String> items = new ArrayList<String>();
        List<ConfServiceModuleEntity> entitys = serviceModuleService.findByIds(selectedItem);
        for (ConfServiceModuleEntity entity : entitys) {
            String hql = "from ConfServiceModuleEntity csm WHERE csm.parentid=?";
            List<ConfServiceModuleEntity> list = serviceModuleService.find(hql, entity.getId());
            for (ConfServiceModuleEntity bean : list) {
                items.add(bean.getId());
            }
            serviceModuleService.remove(entity);
        }
        if (items.size() > 0) {
            serviceModuleRemoveList(items);
        }
    }
    // ======================================================================
    @Resource
    public void setServiceModuleService(ServiceModuleService serviceModuleService) {
        this.serviceModuleService = serviceModuleService;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
