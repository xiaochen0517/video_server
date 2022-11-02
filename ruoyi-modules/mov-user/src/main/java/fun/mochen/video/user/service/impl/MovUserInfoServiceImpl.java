package fun.mochen.video.user.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fun.mochen.video.user.mapper.MovUserInfoMapper;
import fun.mochen.video.user.domain.MovUserInfo;
import fun.mochen.video.user.service.IMovUserInfoService;

/**
 * 网站用户信息Service业务层处理
 * 
 * @author MoChen
 * @date 2022-11-02
 */
@Service
public class MovUserInfoServiceImpl implements IMovUserInfoService 
{
    @Autowired
    private MovUserInfoMapper movUserInfoMapper;

    /**
     * 查询网站用户信息
     * 
     * @param id 网站用户信息主键
     * @return 网站用户信息
     */
    @Override
    public MovUserInfo selectMovUserInfoById(Long id)
    {
        return movUserInfoMapper.selectMovUserInfoById(id);
    }

    /**
     * 查询网站用户信息列表
     * 
     * @param movUserInfo 网站用户信息
     * @return 网站用户信息
     */
    @Override
    public List<MovUserInfo> selectMovUserInfoList(MovUserInfo movUserInfo)
    {
        return movUserInfoMapper.selectMovUserInfoList(movUserInfo);
    }

    /**
     * 新增网站用户信息
     * 
     * @param movUserInfo 网站用户信息
     * @return 结果
     */
    @Override
    public int insertMovUserInfo(MovUserInfo movUserInfo)
    {
        movUserInfo.setCreateTime(DateUtils.getNowDate());
        return movUserInfoMapper.insertMovUserInfo(movUserInfo);
    }

    /**
     * 修改网站用户信息
     * 
     * @param movUserInfo 网站用户信息
     * @return 结果
     */
    @Override
    public int updateMovUserInfo(MovUserInfo movUserInfo)
    {
        movUserInfo.setUpdateTime(DateUtils.getNowDate());
        return movUserInfoMapper.updateMovUserInfo(movUserInfo);
    }

    /**
     * 批量删除网站用户信息
     * 
     * @param ids 需要删除的网站用户信息主键
     * @return 结果
     */
    @Override
    public int deleteMovUserInfoByIds(Long[] ids)
    {
        return movUserInfoMapper.deleteMovUserInfoByIds(ids);
    }

    /**
     * 删除网站用户信息信息
     * 
     * @param id 网站用户信息主键
     * @return 结果
     */
    @Override
    public int deleteMovUserInfoById(Long id)
    {
        return movUserInfoMapper.deleteMovUserInfoById(id);
    }
}
