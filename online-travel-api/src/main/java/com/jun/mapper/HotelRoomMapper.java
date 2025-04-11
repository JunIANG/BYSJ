package com.jun.mapper;

import com.jun.pojo.dto.query.extend.HotelRoomQueryDto;
import com.jun.pojo.dto.query.extend.HotelRoomQueryParamDto;
import com.jun.pojo.entity.HotelRoom;
import com.jun.pojo.vo.HotelRoomVO;
import com.jun.pojo.vo.ScenicTicketVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 酒店房间持久化接口
 */
@Mapper
public interface HotelRoomMapper {

    void save(HotelRoom hotelRoom);

    void update(HotelRoom hotelRoom);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<HotelRoomVO> query(HotelRoomQueryDto dto);

    Integer queryCount(HotelRoomQueryDto dto);

    List<HotelRoomVO> queryByHotelIds(@Param(value = "ids") List<Integer> ids);

    List<HotelRoomVO> queryByHotelOfVendor(HotelRoomQueryParamDto paramDto);

    Integer queryCountByHotelOfVendor(HotelRoomQueryParamDto paramDto);


}
