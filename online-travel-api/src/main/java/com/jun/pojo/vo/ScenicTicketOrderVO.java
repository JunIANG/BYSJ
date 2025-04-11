package com.jun.pojo.vo;

import com.jun.pojo.entity.ScenicTicketOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

/**
 * 景点订票信息的出参VO类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicTicketOrderVO extends ScenicTicketOrder {

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 景点ID
     */
    private Integer scenicId;

    /**
     * 景点名称
     */
    private String scenicName;

    /**
     * 门票介绍
     */
    private String detail;

    /**
     * 门票数量
     */
    private Integer number;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 折扣
     */
    private Double discount;

}
