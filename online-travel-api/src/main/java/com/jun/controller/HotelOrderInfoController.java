package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.context.LocalThreadHolder;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import com.jun.pojo.entity.HotelOrderInfo;
import com.jun.pojo.vo.ChartVO;
import com.jun.pojo.vo.HotelOrderInfoVO;
import com.jun.service.HotelOrderInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店订单的控制器
 */
@RestController
@RequestMapping(value = "/hotelOrderInfo")
public class HotelOrderInfoController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private HotelOrderInfoService hotelOrderInfoService;

    /**
     * 酒店订单新增
     *
     * @param hotelOrderInfo 酒店订单实体
     * @return Result<Void>
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody HotelOrderInfo hotelOrderInfo) {
        return hotelOrderInfoService.save(hotelOrderInfo);
    }

    /**
     * 酒店订单修改
     *
     * @param hotelOrderInfo 酒店订单实体
     * @return Result<Void>
     */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody HotelOrderInfo hotelOrderInfo) {
        return hotelOrderInfoService.update(hotelOrderInfo);
    }

    /**
     * 酒店订单批量删除
     *
     * @param ids 酒店订单Id列表
     * @return Result<Void>
     */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return hotelOrderInfoService.batchDelete(ids);
    }

    /**
     * 查询酒店订单
     *
     * @param dto 查询实体
     * @return Result<List < HotelOrderInfoVO>>
     */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<HotelOrderInfoVO>> query(@RequestBody HotelOrderInfoQueryDto dto) {
        return hotelOrderInfoService.query(dto);
    }

    /**
     * 查询供应商名下的酒店订单
     *
     * @return Result<HotelOrderInfoVO>
     */
    @Pager
    @PostMapping(value = "/queryHotelOrderVendor")
    @ResponseBody
    public Result<List<HotelOrderInfoVO>> queryHotelOrderVendor(@RequestBody HotelOrderInfoQueryDto dto) {
        return hotelOrderInfoService.queryHotelOrderVendor(dto);
    }

    /**
     * 统计近期成交订单的金额
     *
     * @param day 往前查的天数
     * @return Result<List < ChartVO>> 响应结果
     */
    @GetMapping(value = "/daysQuery/{day}")
    @ResponseBody
    public Result<List<ChartVO>> daysQuery(@PathVariable Integer day) {
        return hotelOrderInfoService.daysQuery(day);
    }

    /**
     * 查询用户的酒店订单
     *
     * @return Result<Void>
     */
    @Pager
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result<List<HotelOrderInfoVO>> queryUser(@RequestBody HotelOrderInfoQueryDto dto) {
        // 设置上用户ID
        dto.setUserId(LocalThreadHolder.getUserId());
        return hotelOrderInfoService.query(dto);
    }

    /**
     * 酒店订单支付
     *
     * @return Result<Void>
     */
    @Pager
    @PostMapping(value = "/pay")
    @ResponseBody
    public Result<Void> pay(@RequestBody HotelOrderInfo hotelOrderInfo) {
        return hotelOrderInfoService.pay(hotelOrderInfo);
    }

    /**
     * 统计全站指定日期里面的成交门票金额
     *
     * @return Result<List < ChartVO>> 响应结果
     */
    @GetMapping(value = "/daysQueryMoney/{day}")
    @ResponseBody
    public Result<List<ChartVO>> daysQueryMoney(@PathVariable Integer day) {
        return hotelOrderInfoService.daysQueryMoney(day);
    }

}
