package fun.mochen.video.user.mapper;

import java.util.List;
import fun.mochen.video.user.domain.MovUserInfo;

/**
 * 网站用户信息Mapper接口
 * 
 * @author MoChen
 * @date 2022-11-02
 */
public interface MovUserInfoMapper 
{
    /**
     * 查询网站用户信息
     * 
     * @param id 网站用户信息主键
     * @return 网站用户信息
     */
    public MovUserInfo selectMovUserInfoById(Long id);

    /**
     * 查询网站用户信息列表
     * 
     * @param movUserInfo 网站用户信息
     * @return 网站用户信息集合
     */
    public List<MovUserInfo> selectMovUserInfoList(MovUserInfo movUserInfo);

    /**
     * 新增网站用户信息
     * 
     * @param movUserInfo 网站用户信息
     * @return 结果
     */
    public int insertMovUserInfo(MovUserInfo movUserInfo);

    /**
     * 修改网站用户信息
     * 
     * @param movUserInfo 网站用户信息
     * @return 结果
     */
    public int updateMovUserInfo(MovUserInfo movUserInfo);

    /**
     * 删除网站用户信息
     * 
     * @param id 网站用户信息主键
     * @return 结果
     */
    public int deleteMovUserInfoById(Long id);

    /**
     * 批量删除网站用户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMovUserInfoByIds(Long[] ids);
}
