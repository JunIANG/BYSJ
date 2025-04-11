package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicTicketQueryDto;
import com.jun.pojo.entity.ScenicTicket;
import com.jun.pojo.vo.ScenicTicketVO;

import java.util.List;

public interface ScenicTicketService {

    Result<Void> save(ScenicTicket scenicTicket);

    Result<Void> update(ScenicTicket scenicTicket);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<ScenicTicketVO>> query(ScenicTicketQueryDto dto);

    Result<List<ScenicTicketVO>> queryScenicTicket();
}
