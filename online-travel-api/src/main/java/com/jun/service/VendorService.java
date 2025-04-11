package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.Vendor;
import com.jun.pojo.vo.VendorVO;

import java.util.List;

public interface VendorService {

    Result<Void> save(Vendor vendor);

    Result<Void> update(Vendor vendor);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<VendorVO>> query(VendorQueryDto dto);

}
