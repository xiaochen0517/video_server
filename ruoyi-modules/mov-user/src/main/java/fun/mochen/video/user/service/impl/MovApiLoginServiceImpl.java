package fun.mochen.video.user.service.impl;

import cn.hutool.core.lang.Validator;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import fun.mochen.video.user.domain.MovUser;
import fun.mochen.video.user.domain.form.RegisterBody;
import fun.mochen.video.user.domain.vo.MovUserVo;
import fun.mochen.video.user.service.IMovApiLoginService;
import fun.mochen.video.user.service.IMovUserLoginService;
import fun.mochen.video.user.service.IMovUserService;
import fun.mochen.video.user.utils.UserLoginLogUtils;
import fun.mochen.video.user.utils.aliyun.MovAliyunClientProfile;
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

    @Autowired
    private MovAliyunClientProfile aliyunClientProfile;

    @Override
    public MovUserVo login(HttpServletRequest request, String username, String password) throws ServiceException {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            loginService.insertMovUserLogin(UserLoginLogUtils.getFailureLoginLog(request, username, "用户/密码不可为空"));
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

    @Override
    public MovUserVo register(RegisterBody registerBody) {
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();
        String email = registerBody.getEmail();
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password, email)) {
            throw new ServiceException("用户/密码必须填写");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException("用户名不在指定范围");
        }
        // 邮箱格式错误
        if (!Validator.isEmail(email)) {
            throw new ServiceException("请输入正确的邮箱");
        }
        return null;
    }

    public SingleSendMailResponse sendEmailCode() throws ClientException {
        IAcsClient client = aliyunClientProfile.getDmClient();
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("mochen@mochenqn.top");
            request.setFromAlias("MoChen");//发信人昵称，长度小于15个字符。
            request.setAddressType(1);//0：为随机账号 1：为发信地址
            request.setTagName("MoChenVideo");
            request.setReplyToAddress(false);// 是否启用管理控制台中配置好回信地址（状态须验证通过），取值范围是字符串true或者false
            request.setToAddress("xiaochen0517@qq.com");
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("验证码");
            //如果采用byte[].toString的方式的话请确保最终转换成utf-8的格式再放入htmlbody和textbody，若编码不一致则会被当成垃圾邮件。
            //注意：文本邮件的大小限制为3M，过大的文本会导致连接超时或413错误
            request.setHtmlBody("邮件正文");
            //SDK 采用的是http协议的发信方式, 默认是GET方法，有一定的长度限制。
            //若textBody、htmlBody或content的大小不确定，建议采用POST方式提交，避免出现uri is not valid异常
            request.setMethod(MethodType.POST);
            //开启需要备案，0关闭，1开启
            //request.setClickTrace("0");
            //如果调用成功，正常返回httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            return httpResponse;
        } catch (ServerException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        } catch (ClientException e) {
            //捕获错误异常码
            System.out.println("ErrCode : " + e.getErrCode());
            e.printStackTrace();
        }
        return null;
    }

}
