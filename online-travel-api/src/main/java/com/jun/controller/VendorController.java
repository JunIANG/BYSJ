package com.jun.controller;

import com.jun.aop.Pager;
import com.jun.context.LocalThreadHolder;
import com.jun.pojo.api.Result;
import com.jun.pojo.dto.query.extend.VendorQueryDto;
import com.jun.pojo.entity.Vendor;
import com.jun.pojo.vo.VendorVO;
import com.jun.service.VendorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 供应商的控制器
 */
@RestController
@RequestMapping(value = "/vendor")
public class VendorController {

    // 视图层（controller） ---- 业务逻辑层（service） --- 持久化层（mapper）

    @Resource
    private VendorService vendorService;

    /**
     * 供应商新增
     *
     * @param vendor 供应商实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Void> save(@RequestBody Vendor vendor) {
        return vendorService.save(vendor);
    }

    /**
     * 供应商修改
     *
     * @param vendor 供应商实体
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Vendor vendor) {
        return vendorService.update(vendor);
    }

    /**
     * 供应商批量删除
     *
     * @param ids 供应商ID列表
     * @return Result<Void>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> ids) {
        return vendorService.batchDelete(ids);
    }

    /**
     * 查询用户关联的供应商信息
     *
     * @return Result<List < Vendor>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result<List<VendorVO>> queryUser() {
        VendorQueryDto queryDto = new VendorQueryDto();
        // 取得当前操作者的用户ID，并且设置上查询参数
        queryDto.setUserId(LocalThreadHolder.getUserId());
        return vendorService.query(queryDto);
    }

    /**
     * 供应商查询
     *
     * @param vendorQueryDto 查询实体
     * @return Result<List < Vendor>>
     */
    /* @RequestBody：JSON-->实体类（可以直接用） */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<VendorVO>> query(@RequestBody VendorQueryDto vendorQueryDto) {
        return vendorService.query(vendorQueryDto);
    }

}
