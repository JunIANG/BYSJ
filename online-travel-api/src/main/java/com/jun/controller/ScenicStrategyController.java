package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.aop.Protector;
import com.jun.context.LocalThreadHolder;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicLineQueryDto;
import com.jun.pojo.dto.query.extend.ScenicStrategyQueryDto;
import com.jun.pojo.entity.ScenicLine;
import com.jun.pojo.entity.ScenicStrategy;
import com.jun.pojo.vo.ScenicLineVO;
import com.jun.pojo.vo.ScenicStrategyListVO;
import com.jun.pojo.vo.ScenicStrategyVO;
import com.jun.service.ScenicLineService;
import com.jun.service.ScenicStrategyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 景点攻略的控制器
 */
@RestController
@RequestMapping(value = "/scenicStrategy")
public class ScenicStrategyController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private ScenicStrategyService scenicStrategyService;

    /**
     * 景点攻略新增
     *
     * @param scenicStrategy 景点攻略实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody ScenicStrategy scenicStrategy) {
        return scenicStrategyService.save(scenicStrategy);
    }

    /**
     * 景点攻略审核
     *
     * @param id 景点攻略ID
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Protector(role = "管理员")
    @PostMapping(value = "/audit/{id}")
    @ResponseBody
    public Result<Void> audit(@PathVariable Integer id) {
        return scenicStrategyService.audit(id);
    }

    /**
     * 景点攻略修改
     *
     * @param scenicStrategy 景点攻略实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody ScenicStrategy scenicStrategy) {
        return scenicStrategyService.update(scenicStrategy);
    }

    /**
     * 景点攻略批量删除
     *
     * @param ids 景点攻略ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return scenicStrategyService.batchDelete(ids);
    }

    /**
     * 景点攻略查询
     *
     * @param dto 查询实体
     * @return Result<List < ScenicStrategyVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<ScenicStrategyVO>> query(@RequestBody ScenicStrategyQueryDto dto) {
        return scenicStrategyService.query(dto);
    }

    /**
     * 查询用户发表的景点攻略
     *
     * @return Result<Void>
     */
    @Pager
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result<List<ScenicStrategyVO>> queryUser(@RequestBody ScenicStrategyQueryDto dto) {
        // 设置上用户ID，数据隔离
        dto.setUserId(LocalThreadHolder.getUserId());
        return scenicStrategyService.query(dto);
    }

    /**
     * 查询攻略列表
     *
     * @return Result<List < ScenicStrategyListVO>>
     */
    @Pager
    @PostMapping(value = "/queryList")
    @ResponseBody
    public Result<List<ScenicStrategyListVO>> queryList(@RequestBody ScenicStrategyQueryDto dto) {
        return scenicStrategyService.queryList(dto);
    }

}
