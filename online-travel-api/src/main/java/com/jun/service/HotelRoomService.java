package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.HotelRoomQueryDto;
import com.jun.pojo.entity.HotelRoom;
import com.jun.pojo.vo.HotelRoomVO;

import java.util.List;

public interface HotelRoomService {

    Result<Void> save(HotelRoom hotelRoom);

    Result<Void> update(HotelRoom hotelRoom);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<HotelRoomVO>> query(HotelRoomQueryDto dto);

    Result<List<HotelRoomVO>> queryHotelRoom(HotelRoomQueryDto dto);
}
