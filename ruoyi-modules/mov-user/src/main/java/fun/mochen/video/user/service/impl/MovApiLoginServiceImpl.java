package fun.mochen.video.user.service.impl;

import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import fun.mochen.video.user.domain.MovUser;
import fun.mochen.video.user.domain.vo.MovUserVo;
import fun.mochen.video.user.service.IMovApiLoginService;
import fun.mochen.video.user.service.IMovUserLoginService;
import fun.mochen.video.user.service.IMovUserService;
import fun.mochen.video.user.utils.UserLoginLogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 网站用户相关服务
 *
 * @author MoChen
 * @time 2022/11/2
 */
@Service
public class MovApiLoginServiceImpl implements IMovApiLoginService {

    @Autowired
    private IMovUserLoginService loginService;

    @Autowired
    private IMovUserService userService;


    /**
     * 用户登录方法
     *
     * @param request  请求数据
     * @param username 用户名
     * @param password 密码
     * @return 登录成功，用户信息
     * @throws ServiceException 登录不成功抛出错误
     */
    @Override
    public MovUserVo login(HttpServletRequest request, String username, String password) throws ServiceException {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            loginService.insertMovUserLogin(UserLoginLogUtils.getFailureLoginLog(request, username, "用户/密码必须填写"));
            throw new ServiceException("用户/密码必须填写");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            loginService.insertMovUserLogin(UserLoginLogUtils.getFailureLoginLog(request, username, "用户名不在指定范围"));
            throw new ServiceException("用户名不在指定范围");
        }
        // 查询用户信息
        MovUser movUser = userService.findUserByUsername(username);
        if (StringUtils.isNull(movUser) || StringUtils.isNull(movUser.getPassword())) {
            loginService.insertMovUserLogin(UserLoginLogUtils.getFailureLoginLog(request, username, "登录用户不存在"));
            throw new ServiceException("登录用户：" + username + " 不存在");
        }

        if (UserStatus.DELETED.getCode().equals(movUser.getDelFlag())) {
            loginService.insertMovUserLogin(UserLoginLogUtils.getFailureLoginLog(request, username, "账号已删除"));
            throw new ServiceException("对不起，您的账号：" + username + " 已删除");
        }
        if (UserStatus.DISABLE.getCode().equals(movUser.getStatus())) {
            loginService.insertMovUserLogin(UserLoginLogUtils.getFailureLoginLog(request, username, "用户已停用"));
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        if (!movUser.getPassword().equals(password)) {
            loginService.insertMovUserLogin(UserLoginLogUtils.getFailureLoginLog(request, username, "密码错误"));
            throw new ServiceException("账号：" + movUser.getUsername() + " 密码错误，请检查后重试");
        }
        loginService.insertMovUserLogin(UserLoginLogUtils.getSuccessLoginLog(request, username, "登录成功"));
        return BeanUtils.copyBeanProp(movUser, MovUserVo.class);
    }

}
