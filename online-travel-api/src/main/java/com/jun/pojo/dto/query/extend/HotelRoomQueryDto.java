package com.jun.pojo.dto.query.extend;

import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 酒店房间查询dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelRoomQueryDto extends QueryDto {

    /**
     * 酒店房间名称
     */
    private String name;

    /**
     * 酒店ID
     */
    private Integer hotelId;

}
