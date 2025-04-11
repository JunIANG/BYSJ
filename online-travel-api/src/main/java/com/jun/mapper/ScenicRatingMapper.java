package com.jun.mapper;

import com.jun.pojo.dto.query.extend.ScenicLineQueryDto;
import com.jun.pojo.dto.query.extend.ScenicRatingQueryDto;
import com.jun.pojo.entity.ScenicLine;
import com.jun.pojo.entity.ScenicRating;
import com.jun.pojo.vo.ScenicLineVO;
import com.jun.pojo.vo.ScenicRatingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 景点评分持久化接口
 */
@Mapper
public interface ScenicRatingMapper {

    void save(ScenicRating scenicRating);

    List<ScenicRatingVO> query(ScenicRatingQueryDto dto);

    Integer queryCount(ScenicRatingQueryDto dto);
}
