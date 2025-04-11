package com.jun.pojo.dto.query.extend;

import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 酒店房间床位查询dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelRoomBedQueryDto extends QueryDto {

    /**
     * 房间ID
     */
    private Integer roomId;

    /**
     * 房间状态
     */
    private Boolean status;

}
