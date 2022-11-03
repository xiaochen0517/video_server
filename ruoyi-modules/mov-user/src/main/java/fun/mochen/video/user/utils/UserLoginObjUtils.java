package fun.mochen.video.user.utils;

import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import fun.mochen.video.user.domain.vo.MovUserVo;

/**
 * 用户登录模块对象处理工具类
 *
 * @author MoChen
 * @time 2022/11/3
 */
public class UserLoginObjUtils {

    /**
     * 将movuser转换为sysuser并放入loginuser，用于生成token使用
     *
     * @param movUser 网站用户
     * @return loginuser
     */
    public static LoginUser movUserVoToLoginUser(MovUserVo movUser) {
        LoginUser loginUser = new LoginUser();
        SysUser sysUser = new SysUser();
        sysUser.setUserId(movUser.getId());
        sysUser.setUserName(movUser.getUsername());
        loginUser.setSysUser(sysUser);
        return loginUser;
    }
}
