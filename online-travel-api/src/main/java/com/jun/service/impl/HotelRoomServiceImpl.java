package com.jun.service.impl;

import com.jun.context.LocalThreadHolder;
import com.jun.mapper.HotelMapper;
import com.jun.mapper.HotelRoomMapper;
import com.jun.mapper.VendorMapper;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelQueryDto;
import com.jun.pojo.dto.query.extend.HotelRoomQueryDto;
import com.jun.pojo.dto.query.extend.HotelRoomQueryParamDto;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.HotelRoom;
import com.jun.pojo.vo.HotelRoomVO;
import com.jun.pojo.vo.HotelVO;
import com.jun.pojo.vo.VendorVO;
import com.jun.service.HotelRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 酒店房间的业务逻辑实现类
 */
@Service
public class HotelRoomServiceImpl implements HotelRoomService {

    @Resource
    private HotelRoomMapper hotelRoomMapper;

    @Resource
    private VendorMapper vendorMapper;

    @Resource
    private HotelMapper hotelMapper;

    /**
     * 酒店房间新增
     *
     * @param hotelRoom 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(HotelRoom hotelRoom) {
        // 设置初始时间
        hotelRoom.setCreateTime(LocalDateTime.now());
        hotelRoomMapper.save(hotelRoom);
        return ApiResult.success();
    }

    /**
     * 酒店房间修改
     *
     * @param hotelRoom 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(HotelRoom hotelRoom) {
        hotelRoomMapper.update(hotelRoom);
        return ApiResult.success();
    }

    /**
     * 酒店房间批量删除
     *
     * @param ids 酒店房间ID列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        hotelRoomMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 酒店房间查询
     *
     * @param dto 查询实体
     * @return Result<List < HotelRoomVO>>
     */
    @Override
    public Result<List<HotelRoomVO>> query(HotelRoomQueryDto dto) {
        Integer totalCount = hotelRoomMapper.queryCount(dto);
        List<HotelRoomVO> result = hotelRoomMapper.query(dto);
        return ApiResult.success(result, totalCount);
    }

    /**
     * 查询由当前供应商所管理的酒店房间
     *
     * @return Result<List < HotelRoomVO>>
     */
    @Override
    public Result<List<HotelRoomVO>> queryHotelRoom(HotelRoomQueryDto dto) {
        // 用户ID --- 供应商ID --- 酒店ID --- 房间ID
        // 获取当前供应商所管理的酒店列表
        HotelQueryDto hotelQueryDto = new HotelQueryDto();
        hotelQueryDto.setVendorId(getVendorId());
        List<HotelVO> hotelVOS = hotelMapper.query(hotelQueryDto);
        // 获取供应商管理的酒店ID列表
        List<Integer> hotelIds = hotelVOS.stream()
                .map(HotelVO::getId)
                .collect(Collectors.toList());
        // 构造查询对象
        HotelRoomQueryParamDto roomQueryParamDto = new HotelRoomQueryParamDto(hotelIds, dto);
        // 由酒店ID查询房间信息
        List<HotelRoomVO> hotelRoomVOS = hotelRoomMapper.queryByHotelOfVendor(roomQueryParamDto);
        Integer totalCount = hotelRoomMapper.queryCountByHotelOfVendor(roomQueryParamDto);
        return ApiResult.success(hotelRoomVOS, totalCount);
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
