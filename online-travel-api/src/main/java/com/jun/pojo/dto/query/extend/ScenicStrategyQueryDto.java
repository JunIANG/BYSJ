package com.jun.pojo.dto.query.extend;

import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 景点攻略查询dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicStrategyQueryDto extends QueryDto {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 景点ID
     */
    private Integer scenicId;

    /**
     * 景点攻略标题
     */
    private String title;

    /**
     * 是否已经审核
     */
    private Boolean isAudit;

}
