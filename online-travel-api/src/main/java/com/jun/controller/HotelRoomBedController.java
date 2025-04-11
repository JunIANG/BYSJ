package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelRoomBedQueryDto;
import com.jun.pojo.entity.HotelRoomBed;
import com.jun.service.HotelRoomBedService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店房间床位的控制器
 */
@RestController
@RequestMapping(value = "/hotelRoomBed")
public class HotelRoomBedController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private HotelRoomBedService hotelRoomBedService;

    /**
     * 酒店房间床位新增
     *
     * @param hotelRoomBed 酒店房间床位实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody HotelRoomBed hotelRoomBed) {
        return hotelRoomBedService.save(hotelRoomBed);
    }

    /**
     * 酒店房间床位修改
     *
     * @param hotelRoomBed 酒店房间床位实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody HotelRoomBed hotelRoomBed) {
        return hotelRoomBedService.update(hotelRoomBed);
    }

    /**
     * 酒店房间床位批量删除
     *
     * @param ids 酒店房间床位ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return hotelRoomBedService.batchDelete(ids);
    }

    /**
     * 酒店房间床位查询
     *
     * @param dto 查询实体
     * @return Result<List < HotelRoomBed>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<HotelRoomBed>> query(@RequestBody HotelRoomBedQueryDto dto) {
        return hotelRoomBedService.query(dto);
    }

}
