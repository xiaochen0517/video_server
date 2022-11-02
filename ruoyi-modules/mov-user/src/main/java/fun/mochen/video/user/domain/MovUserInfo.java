package fun.mochen.video.user.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 网站用户信息对象 mov_user_info
 * 
 * @author MoChen
 * @date 2022-11-02
 */
public class MovUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户信息ID */
    private Long id;

    /** 名 */
    @Excel(name = "名")
    private String firstName;

    /** 姓 */
    @Excel(name = "姓")
    private String lastName;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idCard;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 血型 */
    @Excel(name = "血型")
    private String bloodType;

    /** 职业 */
    @Excel(name = "职业")
    private String profession;

    /** 家乡 */
    @Excel(name = "家乡")
    private String hometown;

    /** 居住地 */
    @Excel(name = "居住地")
    private String residence;

    /** 实名认证 */
    @Excel(name = "实名认证")
    private String verified;

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
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getFirstName() 
    {
        return firstName;
    }
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public String getLastName() 
    {
        return lastName;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setBloodType(String bloodType) 
    {
        this.bloodType = bloodType;
    }

    public String getBloodType() 
    {
        return bloodType;
    }
    public void setProfession(String profession) 
    {
        this.profession = profession;
    }

    public String getProfession() 
    {
        return profession;
    }
    public void setHometown(String hometown) 
    {
        this.hometown = hometown;
    }

    public String getHometown() 
    {
        return hometown;
    }
    public void setResidence(String residence) 
    {
        this.residence = residence;
    }

    public String getResidence() 
    {
        return residence;
    }
    public void setVerified(String verified) 
    {
        this.verified = verified;
    }

    public String getVerified() 
    {
        return verified;
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
            .append("firstName", getFirstName())
            .append("lastName", getLastName())
            .append("gender", getGender())
            .append("idCard", getIdCard())
            .append("birthday", getBirthday())
            .append("bloodType", getBloodType())
            .append("profession", getProfession())
            .append("hometown", getHometown())
            .append("residence", getResidence())
            .append("verified", getVerified())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
