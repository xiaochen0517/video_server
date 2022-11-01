package fun.mochen.video.user.controller.api;


import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class ApiMovUserController {

    @PostMapping("login")
    public AjaxResult login(){
        return null;
    }
}
