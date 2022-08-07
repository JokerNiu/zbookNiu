package com.wdzggroup.rbzy.erp.InsureContro.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsureInfo;
import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.common.IRespStatus;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResponseUtils;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResultVO;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsureInfoService;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerUpdateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */

@RestController
@Api(tags = "保险公司配置")
@RequestMapping("/InsureContro/insure-info")

public class InsureInfoController {
    @Autowired
    IInsureInfoService iInsureInfoService;
    @Autowired
    IInsurerUpdateLogService iInsurerUpdateLogService;
    @ApiOperation(value="保险公司配置信息详情")
    @GetMapping ("/getInsureById")
    public ResultVO<InsureInfo> getInsureById(@RequestParam (name="id") String id){
        InsureInfo insureById = iInsureInfoService.getInsureById(id);

        return ResponseUtils.responseResult(insureById,Canst.Common.SUCCESSFUL);
    }

    @ApiOperation(value="新增保险公司配置信息")
    @PostMapping

    public ResultVO<IdVO> addInsure(@RequestBody @Valid InsureInfo insureInfo){

        IdVO idVO = iInsureInfoService.addInsure(insureInfo);
        return ResponseUtils.responseResult(idVO,Canst.Common.SUCCESSFUL);
    }

    @ApiOperation(value="删除保险公司配置信息")
    @DeleteMapping

    public  void deleteInsure(@RequestParam("id") String id){
      iInsureInfoService.deleteInsure(id);
    }

    @ApiOperation(value="更新保险公司配置信息详情")
    @PutMapping

    public  void  updateInsure(@RequestParam("id") String id,@RequestBody InsureInfo insureInfo){
       iInsureInfoService.updateInsure(id, insureInfo);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageSize", value="每页多少数据"),
            @ApiImplicitParam(name="pageNum", value="分几页"),
            @ApiImplicitParam(name="companyName", value="保险公司名称")
    })
    @ApiOperation(value="分页查询")
    @GetMapping
    public ResultVO<IPage<InsureInfo>> getInsureByPage(@RequestParam(name = "pageNum",required=false) Long pageNum,@RequestParam(name="pageSize",required=false) Long pageSize,
    @RequestParam(name="companyName",required=false) String companyName){

        IPage<InsureInfo> insureByPage = iInsureInfoService.getInsureByPage(pageNum,pageSize,companyName);
        return  ResponseUtils.responseResult(insureByPage,Canst.Common.SUCCESSFUL);
    }


}
