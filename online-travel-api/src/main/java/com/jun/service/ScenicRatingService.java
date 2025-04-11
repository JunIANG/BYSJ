package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicLineQueryDto;
import com.jun.pojo.dto.query.extend.ScenicRatingQueryDto;
import com.jun.pojo.entity.ScenicLine;
import com.jun.pojo.entity.ScenicRating;
import com.jun.pojo.vo.ScenicLineVO;
import com.jun.pojo.vo.ScenicRatingVO;

import java.util.List;

public interface ScenicRatingService {

    Result<Void> save(ScenicRating scenicRating);

    Result<List<ScenicRatingVO>> query(ScenicRatingQueryDto dto);

}
