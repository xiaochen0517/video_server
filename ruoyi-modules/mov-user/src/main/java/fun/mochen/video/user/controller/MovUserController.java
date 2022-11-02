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
import fun.mochen.video.user.domain.MovUser;
import fun.mochen.video.user.service.IMovUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 网站用户Controller
 * 
 * @author MoChen
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/user")
public class MovUserController extends BaseController
{
    @Autowired
    private IMovUserService movUserService;

    /**
     * 查询网站用户列表
     */
    @RequiresPermissions("mov:user:list")
    @GetMapping("/list")
    public TableDataInfo list(MovUser movUser)
    {
        startPage();
        List<MovUser> list = movUserService.selectMovUserList(movUser);
        return getDataTable(list);
    }

    /**
     * 导出网站用户列表
     */
    @RequiresPermissions("mov:user:export")
    @Log(title = "网站用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MovUser movUser)
    {
        List<MovUser> list = movUserService.selectMovUserList(movUser);
        ExcelUtil<MovUser> util = new ExcelUtil<MovUser>(MovUser.class);
        util.exportExcel(response, list, "网站用户数据");
    }

    /**
     * 获取网站用户详细信息
     */
    @RequiresPermissions("mov:user:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(movUserService.selectMovUserById(id));
    }

    /**
     * 新增网站用户
     */
    @RequiresPermissions("mov:user:add")
    @Log(title = "网站用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MovUser movUser)
    {
        return toAjax(movUserService.insertMovUser(movUser));
    }

    /**
     * 修改网站用户
     */
    @RequiresPermissions("mov:user:edit")
    @Log(title = "网站用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MovUser movUser)
    {
        return toAjax(movUserService.updateMovUser(movUser));
    }

    /**
     * 删除网站用户
     */
    @RequiresPermissions("mov:user:remove")
    @Log(title = "网站用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(movUserService.deleteMovUserByIds(ids));
    }
}
