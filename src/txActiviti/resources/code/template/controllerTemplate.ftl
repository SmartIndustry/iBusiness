package ${bussiPackage}.controller.${entityPackage};

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mossle.core.spring.MessageHelper;
import com.ibusiness.common.export.IbExportor;
import com.ibusiness.common.export.IbTableModel;
import com.ibusiness.common.hibernate.IbPropertyFilter;
import com.ibusiness.common.page.IbPage;
import com.ibusiness.common.util.CommonUtils;

import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
import ${bussiPackage}.service.${entityPackage}.${entityName}Service;

/**   
 * @Title: Controller
 * @Description: ${ftl_description}
 * @author JiangBo
 *
 */
@Controller
@RequestMapping("${entityName?uncap_first}")
public class ${entityName}Controller {

    private MessageHelper messageHelper;
    private IbExportor exportor;
    private ${entityName}Service ${entityName?uncap_first}Service;

    @RequestMapping("${entityName?uncap_first}-list")
    public String list(@ModelAttribute IbPage page, @RequestParam Map<String, Object> parameterMap, Model model) {
        // 查询条件Filter过滤器
        List<IbPropertyFilter> propertyFilters = IbPropertyFilter.buildFromMap(parameterMap);
        // 根据条件查询数据
        page = ${entityName?uncap_first}Service.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);
        // 返回JSP
        return "../jsp/codebpm/${entityPackage}/${entityName?uncap_first}-list";
    }
    
    /**
     * 插入
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("${entityName?uncap_first}-input")
    public String input(@RequestParam(value = "id", required = false) String id, Model model) {
        ${entityName}Entity entity = null;
        if (!CommonUtils.isNull(id)) {
            entity = ${entityName?uncap_first}Service.get(id);
        } else {
            entity = new ${entityName}Entity();
        }
        model.addAttribute("model", entity);
        
        return "../jsp/codebpm/${entityPackage}/${entityName?uncap_first}-input";
    }

    /**
     * 保存
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("${entityName?uncap_first}-save")
    public String save(@ModelAttribute ${entityName}Entity entity, RedirectAttributes redirectAttributes) throws Exception {
        // 先进行校验
        // 再进行数据复制
        String id = entity.getId();
        if (CommonUtils.isNull(id)) {
            entity.setId(UUID.randomUUID().toString());
            ${entityName?uncap_first}Service.insert(entity);
        } else {
            ${entityName?uncap_first}Service.update(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do";
    }
   /**
     * 删除
     * @param selectedItem
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("${entityName?uncap_first}-remove")
    public String remove(@RequestParam("selectedItem") List<String> selectedItem, RedirectAttributes redirectAttributes) {
        List<${entityName}Entity> entitys = ${entityName?uncap_first}Service.findByIds(selectedItem);
        for (${entityName}Entity entity : entitys) {
            ${entityName?uncap_first}Service.remove(entity);
        }
        messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");

        return "redirect:/${entityName?uncap_first}/${entityName?uncap_first}-list.do";
    }


    /**
     * 导出Excel
     * 
     * @param page
     * @param parameterMap
     * @param response
     * @throws Exception
     */
    @RequestMapping("${entityName?uncap_first}-export")
    public void export(@ModelAttribute IbPage page, @RequestParam Map<String, Object> parameterMap, HttpServletResponse response) throws Exception {
        List<IbPropertyFilter> propertyFilters = IbPropertyFilter.buildFromMap(parameterMap);
        page = ${entityName?uncap_first}Service.pagedQuery(page, propertyFilters);
        IbTableModel tableModel = new IbTableModel();
        tableModel.setName("导出${tableName}");
        // 
        tableModel.addHeaders("id");
        tableModel.setData((List) page.getResult());
        exportor.export(response, tableModel);
    }
    
    // ======================================================================
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    @Resource
    public void setIbExportor(IbExportor exportor) {
        this.exportor = exportor;
    }

    @Resource
    public void set${entityName}Service(${entityName}Service ${entityName?uncap_first}Service) {
        this.${entityName?uncap_first}Service = ${entityName?uncap_first}Service;
    }
    
}
