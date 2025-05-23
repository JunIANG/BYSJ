package com.jun.pojo.dto.query.extend;

import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 酒店订单查询dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelOrderInfoQueryDto extends QueryDto {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 房间ID
     */
    private Integer roomId;

    /**
     * 支付状态
     */
    private Boolean payStatus;

}
