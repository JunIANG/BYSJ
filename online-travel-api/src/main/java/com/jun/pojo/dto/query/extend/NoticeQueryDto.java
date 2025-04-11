package com.jun.pojo.dto.query.extend;

import com.jun.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公告查询dto类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NoticeQueryDto extends QueryDto {

    /**
     * 公告标题
     */
    private String title;

}
