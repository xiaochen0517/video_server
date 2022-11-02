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
import fun.mochen.video.user.domain.MovUserLogin;
import fun.mochen.video.user.service.IMovUserLoginService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 网站用户登录信息Controller
 * 
 * @author MoChen
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/userlogin")
public class MovUserLoginController extends BaseController
{
    @Autowired
    private IMovUserLoginService movUserLoginService;

    /**
     * 查询网站用户登录信息列表
     */
    @RequiresPermissions("mov:userlogin:list")
    @GetMapping("/list")
    public TableDataInfo list(MovUserLogin movUserLogin)
    {
        startPage();
        List<MovUserLogin> list = movUserLoginService.selectMovUserLoginList(movUserLogin);
        return getDataTable(list);
    }

    /**
     * 导出网站用户登录信息列表
     */
    @RequiresPermissions("mov:userlogin:export")
    @Log(title = "网站用户登录信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MovUserLogin movUserLogin)
    {
        List<MovUserLogin> list = movUserLoginService.selectMovUserLoginList(movUserLogin);
        ExcelUtil<MovUserLogin> util = new ExcelUtil<MovUserLogin>(MovUserLogin.class);
        util.exportExcel(response, list, "网站用户登录信息数据");
    }

    /**
     * 获取网站用户登录信息详细信息
     */
    @RequiresPermissions("mov:userlogin:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(movUserLoginService.selectMovUserLoginById(id));
    }

    /**
     * 新增网站用户登录信息
     */
    @RequiresPermissions("mov:userlogin:add")
    @Log(title = "网站用户登录信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MovUserLogin movUserLogin)
    {
        return toAjax(movUserLoginService.insertMovUserLogin(movUserLogin));
    }

    /**
     * 修改网站用户登录信息
     */
    @RequiresPermissions("mov:userlogin:edit")
    @Log(title = "网站用户登录信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MovUserLogin movUserLogin)
    {
        return toAjax(movUserLoginService.updateMovUserLogin(movUserLogin));
    }

    /**
     * 删除网站用户登录信息
     */
    @RequiresPermissions("mov:userlogin:remove")
    @Log(title = "网站用户登录信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(movUserLoginService.deleteMovUserLoginByIds(ids));
    }
}
