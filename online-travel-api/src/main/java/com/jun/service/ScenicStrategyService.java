package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicLineQueryDto;
import com.jun.pojo.dto.query.extend.ScenicStrategyQueryDto;
import com.jun.pojo.entity.ScenicLine;
import com.jun.pojo.entity.ScenicStrategy;
import com.jun.pojo.vo.ScenicLineVO;
import com.jun.pojo.vo.ScenicStrategyListVO;
import com.jun.pojo.vo.ScenicStrategyVO;

import java.util.List;

public interface ScenicStrategyService {

    Result<Void> save(ScenicStrategy scenicStrategy);

    Result<Void> update(ScenicStrategy scenicStrategy);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<ScenicStrategyVO>> query(ScenicStrategyQueryDto dto);

    Result<Void> audit(Integer id);

    Result<List<ScenicStrategyListVO>> queryList(ScenicStrategyQueryDto dto);
}
