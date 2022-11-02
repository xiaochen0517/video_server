package fun.mochen.video.user.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovUserVo {


    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名（英文/数字）
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phonenumber;

    /**
     * VIP过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date vipTime;

}
