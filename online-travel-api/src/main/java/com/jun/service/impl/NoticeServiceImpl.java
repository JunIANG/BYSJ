package com.jun.service.impl;

import com.jun.mapper.NoticeMapper;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.NoticeQueryDto;
import com.jun.pojo.entity.Notice;
import com.jun.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告的业务逻辑实现类
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    /**
     * 公告新增
     *
     * @param notice 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(Notice notice) {
        // 设置初始时间
        notice.setCreateTime(LocalDateTime.now());
        noticeMapper.save(notice);
        return ApiResult.success();
    }

    /**
     * 公告修改
     *
     * @param notice 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(Notice notice) {
        noticeMapper.update(notice);
        return ApiResult.success();
    }

    /**
     * 公告批量删除
     *
     * @param ids 公告ID列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        noticeMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 公告查询
     *
     * @param dto 查询实体
     * @return Result<List < Notice>>
     */
    @Override
    public Result<List<Notice>> query(NoticeQueryDto dto) {
        Integer totalCount = noticeMapper.queryCount(dto);
        List<Notice> result = noticeMapper.query(dto);
        return ApiResult.success(result, totalCount);
    }
}
