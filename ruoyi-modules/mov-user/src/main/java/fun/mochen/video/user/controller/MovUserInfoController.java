package fun.mochen.video.user.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import fun.mochen.video.user.domain.MovUserInfo;
import fun.mochen.video.user.service.IMovUserInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 网站用户信息Controller
 * 
 * @author MoChen
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/userinfo")
public class MovUserInfoController extends BaseController
{
    @Autowired
    private IMovUserInfoService movUserInfoService;

    /**
     * 查询网站用户信息列表
     */
    @RequiresPermissions("mov:userinfo:list")
    @GetMapping("/list")
    public TableDataInfo list(MovUserInfo movUserInfo)
    {
        startPage();
        List<MovUserInfo> list = movUserInfoService.selectMovUserInfoList(movUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出网站用户信息列表
     */
    @RequiresPermissions("mov:userinfo:export")
    @Log(title = "网站用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MovUserInfo movUserInfo)
    {
        List<MovUserInfo> list = movUserInfoService.selectMovUserInfoList(movUserInfo);
        ExcelUtil<MovUserInfo> util = new ExcelUtil<MovUserInfo>(MovUserInfo.class);
        util.exportExcel(response, list, "网站用户信息数据");
    }

    /**
     * 获取网站用户信息详细信息
     */
    @RequiresPermissions("mov:userinfo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(movUserInfoService.selectMovUserInfoById(id));
    }

    /**
     * 新增网站用户信息
     */
    @RequiresPermissions("mov:userinfo:add")
    @Log(title = "网站用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MovUserInfo movUserInfo)
    {
        return toAjax(movUserInfoService.insertMovUserInfo(movUserInfo));
    }

    /**
     * 修改网站用户信息
     */
    @RequiresPermissions("mov:userinfo:edit")
    @Log(title = "网站用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MovUserInfo movUserInfo)
    {
        return toAjax(movUserInfoService.updateMovUserInfo(movUserInfo));
    }

    /**
     * 删除网站用户信息
     */
    @RequiresPermissions("mov:userinfo:remove")
    @Log(title = "网站用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(movUserInfoService.deleteMovUserInfoByIds(ids));
    }
}
