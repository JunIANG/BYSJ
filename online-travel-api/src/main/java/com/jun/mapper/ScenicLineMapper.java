package com.jun.mapper;

import com.jun.pojo.dto.query.extend.ScenicLineQueryDto;
import com.jun.pojo.dto.query.extend.ScenicQueryDto;
import com.jun.pojo.entity.Scenic;
import com.jun.pojo.entity.ScenicLine;
import com.jun.pojo.vo.ScenicLineVO;
import com.jun.pojo.vo.ScenicVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 景点路线持久化接口
 */
@Mapper
public interface ScenicLineMapper {

    void save(ScenicLine scenicLine);

    void update(ScenicLine scenicLine);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<ScenicLineVO> query(ScenicLineQueryDto dto);

    Integer queryCount(ScenicLineQueryDto dto);
}
