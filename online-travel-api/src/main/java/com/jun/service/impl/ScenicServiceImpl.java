package com.jun.service.impl;

import com.jun.context.LocalThreadHolder;
import com.jun.mapper.ScenicMapper;
import com.jun.mapper.VendorMapper;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicQueryDto;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.Scenic;
import com.jun.pojo.vo.ScenicVO;
import com.jun.pojo.vo.SelectedVO;
import com.jun.pojo.vo.VendorVO;
import com.jun.service.ScenicService;
import com.jun.utils.TextUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 供应商的业务逻辑实现类
 */
@Service
public class ScenicServiceImpl implements ScenicService {

    @Resource
    private ScenicMapper scenicMapper;

    @Resource
    private VendorMapper vendorMapper;

    /**
     * 供应商新增
     *
     * @param scenic 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(Scenic scenic) {
        // 景点初始可用状态
        scenic.setStatus(true);
        // 设置初始时间
        scenic.setCreateTime(LocalDateTime.now());
        // 设置供应商ID
        scenic.setVendorId(getVendorId());
        scenicMapper.save(scenic);
        return ApiResult.success();
    }

    /**
     * 供应商修改
     *
     * @param scenic 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(Scenic scenic) {
        scenicMapper.update(scenic);
        return ApiResult.success();
    }

    /**
     * 供应商批量删除
     *
     * @param ids 供应商ID列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        scenicMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 供应商查询
     *
     * @param dto 查询实体
     * @return Result<List < ScenicVO>>
     */
    @Override
    public Result<List<ScenicVO>> query(ScenicQueryDto dto) {
        Integer totalCount = scenicMapper.queryCount(dto);
        List<ScenicVO> result = scenicMapper.query(dto);
        return ApiResult.success(result, totalCount);
    }

    /**
     * 查询与当前供应商相关的景点
     *
     * @param dto 查询实体
     * @return Result<List < ScenicVO>>
     */
    @Override
    public Result<List<ScenicVO>> queryVendorScenic(ScenicQueryDto dto) {
        // 设置供应商ID
        dto.setVendorId(getVendorId());
        return query(dto);
    }

    /**
     * 获取当前供应商ID
     *
     * @return Integer
     */
    private Integer getVendorId() {
        VendorQueryDto vendorQueryDto = new VendorQueryDto();
        // 由用户ID查回来的供应商信息
        vendorQueryDto.setUserId(LocalThreadHolder.getUserId());
        List<VendorVO> vendorVOS = vendorMapper.query(vendorQueryDto);
        // 1.要么什么都没有 2.有的话，只有一项
        if (vendorVOS.isEmpty()) {
            //return ApiResult.error("供应商身份异常");
            return null;
        }
        return vendorVOS.get(0).getId();
    }

    /**
     * 关联景点下拉选择器
     *
     * @return Result<List < SelectedVO>>
     */
    @Override
    public Result<List<SelectedVO>> querySelectedScenic() {
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        // 只能查可用状态的景点信息
        scenicQueryDto.setStatus(true);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        // 再次封装
        List<SelectedVO> selectedScenicList = scenicVOS.stream()
                .map(scenicVO -> new SelectedVO(
                        scenicVO.getId(),
                        scenicVO.getName()
                )).collect(Collectors.toList());
        return ApiResult.success(selectedScenicList);
    }

    /**
     * 浏览操作
     *
     * @param scenicId 景点ID
     * @return Result<Void>
     */
    @Override
    public Result<Void> viewOperation(Integer scenicId) {
        // 1.根据景点ID查景点信息
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        scenicQueryDto.setId(scenicId);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        if (scenicVOS.isEmpty()) {
            return ApiResult.error("景点不存在");
        }
        ScenicVO scenicVO = scenicVOS.get(0);
        String viewIds = scenicVO.getViewIds();
        // 已经存在，不需要处理了
        if (TextUtils.exitId(viewIds, LocalThreadHolder.getUserId())) {
            return ApiResult.success();
        }
        String newViewIds = TextUtils.join(viewIds, LocalThreadHolder.getUserId());
        // 填充新的信息
        Scenic scenic = new Scenic();
        scenic.setId(scenicId);
        scenic.setViewIds(newViewIds);
        // 修改
        scenicMapper.update(scenic);
        return ApiResult.success();
    }

    /**
     * 收藏操作 --- 收藏跟取消收藏是对立的
     *
     * @param scenicId 景点ID
     * @return Result<Void>
     */
    @Override
    public Result<Void> saveOperation(Integer scenicId) {
        // 第一步，根据景点ID，查询回来景点的信息
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        scenicQueryDto.setId(scenicId);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        if (scenicVOS.isEmpty()) {
            return ApiResult.error("景点不存在");
        }
        ScenicVO scenicVO = scenicVOS.get(0);
        String saveIds = scenicVO.getSaveIds();
        Scenic scenic = new Scenic();
        scenic.setId(scenicVO.getId());
        Integer userId = LocalThreadHolder.getUserId();
        // 最后都是更新，只是对于收藏用户ID列表（saveIds）字段的处理不一样
        // 实现方式1
        scenic.setSaveIds(
                TextUtils.exitId(saveIds, userId) ?
                        TextUtils.split(saveIds, userId) :
                        TextUtils.join(saveIds, userId)
        );
        // 实现方式2
        // 取消收藏操作
        // if (TextUtils.exitId(saveIds, LocalThreadHolder.getUserId())) {
        //      scenic.setSaveIds(TextUtils.split(saveIds, userId));
        // } else { // 收藏操作
        //      scenic.setSaveIds(TextUtils.join(saveIds, userId));
        // }
        // 更新字段
        scenicMapper.updateSaveIds(scenic);
        return ApiResult.success(TextUtils.exitId(saveIds, userId) ? "取消收藏成功" : "收藏成功");
    }

    /**
     * 查询用户的收藏景点状况
     *
     * @return Result<Void>
     */
    @Override
    public Result<Boolean> saveStatus(Integer scenicId) {
        // 第一步，根据景点ID，查询回来景点的信息
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        scenicQueryDto.setId(scenicId);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        if (scenicVOS.isEmpty()) {
            return ApiResult.error("景点不存在");
        }
        ScenicVO scenicVO = scenicVOS.get(0);
        String saveIds = scenicVO.getSaveIds();
        return ApiResult.success(TextUtils.exitId(saveIds, LocalThreadHolder.getUserId()));
    }

    /**
     * 查询用户收藏的景点信息
     *
     * @return Result<List < ScenicVO>>
     */
    @Override
    public Result<List<ScenicVO>> querySave() {
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        // 设置用户ID
        Integer userId = LocalThreadHolder.getUserId();
        scenicQueryDto.setSaveIds(String.valueOf(userId));
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        return ApiResult.success(scenicVOS);
    }

}
