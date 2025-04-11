package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.EvaluationsQueryDto;
import com.jun.pojo.entity.Evaluations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论服务接口
 */
public interface EvaluationsService {

    Result<Object> insert(Evaluations evaluations);

    Result<Object> list(Integer contentId, String contentType);

    Result<Object> query(EvaluationsQueryDto evaluationsQueryDto);

    Result<Object> batchDelete(List<Integer> ids);

    Result<String> delete(Integer id);

    Result<Void> update(Evaluations evaluations);

}
