package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.NoticeQueryDto;
import com.jun.pojo.entity.Notice;

import java.util.List;

public interface NoticeService {

    Result<Void> save(Notice notice);

    Result<Void> update(Notice notice);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<Notice>> query(NoticeQueryDto dto);

}
