package fun.mochen.video.user.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 网站用户对象 mov_user
 * 
 * @author MoChen
 * @date 2022-11-02
 */
public class MovUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户名（英文/数字） */
    @Excel(name = "用户名", readConverterExp = "英=文/数字")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phonenumber;

    /** VIP过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "VIP过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vipTime;

    /** 帐号状态 */
    @Excel(name = "帐号状态")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setVipTime(Date vipTime) 
    {
        this.vipTime = vipTime;
    }

    public Date getVipTime() 
    {
        return vipTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("nickName", getNickName())
            .append("avatar", getAvatar())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("vipTime", getVipTime())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
