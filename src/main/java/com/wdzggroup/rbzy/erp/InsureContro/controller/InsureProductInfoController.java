package com.wdzggroup.rbzy.erp.InsureContro.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsureProductInfo;
import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResponseUtils;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResultVO;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsureProductInfoService;
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
 * @since 2021-01-13
 */
@RestController
@Api(tags = "保险公司产品信息配置")
@RequestMapping("/insureProductInfo")
public class InsureProductInfoController {
    @Autowired
     IInsureProductInfoService iInsureProductInfoService;
         @ApiOperation(value="新增产品信息")
        @PostMapping
        public ResultVO<IdVO> addProductInfo(@RequestBody InsureProductInfo insureProductInfo){
            IdVO idVO = iInsureProductInfoService.addProductInfo(insureProductInfo);
            return ResponseUtils.responseResult(idVO, Canst.Common.SUCCESSFUL);
        }
        @ApiOperation(value = "删除产品信息")
    @DeleteMapping
    public void deleteProductInfo(@RequestParam String id){
             iInsureProductInfoService.deleteProductInfo(id);
        }
    @ApiOperation(value = "修改产品信息")
    @GetMapping
    public void updateProductInfo(@RequestParam String id,@RequestBody InsureProductInfo insureProductInfo){
        iInsureProductInfoService.updateProductInfo(id,insureProductInfo);

    }

    @ApiOperation(value = "分页查询产品信息")
    @GetMapping(value = "/selectProductByPage")
    public ResultVO<IPage<InsureProductInfo>> selectProductByPage(@RequestParam(name = "pageNum" ,required = false) Long pageNum,
                                                                  @RequestParam(name = "pageSize" ,required = false)Long pageSize,
                                                                  @RequestParam(name = "proid" ,required = false)String proid,
                                                                  @RequestParam(name = "insureid" ,required = false)String insureid,
                                                                  @RequestParam(name = "partInsureid" ,required = false)String partInsureid,
                                                                  @RequestParam(name = "protype" ,required = false)Integer protype,
                                                                  @RequestParam(name = "insuretype" ,required = false)Integer insuretype,
                                                                  @RequestParam(name = "begintime" ,required = false)String begintime,
                                                                  @RequestParam(name = "endtime" ,required = false)String endtime){
        IPage<InsureProductInfo> insureProductInfoIPage = iInsureProductInfoService.selectProductByPage(pageNum, pageSize, proid, insureid, partInsureid, protype, insuretype, begintime, endtime);
            return  ResponseUtils.responseResult(insureProductInfoIPage, Canst.Common.SUCCESSFUL);
    }
}
