package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelQueryDto;
import com.jun.pojo.entity.Hotel;
import com.jun.pojo.vo.HotelVO;

import java.util.List;

public interface HotelService {

    Result<Void> save(Hotel hotel);

    Result<Void> update(Hotel hotel);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<HotelVO>> query(HotelQueryDto dto);

    Result<List<HotelVO>> queryHotelVendor(HotelQueryDto dto);

}
