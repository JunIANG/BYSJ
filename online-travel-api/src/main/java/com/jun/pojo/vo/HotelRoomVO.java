package com.jun.pojo.vo;

import com.jun.pojo.entity.Hotel;
import com.jun.pojo.entity.HotelRoom;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 酒店房间VO出参类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelRoomVO extends HotelRoom {

    /**
     * 酒店名称
     */
    private String hotelName;

}
