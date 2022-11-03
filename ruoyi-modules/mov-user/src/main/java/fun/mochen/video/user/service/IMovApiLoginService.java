package fun.mochen.video.user.service;

import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.ruoyi.common.core.exception.ServiceException;
import fun.mochen.video.user.domain.form.RegisterBody;
import fun.mochen.video.user.domain.vo.MovUserVo;

import javax.servlet.http.HttpServletRequest;

public interface IMovApiLoginService {

    /**
     * 用户登录方法
     *
     * @param request  请求数据
     * @param username 用户名
     * @param password 密码
     * @return 登录成功，用户信息
     * @throws ServiceException 登录不成功抛出错误
     */
    public MovUserVo login(HttpServletRequest request, String username, String password) throws ServiceException;

    public MovUserVo register(RegisterBody registerBody);

    public SingleSendMailResponse sendEmailCode() throws ClientException;

}
