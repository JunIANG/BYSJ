package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelRoomBedQueryDto;
import com.jun.pojo.entity.HotelRoomBed;

import java.util.List;

public interface HotelRoomBedService {

    Result<Void> save(HotelRoomBed hotelRoomBed);

    Result<Void> update(HotelRoomBed hotelRoomBed);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<HotelRoomBed>> query(HotelRoomBedQueryDto dto);

}
