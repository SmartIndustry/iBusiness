package com.ibusiness.table.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibusiness.common.entity.ConfServiceModuleEntity;
import com.ibusiness.common.service.ServiceModuleService;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.table.entity.ConfTable;
import com.ibusiness.table.entity.ConfTableColumns;
import com.ibusiness.table.service.TableService;
import com.mossle.core.spring.MessageHelper;

/**
 * 数据库表结构管理
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("table")
public class TableManageController {

    // 共用Service,可以查询热力站,热源,BPM等基础信息
    private TableService tableService;
    private ServiceModuleService serviceModuleService;
    private MessageHelper messageHelper;

    /**
     * 业务表管理表信息查询
     * 
     * @return
     */
    @RequestMapping("conf-table-show")
    public String confTableShow(@RequestParam("packageName") String packageName, Model model) {
        // 取得表结构信息。
        List<ConfTable> list = tableService.queryConfTableList(packageName);
        // 表结构信息
        model.addAttribute("tableInfoList", list);
        model.addAttribute("packageName", packageName);
        
        return "../jsp/table/conf-table-show"; 
    }
    /**
     * 表列字段结构管理表信息查询
     * 
     * @return
     */
    @RequestMapping("conf-table-detail-show")
    public String queryConfTableDetail(@RequestParam("tableName") String tableName, Model model) {
    	// 取得表结构信息
        List<ConfTableColumns> list = tableService.queryConfTableColumns(tableName);
        // 表结构信息
        model.addAttribute("tableInfoList", list);
        model.addAttribute("tableName", tableName);
        // 取得表结构信息
        return "../jsp/table/conf-table-detail-show";
    }
    /**
     * 表列字段结构管理表信息查询
     * 
     * @return
     */
    @RequestMapping("conf-table-column-show")
    public String queryConfTableColumn(@RequestParam("tableName") String tableName, @RequestParam("columnValue") String columnValue, Model model) {
    	// 取得表结构信息
        ConfTableColumns bean = tableService.queryConfTableColumn(tableName, columnValue);
        // 表结构信息
        model.addAttribute("beanInfo", bean);
        model.addAttribute("tableName", tableName);
        // 取得表结构信息
        return "../jsp/table/conf-table-column-show";
    }
    /**
     * 新建一张业务表页面跳转
     * 
     * @return
     */
    @RequestMapping("conf-table-insert")
    public String confTableInsert(@RequestParam("packageName") String packageName, Model model) {
        model.addAttribute("packageName", packageName);
        return "../jsp/table/conf-table-insert"; 
    }
    
    /**
     * 保存业务表信息
     * 
     * @return
     */
    @RequestMapping("conf-table-save")
    public String confTableSave(@ModelAttribute ConfTable confTable, RedirectAttributes redirectAttributes) {
    	List<ConfTable> list = new ArrayList<ConfTable>();
    	confTable.setUuId(UUID.randomUUID().toString());
    	confTable.setTableName(confTable.getTableName().toUpperCase());//转成大写
    	list.add(confTable);
    	tableService.insertConfTable(list);
    	// 在数据库中创建一张业务表
		createTable(confTable);
		// 向业务模块树结构中添加一个节点
		String hql = "from ConfServiceModuleEntity csm where packagename='"+confTable.getPackageName()+"' and typeid='Table'";
		List<ConfServiceModuleEntity> entitys = serviceModuleService.find(hql);
		if (null != entitys && entitys.size() > 0) {
		    ConfServiceModuleEntity csmEntity = new ConfServiceModuleEntity();
		    csmEntity.setId(UUID.randomUUID().toString());
		    csmEntity.setPackagename(entitys.get(0).getPackagename());
		    csmEntity.setModulename(confTable.getTableNameComment());
		    csmEntity.setSubname(confTable.getTableName());
		    csmEntity.setParentid(entitys.get(0).getId());
		    csmEntity.setTypeid("tables");
		    // 插入
	        serviceModuleService.insert(csmEntity);
		}
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        return "redirect:/table/conf-table-show.do?packageName="+confTable.getPackageName();
    }
    /**
     * 保存表列字段结构信息
     * 
     * @return
     */
    @RequestMapping("conf-table-columns-save")
    public String confTableColumnsSave(@ModelAttribute ConfTableColumns confTableColumns, @RequestParam("tableName") String tableName, RedirectAttributes redirectAttributes) {
    	List<ConfTableColumns> list = new ArrayList<ConfTableColumns>();
    	confTableColumns.setTableName(tableName.toUpperCase());//转成大写
    	confTableColumns.setColumnValue(confTableColumns.getColumnValue().toUpperCase());//转成大写
    	list.add(confTableColumns);
    	// 插入
    	tableService.insertConfTableColumns(list);
    	// 在数据库中修改(更新/追加)指定的业务表的数据列
    	alterTableColumn(confTableColumns);
        messageHelper.addFlashMessage(redirectAttributes, "core.success.save", "保存成功");
        
        return "redirect:/table/conf-table-detail-show.do?tableName=" + tableName;
    }
    /**
     * 变更/修改表列字段管理表信息
     * 
     * @return
     */
    @RequestMapping("conf-table-columns-update")
    public String confTableColumnsUpdate(@ModelAttribute ConfTableColumns confTableColumns, @RequestParam("tableName") String tableName, RedirectAttributes redirectAttributes) {
    	confTableColumns.setColumnValue(confTableColumns.getColumnValue().toUpperCase());
    	tableService.updateConfTableColumns(tableName, confTableColumns);
    	messageHelper.addFlashMessage(redirectAttributes, "core.success.update", "更新成功");
    	// 更改表结构
    	alterTableColumn(confTableColumns);
        return "redirect:/table/conf-table-detail-show.do?tableName=" + tableName;
    }
    /**
     * 删除业务表管理表信息
     * 
     * @return
     */
    @RequestMapping("conf-table-remove")
    public String confTableRemove(@RequestParam("selectedItem") List<String> selectedItem, @RequestParam("packageName") String packageName, RedirectAttributes redirectAttributes) {
    	tableService.deleteConfTable(selectedItem);
    	messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
        return "redirect:/table/conf-table-show.do?packageName="+packageName;
    }
    /**
     * 删除表列字段管理表信息
     * 
     * @return
     */
    @RequestMapping("conf-table-columns-remove")
    public String confTableColumnsRemove(@RequestParam("selectedItem") List<String> selectedItem, @RequestParam("tableName") String tableName, RedirectAttributes redirectAttributes) {
    	tableService.deleteConfColumnsTable(selectedItem, tableName);
    	messageHelper.addFlashMessage(redirectAttributes, "core.success.delete", "删除成功");
    	// 在数据库中(删除)指定的业务表的数据列
    	for (String columnValue : selectedItem) {
    		alterDropTableColumns(tableName, columnValue);
    	}
        return "redirect:/table/conf-table-detail-show.do?tableName=" + tableName;
    }
    /**
     * 在数据库中创建一张业务表
     * @param list
     */
    private void createTable(ConfTable bean) {
    	String sql = "CREATE TABLE " + bean.getTableName() + " (TASKID VARCHAR(64))";
        if (0 == tableService.execute(sql)) {
            return;
        }
    }
    /**
     * 在数据库中修改(更新/追加)指定的业务表的数据列
     * @param list
     */
    private void alterTableColumn(ConfTableColumns bean) {
    	String defaultStr = "";
    	if (bean.getColumnType().indexOf("number") >= 0) {
		    defaultStr = " default 0";
	    }
    	// 插入数据列
		if (CommonUtils.isNull(bean.getColumnValueOld())) {
			String sql = "ALTER TABLE " + bean.getTableName() + " ADD " + bean.getColumnValue() + " " + bean.getColumnType();
			if (!CommonUtils.isNull(bean.getColumnSize())) {
				sql = sql + "(" + bean.getColumnSize() + ")";
			}
			sql = sql + defaultStr;
			if (0 == tableService.execute(sql)) {
				return;
			}
		} else {
			// 修改数据列名
		    if (!bean.getColumnValue().equals(bean.getColumnValueOld())) {
		    	String sql = "ALTER TABLE " + bean.getTableName() + " RENAME COLUMN " + bean.getColumnValueOld() + " TO " + bean.getColumnValue();
		        if (0 == tableService.execute(sql)) {
		            return;
		        }
		    }
	    	
		    // 修改数据列类型
			if (!bean.getColumnType().equals(bean.getColumnTypeOld())
					|| (CommonUtils.isNull(bean.getColumnSize()) && !bean.getColumnSize().equals(bean.getColumnSizeOld()))) {
				String sql = "ALTER TABLE " + bean.getTableName() + " MODIFY " + bean.getColumnValue() + " " + bean.getColumnType();
				if (!CommonUtils.isNull(bean.getColumnSize())) {
					sql = sql + "(" + bean.getColumnSize() + ")";
				}
				if (0 == tableService.execute(sql)) {
		            return;
		        }
			}
		}
    }
    /**
     * 删除指定表中的指定列字段-在指定表的表结构中删除列
     * @param tableName
     * @param columnValue
     */
    private void alterDropTableColumns(String tableName, String columnValue) {
    	String sql = "ALTER TABLE " + tableName + " DROP COLUMN " + columnValue;
        if (0 == tableService.execute(sql)) {
            return;
        }
	}
    /**
     * 注入 table service
     * @param tableService
     */
    @Resource
	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}
    @Resource
    public void setServiceModuleService(ServiceModuleService serviceModuleService) {
        this.serviceModuleService = serviceModuleService;
    }
    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
