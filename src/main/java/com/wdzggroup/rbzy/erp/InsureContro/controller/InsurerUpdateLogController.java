package com.wdzggroup.rbzy.erp.InsureContro.controller;


import com.wdzggroup.rbzy.erp.InsureContro.bean.InsurerUpdateLog;
import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResponseUtils;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResultVO;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerUpdateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */

@RestController
@Api(tags = "修改记录")
@RequestMapping("/InsureContro/insurer-update-log")
public class InsurerUpdateLogController {
    @Autowired
    IInsurerUpdateLogService iInsurerUpdateLogService;
    @ApiOperation(value = "修改记录")
    @GetMapping
    public ResultVO<InsurerUpdateLog> getUpdateLogById(@RequestParam("id") String id){
        InsurerUpdateLog updateLogById = iInsurerUpdateLogService.getUpdateLogById(id);
        return  ResponseUtils.responseResult(updateLogById, Canst.Common.SUCCESSFUL);
    }

}
