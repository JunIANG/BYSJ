package com.jun.mapper;

import com.jun.pojo.dto.query.extend.ScenicQueryDto;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.Scenic;
import com.jun.pojo.entity.Vendor;
import com.jun.pojo.vo.ScenicVO;
import com.jun.pojo.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 景点持久化接口
 */
@Mapper
public interface ScenicMapper {

    void save(Scenic scenic);

    void update(Scenic scenic);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<ScenicVO> query(ScenicQueryDto dto);

    Integer queryCount(ScenicQueryDto dto);

    void updateSaveIds(Scenic scenic);
}
