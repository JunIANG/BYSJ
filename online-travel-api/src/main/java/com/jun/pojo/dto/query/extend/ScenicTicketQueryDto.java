package com.jun.pojo.dto.query.extend;


import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 景点门票查询Dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicTicketQueryDto extends QueryDto {

    /**
     * 景点ID
     */
    private Integer scenicId;

    /**
     * 门票介绍
     */
    private String detail;

    /**
     * 门票状态
     */
    private Boolean useStatus;
}
