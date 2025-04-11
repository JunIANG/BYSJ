package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.NoticeQueryDto;
import com.jun.pojo.entity.Notice;
import com.jun.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告的控制器
 */
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private NoticeService noticeService;

    /**
     * 公告新增
     *
     * @param notice 公告实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody Notice notice) {
        return noticeService.save(notice);
    }

    /**
     * 公告修改
     *
     * @param notice 公告实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Notice notice) {
        return noticeService.update(notice);
    }

    /**
     * 公告批量删除
     *
     * @param ids 公告ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return noticeService.batchDelete(ids);
    }

    /**
     * 公告查询
     *
     * @param dto 查询实体
     * @return Result<List < Notice>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<Notice>> query(@RequestBody NoticeQueryDto dto) {
        return noticeService.query(dto);
    }

}
