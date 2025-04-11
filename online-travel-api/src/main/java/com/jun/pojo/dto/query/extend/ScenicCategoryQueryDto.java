package com.jun.pojo.dto.query.extend;


import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 景点分类查询Dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScenicCategoryQueryDto extends QueryDto {

    /**
     * 旅游分类名
     */
    private String name;

}
