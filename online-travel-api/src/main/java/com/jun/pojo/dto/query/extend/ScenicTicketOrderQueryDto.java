package com.jun.pojo.dto.query.extend;


import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 景点门票订单查询Dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicTicketOrderQueryDto extends QueryDto {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 景点ID
     */
    private Integer scenicId;

    /**
     * 门票ID
     */
    private Integer ticketId;

    /**
     * 支付状态
     */
    private Boolean payStatus;
}
