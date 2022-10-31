package fun.mochen.video.user.mapper;

import java.util.List;
import fun.mochen.video.user.domain.MovUser;

/**
 * 网站用户Mapper接口
 * 
 * @author MoChen
 * @date 2022-10-31
 */
public interface MovUserMapper 
{
    /**
     * 查询网站用户
     * 
     * @param id 网站用户主键
     * @return 网站用户
     */
    public MovUser selectMovUserById(Long id);

    /**
     * 查询网站用户列表
     * 
     * @param movUser 网站用户
     * @return 网站用户集合
     */
    public List<MovUser> selectMovUserList(MovUser movUser);

    /**
     * 新增网站用户
     * 
     * @param movUser 网站用户
     * @return 结果
     */
    public int insertMovUser(MovUser movUser);

    /**
     * 修改网站用户
     * 
     * @param movUser 网站用户
     * @return 结果
     */
    public int updateMovUser(MovUser movUser);

    /**
     * 删除网站用户
     * 
     * @param id 网站用户主键
     * @return 结果
     */
    public int deleteMovUserById(Long id);

    /**
     * 批量删除网站用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMovUserByIds(Long[] ids);
}
