package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.context.LocalThreadHolder;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicRatingQueryDto;
import com.jun.pojo.entity.ScenicRating;
import com.jun.pojo.vo.ScenicRatingVO;
import com.jun.service.ScenicRatingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 景点评分的控制器
 */
@RestController
@RequestMapping(value = "/scenicRating")
public class ScenicRatingController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private ScenicRatingService scenicRatingService;

    /**
     * 景点评分新增
     *
     * @param scenicRating 景点评分实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody ScenicRating scenicRating) {
        return scenicRatingService.save(scenicRating);
    }

    /**
     * 景点评分查询
     *
     * @param dto 查询实体
     * @return Result<List < ScenicRatingVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<ScenicRatingVO>> query(@RequestBody ScenicRatingQueryDto dto) {
        return scenicRatingService.query(dto);
    }

    /**
     * 查询用户对于景点的评分
     *
     * @return Result<List < ScenicRatingVO>>
     */
    @Pager
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result<List<ScenicRatingVO>> queryUser(@RequestBody ScenicRatingQueryDto dto) {
        dto.setUserId(LocalThreadHolder.getUserId());
        return scenicRatingService.query(dto);
    }

}
