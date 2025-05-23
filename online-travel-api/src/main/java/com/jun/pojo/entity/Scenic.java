package com.jun.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 景点实体
 *
 * Lombok
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scenic {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 景点名称
     */
    private String name;

    /**
     * 景点分类ID
     */
    private Integer categoryId;

    /**
     * 封面
     */
    private String cover;

    /**
     * 景点详情
     */
    private String detail;

    /**
     * 供应商ID
     */
    private Integer vendorId;

    /**
     * 景点收藏者用户ID，以“,”进行分割
     */
    private String saveIds;

    /**
     * 景点浏览者用户ID，以“,”进行分割
     */
    private String viewIds;

    /**
     * 景点所在地
     */
    private String address;

    /**
     * 景点状态
     */
    private Boolean status;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
