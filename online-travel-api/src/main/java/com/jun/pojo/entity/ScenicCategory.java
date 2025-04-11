package com.jun.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 景点分类实体
 *
 * Lombok
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicCategory {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 景点分类名
     */
    private String name;
}
