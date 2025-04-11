package com.jun.mapper;

import com.jun.pojo.dto.query.extend.ScenicLineQueryDto;
import com.jun.pojo.dto.query.extend.ScenicTicketQueryDto;
import com.jun.pojo.entity.ScenicLine;
import com.jun.pojo.entity.ScenicTicket;
import com.jun.pojo.vo.ScenicLineVO;
import com.jun.pojo.vo.ScenicTicketVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 景点门票持久化接口
 */
@Mapper
public interface ScenicTicketMapper {

    void save(ScenicTicket scenicTicket);

    void update(ScenicTicket scenicTicket);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<ScenicTicketVO> query(ScenicTicketQueryDto dto);

    Integer queryCount(ScenicTicketQueryDto dto);

    List<ScenicTicketVO> queryByScenicIds(@Param(value = "ids") List<Integer> ids);
}
