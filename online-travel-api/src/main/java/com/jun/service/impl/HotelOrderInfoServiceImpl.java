package com.jun.service.impl;

import com.jun.context.LocalThreadHolder;
import com.jun.mapper.HotelMapper;
import com.jun.mapper.HotelOrderInfoMapper;
import com.jun.mapper.HotelRoomMapper;
import com.jun.mapper.VendorMapper;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.base.QueryDto;
import com.jun.pojo.dto.query.extend.*;
import com.jun.pojo.entity.HotelOrderInfo;
import com.jun.pojo.vo.*;
import com.jun.service.HotelOrderInfoService;
import com.jun.utils.DateUtil;
import com.jun.utils.DecimalUtils;
import com.jun.utils.MoneyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 酒店订单的业务逻辑实现类
 */
@Service
public class HotelOrderInfoServiceImpl implements HotelOrderInfoService {

    @Resource
    private HotelOrderInfoMapper hotelOrderInfoMapper;
    @Resource
    private HotelRoomMapper hotelRoomMapper;
    @Resource
    private VendorMapper vendorMapper;
    @Resource
    private HotelMapper hotelMapper;

    /**
     * 酒店订单新增
     *
     * @param hotelOrderInfo 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(HotelOrderInfo hotelOrderInfo) {
        HotelRoomQueryDto hotelRoomQueryDto = new HotelRoomQueryDto();
        hotelRoomQueryDto.setId(hotelOrderInfo.getRoomId());
        List<HotelRoomVO> hotelRoomVOS = hotelRoomMapper.query(hotelRoomQueryDto);
        if (hotelRoomVOS.isEmpty()) {
            return ApiResult.error("房间信息未找到");
        }
        HotelRoomVO hotelRoomVO = hotelRoomVOS.get(0);
        Double discount = hotelRoomVO.getDiscount();
        // 没有设计买多少晚，这里使用1就行
        BigDecimal amount = DecimalUtils.calculateTotal(
                1,
                hotelRoomVO.getPrice(),
                discount == null ? 1 : (discount / 10)
        );
        hotelOrderInfo.setAmount(amount);
        // 设置用户ID
        hotelOrderInfo.setUserId(LocalThreadHolder.getUserId());
        // 设置初始时间
        hotelOrderInfo.setCreateTime(LocalDateTime.now());
        // 开始的时候，订单的状态是未支付的
        hotelOrderInfo.setPayStatus(false);
        hotelOrderInfoMapper.save(hotelOrderInfo);
        return ApiResult.success();
    }

    /**
     * 酒店订单修改
     *
     * @param hotelOrderInfo 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(HotelOrderInfo hotelOrderInfo) {
        hotelOrderInfoMapper.update(hotelOrderInfo);
        return ApiResult.success();
    }

    /**
     * 酒店订单删除
     *
     * @param ids 酒店订单ID列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        hotelOrderInfoMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 酒店订单查询
     *
     * @param dto 查询实体
     * @return Result<HotelOrderInfoVO>
     */
    @Override
    public Result<List<HotelOrderInfoVO>> query(HotelOrderInfoQueryDto dto) {
        Integer totalCount = hotelOrderInfoMapper.queryCount(dto);
        List<HotelOrderInfoVO> result = hotelOrderInfoMapper.query(dto);
        return ApiResult.success(result, totalCount);
    }

    /**
     * 查询供应商名下的酒店订单
     *
     * @return Result<HotelOrderInfoVO>
     */
    @Override
    public Result<List<HotelOrderInfoVO>> queryHotelOrderVendor(HotelOrderInfoQueryDto dto) {
        // 链路： 用户ID  --- 供应商  --- 名下管理的酒店 --- 房间 --- 订单（用户下单）
        // 当前操作者的用户ID
        Integer userId = LocalThreadHolder.getUserId();
        VendorQueryDto vendorQueryDto = new VendorQueryDto();
        vendorQueryDto.setUserId(userId);
        // 名下管理的供应商信息
        List<VendorVO> vendorVOS = vendorMapper.query(vendorQueryDto);
        // 这是没有的判断，反之，过了if，一定存在并且只有一项
        if (vendorVOS.isEmpty()) {
            return ApiResult.error("供应商信息异常");
        }
        VendorVO vendorVO = vendorVOS.get(0);
        // 只有当供应商已经审核并且状态是正常的，才有资格查
        if (!vendorVO.getStatus() && !vendorVO.getIsAudit()) {
            return ApiResult.error("供应商状态异常或无查看权限");
        }
        // 查询名下的酒店信息
        HotelQueryDto hotelQueryDto = new HotelQueryDto();
        hotelQueryDto.setVendorId(vendorVO.getId());
        List<HotelVO> hotelVOS = hotelMapper.query(hotelQueryDto);
        if (hotelVOS.isEmpty()) {
            return ApiResult.error("名下无管理酒店");
        }
        // 过滤取得酒店的Id集合列表
        List<Integer> hotelIds = hotelVOS.stream()
                .map(HotelVO::getId)
                .collect(Collectors.toList());
        // 通过酒店的的这一批ID，去获取他们名下的房间信息
        List<HotelRoomVO> hotelRoomVOS = hotelRoomMapper.queryByHotelIds(hotelIds);
        // 过滤获取房间的ID集合列表
        List<Integer> hotelRoomIds = hotelRoomVOS.stream()
                .map(HotelRoomVO::getId)
                .collect(Collectors.toList());
        // 构造查询条件
        HotelOrderInfoQueryParamDto paramDto = new HotelOrderInfoQueryParamDto(hotelRoomIds, dto);
        // 查询指定房间下的所有符合条件订单
        List<HotelOrderInfoVO> orderInfoVOList = hotelOrderInfoMapper.queryByHotelRoomIds(paramDto);
        Integer totalCount = hotelOrderInfoMapper.queryCountByHotelRoomIds(paramDto);
        return ApiResult.success(orderInfoVOList, totalCount);
    }

    /**
     * 统计近期成交的金额
     *
     * @param day 往前面查的天数
     * @return Result<List < ChartVO>>
     */
    @Override
    public Result<List<ChartVO>> daysQuery(Integer day) {
        // 获取时间范围
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        HotelOrderInfoQueryDto dto = new HotelOrderInfoQueryDto();
        dto.setStartTime(queryDto.getStartTime());
        dto.setEndTime(queryDto.getEndTime());
        Result<List<HotelOrderInfoVO>> hotelOrderVendor = queryHotelOrderVendor(dto);
        ApiResult<List<HotelOrderInfoVO>> response = (ApiResult<List<HotelOrderInfoVO>>) hotelOrderVendor;
        List<HotelOrderInfoVO> data = response.getData();
        List<MoneyDto> moneyDtoList = data.stream().map(hotelOrderInfoVO -> new MoneyDto(
                hotelOrderInfoVO.getAmount(),
                hotelOrderInfoVO.getPayTime()
        )).collect(Collectors.toList());
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);
    }

    /**
     * 酒店订单支付
     *
     * @param hotelOrderInfo 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> pay(HotelOrderInfo hotelOrderInfo) {
        hotelOrderInfo.setPayStatus(true);
        hotelOrderInfo.setPayTime(LocalDateTime.now());
        hotelOrderInfoMapper.update(hotelOrderInfo);
        return ApiResult.success();
    }

    /**
     * 统计全站指定日期里面的成交门票金额
     *
     * @return Result<List < ChartVO>> 响应结果
     */
    @Override
    public Result<List<ChartVO>> daysQueryMoney(Integer day) {
        // 获取时间范围
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        HotelOrderInfoQueryDto dto = new HotelOrderInfoQueryDto();
        dto.setStartTime(queryDto.getStartTime());
        dto.setEndTime(queryDto.getEndTime());
        List<HotelOrderInfoVO> orderInfoVOS = hotelOrderInfoMapper.query(dto);
        List<MoneyDto> moneyDtoList = orderInfoVOS.stream().map(hotelOrderInfoVO -> new MoneyDto(
                hotelOrderInfoVO.getAmount(),
                hotelOrderInfoVO.getPayTime()
        )).collect(Collectors.toList());
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);
    }

}
