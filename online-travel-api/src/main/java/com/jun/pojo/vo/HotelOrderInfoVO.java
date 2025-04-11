package com.jun.pojo.vo;

import com.jun.pojo.entity.HotelOrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 酒店订单的VO出参类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelOrderInfoVO extends HotelOrderInfo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 酒店名
     */
    private String hotelName;

    /**
     * 房间名
     */
    private String roomName;

}
