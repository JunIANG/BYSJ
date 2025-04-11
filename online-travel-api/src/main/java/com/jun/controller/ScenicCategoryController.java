package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicCategoryQueryDto;
import com.jun.pojo.entity.ScenicCategory;
import com.jun.service.ScenicCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 景点分类的控制器
 */
@RestController
@RequestMapping(value = "/scenicCategory")
public class ScenicCategoryController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private ScenicCategoryService scenicCategoryService;

    /**
     * 景点分类新增
     *
     * @param scenicCategory 景点分类实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody ScenicCategory scenicCategory) {
        return scenicCategoryService.save(scenicCategory);
    }

    /**
     * 景点分类修改
     *
     * @param scenicCategory 景点分类实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody ScenicCategory scenicCategory) {
        return scenicCategoryService.update(scenicCategory);
    }

    /**
     * 景点分类批量删除
     *
     * @param ids 景点分类ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return scenicCategoryService.batchDelete(ids);
    }

    /**
     * 景点分类查询
     *
     * @param dto 查询实体
     * @return Result<List < ScenicCategory>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<ScenicCategory>> query(@RequestBody ScenicCategoryQueryDto dto) {
        return scenicCategoryService.query(dto);
    }

}
