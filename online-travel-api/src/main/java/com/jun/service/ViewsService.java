package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.vo.ChartVO;

import java.util.List;

public interface ViewsService {

    Result<List<ChartVO>> staticControls();

}
