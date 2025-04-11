package com.jun.pojo.dto.query.extend;

import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 酒店查询dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelQueryDto extends QueryDto {

    /**
     * 酒店名称
     */
    private String name;

    /**
     * 供应商ID
     */
    private Integer vendorId;

}
