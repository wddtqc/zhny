package com.ruoyi.controller.system;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AgricultureGermplasm;
import com.ruoyi.system.service.IAgricultureGermplasmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 种质Controller
 * 
 * @author ruoyi
 * @date 2026-06-02
 */
@RestController
@RequestMapping("/system/germplasm")
public class AgricultureGermplasmController extends BaseController
{
    @Autowired
    private IAgricultureGermplasmService agricultureGermplasmService;

    /**
     * 查询种质列表
     */
    @PreAuthorize("@ss.hasPermi('system:germplasm:list')")
    @GetMapping("/list")
    public TableDataInfo list(AgricultureGermplasm agricultureGermplasm)
    {
        startPage();
        List<AgricultureGermplasm> list = agricultureGermplasmService.selectAgricultureGermplasmList(agricultureGermplasm);
        return getDataTable(list);
    }

    /**
     * 导出种质列表
     */
    @PreAuthorize("@ss.hasPermi('system:germplasm:export')")
    @Log(title = "种质", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AgricultureGermplasm agricultureGermplasm)
    {
        List<AgricultureGermplasm> list = agricultureGermplasmService.selectAgricultureGermplasmList(agricultureGermplasm);
        ExcelUtil<AgricultureGermplasm> util = new ExcelUtil<AgricultureGermplasm>(AgricultureGermplasm.class);
        util.exportExcel(response, list, "种质数据");
    }

    /**
     * 获取种质详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:germplasm:query')")
    @GetMapping(value = "/{germplasmId}")
    public AjaxResult getInfo(@PathVariable("germplasmId") String germplasmId)
    {
        return success(agricultureGermplasmService.selectAgricultureGermplasmByGermplasmId(germplasmId));
    }

    /**
     * 新增种质
     */
    @PreAuthorize("@ss.hasPermi('system:germplasm:add')")
    @Log(title = "种质", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AgricultureGermplasm agricultureGermplasm)
    {
        return toAjax(agricultureGermplasmService.insertAgricultureGermplasm(agricultureGermplasm));
    }

    /**
     * 修改种质
     */
    @PreAuthorize("@ss.hasPermi('system:germplasm:edit')")
    @Log(title = "种质", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AgricultureGermplasm agricultureGermplasm)
    {
        return toAjax(agricultureGermplasmService.updateAgricultureGermplasm(agricultureGermplasm));
    }

    /**
     * 删除种质
     */
    @PreAuthorize("@ss.hasPermi('system:germplasm:remove')")
    @Log(title = "种质", businessType = BusinessType.DELETE)
	@DeleteMapping("/{germplasmIds}")
    public AjaxResult remove(@PathVariable String[] germplasmIds)
    {
        return toAjax(agricultureGermplasmService.deleteAgricultureGermplasmByGermplasmIds(germplasmIds));
    }
}
