package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelRoomQueryDto;
import com.jun.pojo.entity.HotelRoom;
import com.jun.pojo.vo.HotelRoomVO;
import com.jun.service.HotelRoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店房间的控制器
 */
@RestController
@RequestMapping(value = "/hotelRoom")
public class HotelRoomController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private HotelRoomService hotelRoomService;

    /**
     * 酒店房间新增
     *
     * @param hotelRoom 酒店房间实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody HotelRoom hotelRoom) {
        return hotelRoomService.save(hotelRoom);
    }

    /**
     * 酒店房间修改
     *
     * @param hotelRoom 酒店房间实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody HotelRoom hotelRoom) {
        return hotelRoomService.update(hotelRoom);
    }

    /**
     * 酒店房间批量删除
     *
     * @param ids 酒店房间ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return hotelRoomService.batchDelete(ids);
    }

    /**
     * 酒店房间查询
     *
     * @param dto 查询实体
     * @return Result<List < HotelRoomVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<HotelRoomVO>> query(@RequestBody HotelRoomQueryDto dto) {
        return hotelRoomService.query(dto);
    }

    /**
     * 查询由当前供应商所管理的酒店房间
     *
     * @param dto 查询实体
     * @return Result<List < HotelRoomVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/queryHotelRoom")
    @ResponseBody
    public Result<List<HotelRoomVO>> queryHotelRoom(@RequestBody HotelRoomQueryDto dto) {
        return hotelRoomService.queryHotelRoom(dto);
    }

}
