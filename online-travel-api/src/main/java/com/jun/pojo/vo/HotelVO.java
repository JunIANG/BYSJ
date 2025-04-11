package com.jun.pojo.vo;

import com.jun.pojo.entity.Hotel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 酒店VO出参类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HotelVO extends Hotel {

    /**
     * 供应商名称
     */
    private String vendorName;

}
