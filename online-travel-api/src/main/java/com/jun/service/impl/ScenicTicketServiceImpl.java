package com.jun.service.impl;

import com.jun.context.LocalThreadHolder;
import com.jun.mapper.ScenicMapper;
import com.jun.mapper.ScenicTicketMapper;
import com.jun.mapper.VendorMapper;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicQueryDto;
import com.jun.pojo.dto.query.extend.ScenicTicketQueryDto;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.ScenicTicket;
import com.jun.pojo.vo.ScenicTicketVO;
import com.jun.pojo.vo.ScenicVO;
import com.jun.pojo.vo.VendorVO;
import com.jun.service.ScenicTicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 景点门票的业务逻辑实现类
 */
@Service
public class ScenicTicketServiceImpl implements ScenicTicketService {

    @Resource
    private ScenicTicketMapper scenicTicketMapper;

    @Resource
    private VendorMapper vendorMapper;

    @Resource
    private ScenicMapper scenicMapper;

    /**
     * 景点门票新增
     *
     * @param scenicTicket 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(ScenicTicket scenicTicket) {
        // 设置初始时间
        scenicTicket.setCreateTime(LocalDateTime.now());
        scenicTicketMapper.save(scenicTicket);
        return ApiResult.success();
    }

    /**
     * 景点门票修改
     *
     * @param scenicTicket 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(ScenicTicket scenicTicket) {
        scenicTicketMapper.update(scenicTicket);
        return ApiResult.success();
    }

    /**
     * 景点门票批量删除
     *
     * @param ids 景点门票ID列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        scenicTicketMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 景点门票查询
     *
     * @param dto 查询实体
     * @return Result<List < ScenicTicketVO>>
     */
    @Override
    public Result<List<ScenicTicketVO>> query(ScenicTicketQueryDto dto) {
        Integer totalCount = scenicTicketMapper.queryCount(dto);
        List<ScenicTicketVO> result = scenicTicketMapper.query(dto);
        return ApiResult.success(result, totalCount);
    }

    /**
     * 查询供应商所管理的景点门票信息
     *
     * @return Result<List < ScenicTicketVO>>
     */
    @Override
    public Result<List<ScenicTicketVO>> queryScenicTicket() {
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        // 获取当前供应商所管理的景点信息
        scenicQueryDto.setVendorId(getVendorId());
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        // 取出所有景点的ID
        List<Integer> scenicIds = scenicVOS.stream()
                .map(ScenicVO::getId)
                .collect(Collectors.toList());
        // 由景点ID查门票信息
        List<ScenicTicketVO> scenicTicketVOS = scenicTicketMapper.queryByScenicIds(scenicIds);
        return ApiResult.success(scenicTicketVOS);
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

}
