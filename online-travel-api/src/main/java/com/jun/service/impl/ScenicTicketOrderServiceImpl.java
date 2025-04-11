package com.jun.service.impl;

import com.jun.context.LocalThreadHolder;
import com.jun.mapper.ScenicMapper;
import com.jun.mapper.ScenicTicketMapper;
import com.jun.mapper.ScenicTicketOrderMapper;
import com.jun.mapper.VendorMapper;
import com.jun.pojo.api.ApiResult;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.base.QueryDto;
import com.jun.pojo.dto.query.extend.*;
import com.jun.pojo.entity.ScenicTicket;
import com.jun.pojo.entity.ScenicTicketOrder;
import com.jun.pojo.vo.*;
import com.jun.service.ScenicTicketOrderService;
import com.jun.utils.DateUtil;
import com.jun.utils.DecimalUtils;
import com.jun.utils.MoneyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 景点门票订单的业务逻辑实现类
 */
@Service
public class ScenicTicketOrderServiceImpl implements ScenicTicketOrderService {

    @Resource
    private ScenicTicketOrderMapper scenicTicketOrderMapper;

    @Resource
    private VendorMapper vendorMapper;

    @Resource
    private ScenicMapper scenicMapper;

    @Resource
    private ScenicTicketMapper scenicTicketMapper;

    /**
     * 景区订单新增
     *
     * @param scenicTicketOrder 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(ScenicTicketOrder scenicTicketOrder) {
        if (!StringUtils.hasText(scenicTicketOrder.getConcatPerson()) ||
                !StringUtils.hasText(scenicTicketOrder.getConcatPhone())) {
            return ApiResult.error("联系人或联系电话不为空");
        }
        ScenicTicketQueryDto scenicTicketQueryDto = new ScenicTicketQueryDto();
        scenicTicketQueryDto.setId(scenicTicketOrder.getTicketId());
        List<ScenicTicketVO> scenicTicketVOS = scenicTicketMapper.query(scenicTicketQueryDto);
        if (scenicTicketVOS.isEmpty()) {
            return ApiResult.error("暂无门票信息");
        }
        ScenicTicketVO scenicTicketVO = scenicTicketVOS.get(0);
        // 门票是否可用
        if (!scenicTicketVO.getUseStatus()) {
            return ApiResult.error("门票暂不可用");
        }
        // 看门票是否充足
        if (scenicTicketOrder.getBuyNumber() > scenicTicketVO.getNumber()) {
            return ApiResult.error("门票库存不足");
        }
        BigDecimal amount = DecimalUtils.calculateTotal(
                scenicTicketOrder.getBuyNumber(),
                scenicTicketVO.getPrice(),
                scenicTicketVO.getDiscount() == null ? 1 : (scenicTicketVO.getDiscount() / 10)
        );
        // 设置金额
        scenicTicketOrder.setAmount(amount);
        // 将购买者的用户ID设置上
        scenicTicketOrder.setUserId(LocalThreadHolder.getUserId());
        // 设置初始时间
        scenicTicketOrder.setCreateTime(LocalDateTime.now());
        scenicTicketOrder.setTicketId(scenicTicketVO.getId());
        // 开始的时候，订单的状态是未支付的
        scenicTicketOrder.setPayStatus(false);
        scenicTicketOrderMapper.save(scenicTicketOrder);
        // 改门票数量
        ScenicTicket scenicTicket = new ScenicTicket();
        scenicTicket.setId(scenicTicketVO.getId());
        scenicTicket.setNumber(scenicTicketVO.getNumber() - scenicTicketOrder.getBuyNumber());
        scenicTicketMapper.update(scenicTicket);
        return ApiResult.success("下单成功");
    }

    /**
     * 景点门票订单修改
     *
     * @param scenicTicketOrder 实体
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(ScenicTicketOrder scenicTicketOrder) {
        scenicTicketOrderMapper.update(scenicTicketOrder);
        return ApiResult.success();
    }

    /**
     * 景点门票订单删除
     *
     * @param ids 景点门票订单ID列表
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        scenicTicketOrderMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 景点门票订单查询
     *
     * @param dto 查询实体
     * @return Result<ScenicTicketOrderVO>
     */
    @Override
    public Result<List<ScenicTicketOrderVO>> query(ScenicTicketOrderQueryDto dto) {
        Integer totalCount = scenicTicketOrderMapper.queryCount(dto);
        List<ScenicTicketOrderVO> result = scenicTicketOrderMapper.query(dto);
        return ApiResult.success(result, totalCount);
    }

    /**
     * 查询与当前供应商关联的景点门票订单
     *
     * @param dto 查询实体
     * @return Result<List < ScenicTicketOrderVO>>
     */
    @Override
    public Result<List<ScenicTicketOrderVO>> queryScenicTicketOrder(ScenicTicketOrderQueryDto dto) {
        // 用户ID --- 供应商ID --- 景点ID --- 门票ID --- 门票订单ID
        // 取出当前供应商ID
        Integer vendorId = getVendorId();
        // 查询供应商管理的全部的景点信息
        ScenicQueryDto scenicQueryDto = new ScenicQueryDto();
        scenicQueryDto.setVendorId(vendorId);
        List<ScenicVO> scenicVOS = scenicMapper.query(scenicQueryDto);
        // 取出景点ID列表
        List<Integer> scenicIds = scenicVOS.stream()
                .map(ScenicVO::getId)
                .collect(Collectors.toList());
        // 根据景点ID列表查询门票
        List<ScenicTicketVO> scenicTicketVOS = scenicTicketMapper.queryByScenicIds(scenicIds);
        // 取出门票ID列表
        List<Integer> scenicTicketIds = scenicTicketVOS.stream()
                .map(ScenicTicketVO::getId)
                .collect(Collectors.toList());
        // 构造查询对象
        ScenicTicketQueryParamDto orderQueryParam = new ScenicTicketQueryParamDto(scenicTicketIds, dto);
        // 查询门票订单
        List<ScenicTicketOrderVO> scenicTicketOrderVOS = scenicTicketOrderMapper.queryByTicketIds(orderQueryParam);
        Integer totalCount = scenicTicketOrderMapper.queryCountByTicketIds(orderQueryParam);
        return ApiResult.success(scenicTicketOrderVOS, totalCount);
    }

    /**
     * 获取当前供应商ID
     *
     * @return Integer
     */
    private Integer getVendorId() {
        VendorQueryDto vendorQueryDto = new VendorQueryDto();
        // 由用户ID查回来的供应商信息
        vendorQueryDto.setUserId(LocalThreadHolder.getUserId());
        List<VendorVO> vendorVOS = vendorMapper.query(vendorQueryDto);
        // 1.要么什么都没有 2.有的话，只有一项
        if (vendorVOS.isEmpty()) {
            //return ApiResult.error("供应商身份异常");
            return null;
        }
        return vendorVOS.get(0).getId();
    }

    /**
     * 统计近期成交订单的金额
     *
     * @param day 往前查的天数
     * @return Result<List < ChartVO>> 响应结果
     */
    @Override
    public Result<List<ChartVO>> daysQuery(Integer day) {
        // 1.获取时间范围
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        ScenicTicketOrderQueryDto dto = new ScenicTicketOrderQueryDto();
        dto.setStartTime(queryDto.getStartTime());
        dto.setEndTime(queryDto.getEndTime());
        // 2.查询数据
        Result<List<ScenicTicketOrderVO>> scenicTicketOrder = queryScenicTicketOrder(dto);
        ApiResult<List<ScenicTicketOrderVO>> response = (ApiResult<List<ScenicTicketOrderVO>>) scenicTicketOrder;
        List<ScenicTicketOrderVO> data = response.getData();
        List<MoneyDto> moneyDtoList = data.stream().map(scenicTicketOrderVO -> new MoneyDto(
                scenicTicketOrderVO.getAmount(),
                scenicTicketOrderVO.getPayTime()
        )).collect(Collectors.toList());
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);
    }

    /**
     * 门票订单支付
     *
     * @param scenicTicketOrder 门票订单数据
     * @return Result<Void>
     */
    @Override
    public Result<Void> pay(ScenicTicketOrder scenicTicketOrder) {
        // 支付状态
        scenicTicketOrder.setPayStatus(true);
        // 支付事件
        scenicTicketOrder.setPayTime(LocalDateTime.now());
        scenicTicketOrderMapper.update(scenicTicketOrder);
        return ApiResult.success();
    }

    /**
     * 查询用户自己的门票消费金额数据
     *
     * @param day 往前查多少天
     * @return Result<List < ChartVO>>
     */
    @Override
    public Result<List<ChartVO>> daysQueryUser(Integer day) {
        // 获取时间范围
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        ScenicTicketOrderQueryDto dto = new ScenicTicketOrderQueryDto();
        dto.setStartTime(queryDto.getStartTime());
        dto.setEndTime(queryDto.getEndTime());
        // 设置上用户的ID
        dto.setUserId(LocalThreadHolder.getUserId());
        List<ScenicTicketOrderVO> scenicTicketOrderVOS = scenicTicketOrderMapper.query(dto);
        List<MoneyDto> moneyDtoList = scenicTicketOrderVOS.stream()
                .map(scenicTicketOrderVO -> new MoneyDto(
                        scenicTicketOrderVO.getAmount(),
                        scenicTicketOrderVO.getPayTime())).collect(Collectors.toList());
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);
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
        ScenicTicketOrderQueryDto dto = new ScenicTicketOrderQueryDto();
        dto.setStartTime(queryDto.getStartTime());
        dto.setEndTime(queryDto.getEndTime());
        List<ScenicTicketOrderVO> scenicTicketOrderVOS = scenicTicketOrderMapper.query(dto);
        List<MoneyDto> moneyDtoList = scenicTicketOrderVOS.stream()
                .map(scenicTicketOrderVO -> new MoneyDto(
                        scenicTicketOrderVO.getAmount(),
                        scenicTicketOrderVO.getPayTime())).collect(Collectors.toList());
        List<ChartVO> chartVOS = MoneyUtils.countMoney(day, moneyDtoList);
        return ApiResult.success(chartVOS);
    }

}
