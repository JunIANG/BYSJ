package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelOrderInfoQueryDto;
import com.jun.pojo.entity.HotelOrderInfo;
import com.jun.pojo.vo.ChartVO;
import com.jun.pojo.vo.HotelOrderInfoVO;

import java.util.List;

public interface HotelOrderInfoService {

    Result<Void> save(HotelOrderInfo hotelOrderInfo);

    Result<Void> update(HotelOrderInfo hotelOrderInfo);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<HotelOrderInfoVO>> query(HotelOrderInfoQueryDto dto);

    Result<List<HotelOrderInfoVO>> queryHotelOrderVendor(HotelOrderInfoQueryDto dto);

    Result<List<ChartVO>> daysQuery(Integer day);

    public Result<Void> pay(HotelOrderInfo hotelOrderInfo);

    Result<List<ChartVO>> daysQueryMoney(Integer day);
}
