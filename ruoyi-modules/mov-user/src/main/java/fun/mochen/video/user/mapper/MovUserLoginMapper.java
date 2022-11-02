package fun.mochen.video.user.mapper;

import java.util.List;
import fun.mochen.video.user.domain.MovUserLogin;

/**
 * 网站用户登录信息Mapper接口
 * 
 * @author MoChen
 * @date 2022-11-02
 */
public interface MovUserLoginMapper 
{
    /**
     * 查询网站用户登录信息
     * 
     * @param id 网站用户登录信息主键
     * @return 网站用户登录信息
     */
    public MovUserLogin selectMovUserLoginById(Long id);

    /**
     * 查询网站用户登录信息列表
     * 
     * @param movUserLogin 网站用户登录信息
     * @return 网站用户登录信息集合
     */
    public List<MovUserLogin> selectMovUserLoginList(MovUserLogin movUserLogin);

    /**
     * 新增网站用户登录信息
     * 
     * @param movUserLogin 网站用户登录信息
     * @return 结果
     */
    public int insertMovUserLogin(MovUserLogin movUserLogin);

    /**
     * 修改网站用户登录信息
     * 
     * @param movUserLogin 网站用户登录信息
     * @return 结果
     */
    public int updateMovUserLogin(MovUserLogin movUserLogin);

    /**
     * 删除网站用户登录信息
     * 
     * @param id 网站用户登录信息主键
     * @return 结果
     */
    public int deleteMovUserLoginById(Long id);

    /**
     * 批量删除网站用户登录信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMovUserLoginByIds(Long[] ids);
}
