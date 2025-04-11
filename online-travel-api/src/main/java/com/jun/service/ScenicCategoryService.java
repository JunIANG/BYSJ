package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicCategoryQueryDto;
import com.jun.pojo.entity.ScenicCategory;

import java.util.List;

public interface ScenicCategoryService {

    Result<Void> save(ScenicCategory scenicCategory);

    Result<Void> update(ScenicCategory scenicCategory);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<ScenicCategory>> query(ScenicCategoryQueryDto dto);

}
