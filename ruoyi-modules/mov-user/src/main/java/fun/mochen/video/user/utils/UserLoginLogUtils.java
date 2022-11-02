package fun.mochen.video.user.utils;

import com.ruoyi.common.core.constant.MovUserConstants;
import com.ruoyi.common.core.utils.StringUtils;
import fun.mochen.video.user.domain.MovUserLogin;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户登录工具类
 *
 * @author MoChen
 * @time 2022/11/2
 */
public class UserLoginLogUtils {

    /**
     * 获取成功登录日志对象
     *
     * @param request  请求
     * @param username 用户名
     * @param loginMsg 登录信息
     * @return 登录日志对象
     */
    public static MovUserLogin getSuccessLoginLog(HttpServletRequest request, String username, String loginMsg) {
        return getUserLoginLogObj(request, username, MovUserConstants.LOGIN_STATUS_SUCCESS, loginMsg);
    }

    /**
     * 获取登录失败日志对象
     *
     * @param request  请求
     * @param username 用户名
     * @param loginMsg 登录信息
     * @return 登录日志对象
     */
    public static MovUserLogin getFailureLoginLog(HttpServletRequest request, String username, String loginMsg) {
        return getUserLoginLogObj(request, username, MovUserConstants.LOGIN_STATUS_FAILURE, loginMsg);
    }

    /**
     * 获取登录日志对象
     *
     * @param request     请求数据
     * @param loginStatus 是否成功
     * @param loginMsg    描述信息
     * @return 登录日志对象
     */
    private static MovUserLogin getUserLoginLogObj(HttpServletRequest request, String username, String loginStatus, String loginMsg) {
        MovUserLogin movUserLogin = new MovUserLogin();
        movUserLogin.setLoginTime(new Date());
        movUserLogin.setLoginIp(request.getRemoteAddr());
        movUserLogin.setSystemInfo(request.getHeader("User-Agent"));
        movUserLogin.setRemark(StringUtils.format("username: {}, msg: {}", username, loginMsg));
        movUserLogin.setStatus(loginStatus);
        return movUserLogin;
    }

}
