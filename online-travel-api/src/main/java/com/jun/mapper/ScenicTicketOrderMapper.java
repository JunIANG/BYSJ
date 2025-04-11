package com.jun.mapper;

import com.jun.pojo.dto.query.extend.ScenicTicketOrderQueryDto;
import com.jun.pojo.dto.query.extend.ScenicTicketQueryParamDto;
import com.jun.pojo.entity.ScenicTicketOrder;
import com.jun.pojo.vo.ScenicTicketOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 酒店订单持久化接口
 */
@Mapper
public interface ScenicTicketOrderMapper {

    void save(ScenicTicketOrder scenicTicketOrder);

    void update(ScenicTicketOrder scenicTicketOrder);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<ScenicTicketOrderVO> query(ScenicTicketOrderQueryDto dto);

    Integer queryCount(ScenicTicketOrderQueryDto dto);

    List<ScenicTicketOrderVO> queryByTicketIds(ScenicTicketQueryParamDto paramDto);

    Integer queryCountByTicketIds(ScenicTicketQueryParamDto paramDto);
}
