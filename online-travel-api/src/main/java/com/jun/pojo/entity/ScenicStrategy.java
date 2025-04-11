package com.jun.pojo.entity;

import com.alibaba.excel.converters.string.StringErrorConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 景点攻略实体
 *
 * Lombok
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicStrategy {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 景点ID
     */
    private Integer scenicId;

    /**
     * 景点攻略封面
     */
    private String cover;

    /**
     * 景点标题
     */
    private String title;

    /**
     * 景点攻略内容
     */
    private String  content;

    /**
     * 是否已经审核
     */
    private Boolean isAudit;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
