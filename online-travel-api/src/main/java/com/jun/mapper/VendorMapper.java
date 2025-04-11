package com.jun.mapper;

import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.Vendor;
import com.jun.pojo.vo.VendorVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商持久化接口
 */
@Mapper
public interface VendorMapper {

    void save(Vendor vendor);

    void update(Vendor vendor);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<VendorVO> query(VendorQueryDto dto);

    Integer queryCount(VendorQueryDto dto);
}
