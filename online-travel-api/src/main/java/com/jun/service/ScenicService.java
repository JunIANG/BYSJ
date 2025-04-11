package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.ScenicQueryDto;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.Scenic;
import com.jun.pojo.entity.Vendor;
import com.jun.pojo.vo.ScenicVO;
import com.jun.pojo.vo.SelectedVO;
import com.jun.pojo.vo.VendorVO;

import java.util.List;

public interface ScenicService {

    Result<Void> save(Scenic scenic);

    Result<Void> update(Scenic scenic);

    Result<Void> batchDelete(List<Integer> ids);

    Result<List<ScenicVO>> query(ScenicQueryDto dto);

    Result<List<ScenicVO>> queryVendorScenic(ScenicQueryDto dto);

    Result<List<SelectedVO>> querySelectedScenic();

    Result<Void> viewOperation(Integer scenicId);

    Result<Void> saveOperation(Integer scenicId);

    Result<Boolean> saveStatus(Integer scenicId);

    Result<List<ScenicVO>> querySave();

}
