package fun.mochen.video.user.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fun.mochen.video.user.mapper.MovUserLoginMapper;
import fun.mochen.video.user.domain.MovUserLogin;
import fun.mochen.video.user.service.IMovUserLoginService;

/**
 * 网站用户登录信息Service业务层处理
 * 
 * @author MoChen
 * @date 2022-11-02
 */
@Service
public class MovUserLoginServiceImpl implements IMovUserLoginService 
{
    @Autowired
    private MovUserLoginMapper movUserLoginMapper;

    /**
     * 查询网站用户登录信息
     * 
     * @param id 网站用户登录信息主键
     * @return 网站用户登录信息
     */
    @Override
    public MovUserLogin selectMovUserLoginById(Long id)
    {
        return movUserLoginMapper.selectMovUserLoginById(id);
    }

    /**
     * 查询网站用户登录信息列表
     * 
     * @param movUserLogin 网站用户登录信息
     * @return 网站用户登录信息
     */
    @Override
    public List<MovUserLogin> selectMovUserLoginList(MovUserLogin movUserLogin)
    {
        return movUserLoginMapper.selectMovUserLoginList(movUserLogin);
    }

    /**
     * 新增网站用户登录信息
     * 
     * @param movUserLogin 网站用户登录信息
     * @return 结果
     */
    @Override
    public int insertMovUserLogin(MovUserLogin movUserLogin)
    {
        movUserLogin.setCreateTime(DateUtils.getNowDate());
        return movUserLoginMapper.insertMovUserLogin(movUserLogin);
    }

    /**
     * 修改网站用户登录信息
     * 
     * @param movUserLogin 网站用户登录信息
     * @return 结果
     */
    @Override
    public int updateMovUserLogin(MovUserLogin movUserLogin)
    {
        movUserLogin.setUpdateTime(DateUtils.getNowDate());
        return movUserLoginMapper.updateMovUserLogin(movUserLogin);
    }

    /**
     * 批量删除网站用户登录信息
     * 
     * @param ids 需要删除的网站用户登录信息主键
     * @return 结果
     */
    @Override
    public int deleteMovUserLoginByIds(Long[] ids)
    {
        return movUserLoginMapper.deleteMovUserLoginByIds(ids);
    }

    /**
     * 删除网站用户登录信息信息
     * 
     * @param id 网站用户登录信息主键
     * @return 结果
     */
    @Override
    public int deleteMovUserLoginById(Long id)
    {
        return movUserLoginMapper.deleteMovUserLoginById(id);
    }
}
