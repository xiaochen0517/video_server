package fun.mochen.video.user.controller.api;


import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.service.TokenService;
import fun.mochen.video.user.domain.form.LoginBody;
import fun.mochen.video.user.domain.vo.MovUserVo;
import fun.mochen.video.user.service.IMovApiLoginService;
import fun.mochen.video.user.utils.UserLoginObjUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/user")
public class ApiMovUserController {

    @Autowired
    private IMovApiLoginService apiLoginService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
            MovUserVo movUserVo = apiLoginService.login(request, loginBody.getUsername(), loginBody.getPassword());
            AjaxResult ajax = AjaxResult.success("登录成功", movUserVo);
            ajax.put("token", tokenService.createToken(UserLoginObjUtils.movUserVoToLoginUser(movUserVo)));
            return ajax;
        }catch (ServiceException exception){
            return AjaxResult.error(exception.getMessage());
        }
    }
}
