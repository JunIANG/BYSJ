package com.jun.service.impl;

import com.jun.context.LocalThreadHolder;
import com.jun.mapper.VendorMapper;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.ScenicCategory;
import com.jun.pojo.entity.Vendor;
import com.jun.pojo.vo.VendorVO;
import com.jun.service.VendorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 供应商的业务逻辑实现类
 */
@Service
public class VendorServiceImpl implements VendorService {

    @Resource
    private VendorMapper vendorMapper;

    /**
     * 供应商新增
     *
     * @param vendor 实体
     * @return
     */
    @Override
    public Result<Void> save(Vendor vendor) {
        // TODO 还有逻辑  --- 一个人只能有一条申请记录，除了管理员
        if (LocalThreadHolder.getRoleId() != 0) {
            VendorQueryDto queryDto = new VendorQueryDto();
            queryDto.setUserId(LocalThreadHolder.getUserId());
            Integer count = vendorMapper.queryCount(queryDto);
            if (count != 0) {
                return ApiResult.error("请勿重复申请");
            }
            // 设置供应商初始状态 --- 默认是好的，新增的时候只是没有审核
            vendor.setStatus(true);
            // 初始，未审核
            vendor.setIsAudit(false);
            // 设置上用户ID
            vendor.setUserId(LocalThreadHolder.getUserId());
        }
        // 设置创建时间
        vendor.setCreateTime(LocalDateTime.now());
        vendorMapper.save(vendor);
        return ApiResult.success();
    }

    /**
     * 供应商修改
     *
     * @param vendor 实体
     * @return
     */
    @Override
    public Result<Void> update(Vendor vendor) {
        vendorMapper.update(vendor);
        return ApiResult.success();
    }

    /**
     * 供应商批量删除
     *
     * @param ids 供应商ID列表
     * @return
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        vendorMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 供应商查询
     *
     * @param dto 查询实体
     * @return
     */
    @Override
    public Result<List<VendorVO>> query(VendorQueryDto dto) {
        Integer totalCount = vendorMapper.queryCount(dto);
        List<VendorVO> result = vendorMapper.query(dto);
        return ApiResult.success(result, totalCount);
    }
}
