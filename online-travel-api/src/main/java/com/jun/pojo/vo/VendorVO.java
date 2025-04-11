package com.jun.pojo.vo;

import com.jun.pojo.entity.Vendor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商的VO类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VendorVO extends Vendor {

    /**
     * 用户名
     */
    private String userName;

}
