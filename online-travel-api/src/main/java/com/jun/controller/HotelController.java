package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelQueryDto;
import com.jun.pojo.entity.Hotel;
import com.jun.pojo.vo.HotelVO;
import com.jun.service.HotelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店的控制器
 */
@RestController
@RequestMapping(value = "/hotel")
public class HotelController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private HotelService hotelService;

    /**
     * 酒店新增
     *
     * @param hotel 酒店实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }

    /**
     * 酒店修改
     *
     * @param hotel 酒店实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Hotel hotel) {
        return hotelService.update(hotel);
    }

    /**
     * 酒店批量删除
     *
     * @param ids 酒店ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return hotelService.batchDelete(ids);
    }

    /**
     * 查询供应商名下维护的酒店信息
     *
     * @param dto 查询实体
     * @return Result<List < HotelVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/queryHotelVendor")
    @ResponseBody
    public Result<List<HotelVO>> queryHotelVendor(@RequestBody HotelQueryDto dto) {
        return hotelService.queryHotelVendor(dto);
    }

    /**
     * 酒店查询
     *
     * @param dto 查询实体
     * @return Result<List < HotelVO>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<HotelVO>> query(@RequestBody HotelQueryDto dto) {
        return hotelService.query(dto);
    }

}
