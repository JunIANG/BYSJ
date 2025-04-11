package com.jun.service.impl;

import com.jun.context.LocalThreadHolder;
import com.jun.mapper.HotelMapper;
import com.jun.mapper.VendorMapper;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelQueryDto;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.Hotel;
import com.jun.pojo.vo.HotelVO;
import com.jun.pojo.vo.VendorVO;
import com.jun.service.HotelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 酒店的业务逻辑实现类
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Resource
    private HotelMapper hotelMapper;

    @Resource
    private VendorMapper vendorMapper;

    /**
     * 酒店新增
     *
     * @param hotel 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(Hotel hotel) {
        // 设置初始时间
        hotel.setCreateTime(LocalDateTime.now());
        // 设置供应商ID
        hotel.setVendorId(getVendorId());
        hotelMapper.save(hotel);
        return ApiResult.success();
    }

    /**
     * 酒店修改
     *
     * @param hotel 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(Hotel hotel) {
        hotelMapper.update(hotel);
        return ApiResult.success();
    }

    /**
     * 酒店批量删除
     *
     * @param ids 酒店ID列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        hotelMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 酒店查询
     *
     * @param dto 查询实体
     * @return Result<List < HotelVO>>
     */
    @Override
    public Result<List<HotelVO>> query(HotelQueryDto dto) {
        Integer totalCount = hotelMapper.queryCount(dto);
        List<HotelVO> result = hotelMapper.query(dto);
        return ApiResult.success(result, totalCount);
    }

    /**
     * 查询供应商名下维护的酒店信息
     *
     * @param dto 查询实体
     * @return Result<List < HotelVO>>
     */
    @Override
    public Result<List<HotelVO>> queryHotelVendor(HotelQueryDto dto) {
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


}
