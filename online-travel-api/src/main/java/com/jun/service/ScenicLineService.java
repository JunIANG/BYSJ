package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicLineQueryDto;
import com.jun.pojo.dto.query.extend.ScenicQueryDto;
import com.jun.pojo.entity.Scenic;
import com.jun.pojo.entity.ScenicLine;
import com.jun.pojo.vo.ScenicLineVO;
import com.jun.pojo.vo.ScenicVO;

import java.util.List;

public interface ScenicLineService {

    Result<Void> save(ScenicLine scenicLine);

    Result<Void> update(ScenicLine scenicLine);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<ScenicLineVO>> query(ScenicLineQueryDto dto);

}
