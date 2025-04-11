package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicLineQueryDto;
import com.jun.pojo.entity.ScenicLine;
import com.jun.pojo.vo.ScenicLineVO;
import com.jun.service.ScenicLineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 景点路线的控制器
 */
@RestController
@RequestMapping(value = "/scenicLine")
public class ScenicLineController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private ScenicLineService scenicLineService;

    /**
     * 景点路线新增
     *
     * @param scenicLine 景点路线实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody ScenicLine scenicLine) {
        return scenicLineService.save(scenicLine);
    }

    /**
     * 景点路线修改
     *
     * @param scenicLine 景点路线实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody ScenicLine scenicLine) {
        return scenicLineService.update(scenicLine);
    }

    /**
     * 景点路线批量删除
     *
     * @param ids 景点路线ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return scenicLineService.batchDelete(ids);
    }

    /**
     * 景点路线查询
     *
     * @param dto 查询实体
     * @return Result<List < ScenicLineVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<ScenicLineVO>> query(@RequestBody ScenicLineQueryDto dto) {
        return scenicLineService.query(dto);
    }

}
