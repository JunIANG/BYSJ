package com.jun.service.impl;

import com.jun.mapper.*;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.*;
import com.jun.pojo.vo.ChartVO;
import com.jun.service.ViewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页可视化
 */
@Service
public class ViewsServiceImpl implements ViewsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private ScenicMapper scenicMapper;
    @Resource
    private HotelMapper hotelMapper;
    @Resource
    private ScenicStrategyMapper scenicStrategyMapper;
    @Resource
    private ScenicTicketOrderMapper scenicTicketOrderMapper;
    @Resource
    private HotelOrderInfoMapper hotelOrderInfoMapper;

    /**
     * 统计一些系统的基础数据
     *
     * @return Result<List < ChartVO>>
     */
    @Override
    public Result<List<ChartVO>> staticControls() {
        List<ChartVO> chartVOS = new ArrayList<>();
        // 1. 用户数
        UserQueryDto userQueryDto = new UserQueryDto();
        int userCount = userMapper.queryCount(userQueryDto);
        change(userCount, "存量用户", chartVOS);
        // 2. 收录景点
        Integer scenicCount = scenicMapper.queryCount(new ScenicQueryDto());
        change(scenicCount, "收录景点", chartVOS);
        // 3. 入驻酒店
        Integer hotelCount = hotelMapper.queryCount(new HotelQueryDto());
        change(hotelCount, "入驻酒店", chartVOS);
        // 4. 收录攻略
        Integer strategyCount = scenicStrategyMapper.queryCount(new ScenicStrategyQueryDto());
        change(strategyCount, "收录攻略", chartVOS);
        // 4. 门票订单
        Integer scenicTicketOrderCount = scenicTicketOrderMapper.queryCount(new ScenicTicketOrderQueryDto());
        change(scenicTicketOrderCount, "门票订单", chartVOS);
        // 4. 酒店订单
        Integer hotelOrderInfoCount = hotelOrderInfoMapper.queryCount(new HotelOrderInfoQueryDto());
        change(hotelOrderInfoCount, "酒店订单", chartVOS);
        return ApiResult.success(chartVOS);
    }

    /**
     * 参数处理
     *
     * @param count    总数目
     * @param name     统计项
     * @param chartVOS 装它们的集合
     */
    private void change(Integer count, String name, List<ChartVO> chartVOS) {
        ChartVO chartVO = new ChartVO(name, count);
        chartVOS.add(chartVO);
    }


}
