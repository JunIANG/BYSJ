package com.jun.pojo.dto.query.extend;


import lombok.Data;

import java.util.List;

/**
 * 供应商查询酒店房间参数入参
 */
@Data
public class HotelRoomQueryParamDto {

    /**
     * 酒店的ID列表
     */
    private List<Integer> hotelIds;

    /**
     * 查询酒店房间的参数实体
     */
    private HotelRoomQueryDto hotelRoomQueryDto;

    public HotelRoomQueryParamDto(List<Integer> roomIds, HotelRoomQueryDto hotelRoomQueryDto) {
        this.hotelIds = roomIds;
        this.hotelRoomQueryDto = hotelRoomQueryDto;
    }
}
