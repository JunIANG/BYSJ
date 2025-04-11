package com.jun.mapper;

import com.jun.pojo.dto.query.extend.ScenicCategoryQueryDto;
import com.jun.pojo.entity.ScenicCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 景点分类持久化接口
 */
@Mapper
public interface ScenicCategoryMapper {

    void save(ScenicCategory scenicCategory);

    void update(ScenicCategory scenicCategory);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<ScenicCategory> query(ScenicCategoryQueryDto dto);

    Integer queryCount(ScenicCategoryQueryDto dto);
}
