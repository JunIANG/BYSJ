package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicTicketQueryDto;
import com.jun.pojo.entity.ScenicTicket;
import com.jun.pojo.vo.ScenicTicketVO;
import com.jun.service.ScenicTicketService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 景点门票的控制器
 */
@RestController
@RequestMapping(value = "/scenicTicket")
public class ScenicTicketController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private ScenicTicketService scenicTicketService;

    /**
     * 景点门票新增
     *
     * @param scenicTicket 景点门票实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody ScenicTicket scenicTicket) {
        return scenicTicketService.save(scenicTicket);
    }

    /**
     * 景点门票修改
     *
     * @param scenicTicket 景点门票实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody ScenicTicket scenicTicket) {
        return scenicTicketService.update(scenicTicket);
    }

    /**
     * 景点门票批量删除
     *
     * @param ids 景点门票ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return scenicTicketService.batchDelete(ids);
    }

    /**
     * 查询供应商所管理的景点门票信息
     *
     * @return Result<List < ScenicTicketVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/queryScenicTicket")
    @ResponseBody
    public Result<List<ScenicTicketVO>> queryScenicTicket() {
        return scenicTicketService.queryScenicTicket();
    }

    /**
     * 景点门票查询
     *
     * @param dto 查询实体
     * @return Result<List < ScenicTicketVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<ScenicTicketVO>> query(@RequestBody ScenicTicketQueryDto dto) {
        return scenicTicketService.query(dto);
    }

}
