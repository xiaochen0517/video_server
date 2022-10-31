package fun.mochen.video.user.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fun.mochen.video.user.mapper.MovUserMapper;
import fun.mochen.video.user.domain.MovUser;
import fun.mochen.video.user.service.IMovUserService;

/**
 * 网站用户Service业务层处理
 * 
 * @author MoChen
 * @date 2022-10-31
 */
@Service
public class MovUserServiceImpl implements IMovUserService 
{
    @Autowired
    private MovUserMapper movUserMapper;

    /**
     * 查询网站用户
     * 
     * @param id 网站用户主键
     * @return 网站用户
     */
    @Override
    public MovUser selectMovUserById(Long id)
    {
        return movUserMapper.selectMovUserById(id);
    }

    /**
     * 查询网站用户列表
     * 
     * @param movUser 网站用户
     * @return 网站用户
     */
    @Override
    public List<MovUser> selectMovUserList(MovUser movUser)
    {
        return movUserMapper.selectMovUserList(movUser);
    }

    /**
     * 新增网站用户
     * 
     * @param movUser 网站用户
     * @return 结果
     */
    @Override
    public int insertMovUser(MovUser movUser)
    {
        movUser.setCreateTime(DateUtils.getNowDate());
        return movUserMapper.insertMovUser(movUser);
    }

    /**
     * 修改网站用户
     * 
     * @param movUser 网站用户
     * @return 结果
     */
    @Override
    public int updateMovUser(MovUser movUser)
    {
        movUser.setUpdateTime(DateUtils.getNowDate());
        return movUserMapper.updateMovUser(movUser);
    }

    /**
     * 批量删除网站用户
     * 
     * @param ids 需要删除的网站用户主键
     * @return 结果
     */
    @Override
    public int deleteMovUserByIds(Long[] ids)
    {
        return movUserMapper.deleteMovUserByIds(ids);
    }

    /**
     * 删除网站用户信息
     * 
     * @param id 网站用户主键
     * @return 结果
     */
    @Override
    public int deleteMovUserById(Long id)
    {
        return movUserMapper.deleteMovUserById(id);
    }
}
