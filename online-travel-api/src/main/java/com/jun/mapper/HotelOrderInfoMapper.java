package com.jun.mapper;

import com.jun.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import com.jun.pojo.dto.query.extend.HotelOrderInfoQueryParamDto;
import com.jun.pojo.entity.HotelOrderInfo;
import com.jun.pojo.vo.HotelOrderInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 酒店订单持久化接口
 */
@Mapper
public interface HotelOrderInfoMapper {

    void save(HotelOrderInfo hotelOrderInfo);

    void update(HotelOrderInfo hotelOrderInfo);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<HotelOrderInfoVO> query(HotelOrderInfoQueryDto dto);

    Integer queryCount(HotelOrderInfoQueryDto dto);

    List<HotelOrderInfoVO> queryByHotelRoomIds(HotelOrderInfoQueryParamDto paramDto);

    Integer queryCountByHotelRoomIds(HotelOrderInfoQueryParamDto paramDto);
}
