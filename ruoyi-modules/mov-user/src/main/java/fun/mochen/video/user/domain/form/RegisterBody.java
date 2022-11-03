package fun.mochen.video.user.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterBody {

    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户名（英文/数字） */
    private String username;

    /** 密码 */
    private String password;

    /** 昵称 */
    private String nickName;

    /** 邮箱 */
    private String email;

    /** 电话号码 */
    private String phonenumber;

}
