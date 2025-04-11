package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicQueryDto;
import com.jun.pojo.entity.Scenic;
import com.jun.pojo.vo.ScenicVO;
import com.jun.pojo.vo.SelectedVO;
import com.jun.service.ScenicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 景点的控制器
 */
@RestController
@RequestMapping(value = "/scenic")
public class ScenicController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private ScenicService scenicService;

    /**
     * 景点新增
     *
     * @param scenic 景点实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody Scenic scenic) {
        return scenicService.save(scenic);
    }

    /**
     * 景点修改
     *
     * @param scenic 景点实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Scenic scenic) {
        return scenicService.update(scenic);
    }

    /**
     * 景点批量删除
     *
     * @param ids 景点ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return scenicService.batchDelete(ids);
    }

    /**
     * 景点查询
     *
     * @param dto 查询实体
     * @return Result<List < ScenicVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<ScenicVO>> query(@RequestBody ScenicQueryDto dto) {
        return scenicService.query(dto);
    }

    /**
     * 查询与当前供应商相关的景点
     *
     * @param dto 查询实体
     * @return Result<List < ScenicVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/queryVendorScenic")
    @ResponseBody
    public Result<List<ScenicVO>> queryVendorScenic(@RequestBody ScenicQueryDto dto) {
        return scenicService.queryVendorScenic(dto);
    }

    /**
     * 关联景点下拉选择器
     *
     * @return Result<List < SelectedVO>>
     */
    @GetMapping(value = "/querySelectedScenic")
    @ResponseBody
    public Result<List<SelectedVO>> querySelectedScenic() {
        return scenicService.querySelectedScenic();
    }

    /**
     * 浏览操作
     *
     * @return Result<Void>
     */
    @Pager
    @PostMapping(value = "/viewOperation/{scenicId}")
    @ResponseBody
    public Result<Void> viewOperation(@PathVariable Integer scenicId) {
        return scenicService.viewOperation(scenicId);
    }

    /**
     * 收藏操作
     *
     * @return Result<Void>
     */
    @Pager
    @PostMapping(value = "/saveOperation/{scenicId}")
    @ResponseBody
    public Result<Void> saveOperation(@PathVariable Integer scenicId) {
        return scenicService.saveOperation(scenicId);
    }

    /**
     * 查询用户的收藏景点状况
     *
     * @return Result<Void>
     */
    @Pager
    @PostMapping(value = "/saveStatus/{scenicId}")
    @ResponseBody
    public Result<Boolean> saveStatus(@PathVariable Integer scenicId) {
        return scenicService.saveStatus(scenicId);
    }

    /**
     * 查询用户收藏的景点信息
     *
     * @return List<ScenicVO>
     */
    @Pager
    @GetMapping(value = "/querySave")
    @ResponseBody
    public Result<List<ScenicVO>> querySave() {
        return scenicService.querySave();
    }

}
