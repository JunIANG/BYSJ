package com.jun.mapper;

import com.jun.pojo.dto.query.extend.HotelRoomBedQueryDto;
import com.jun.pojo.entity.HotelRoomBed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 酒店持久化接口
 */
@Mapper
public interface HotelRoomBedMapper {

    void save(HotelRoomBed hotelRoomBed);

    void update(HotelRoomBed hotelRoomBed);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<HotelRoomBed> query(HotelRoomBedQueryDto dto);

    Integer queryCount(HotelRoomBedQueryDto dto);
}
