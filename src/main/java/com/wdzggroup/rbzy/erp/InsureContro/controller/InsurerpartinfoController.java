package com.wdzggroup.rbzy.erp.InsureContro.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.Insurerpartinfo;
import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResponseUtils;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResultVO;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerinfoService;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerpartinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import javafx.scene.transform.Scale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */
@RestController
@Api(tags = "保险分公司信息配置")
@RequestMapping("/InsureContro/insurerpartinfo")
public class InsurerpartinfoController {
        //新增
    @Autowired
    IInsurerpartinfoService iInsurerpartinfoService;
    @ApiOperation(value = "新增分公司配置信息")

    @PostMapping
    public ResultVO<IdVO> addInsurePart(@RequestBody @Valid Insurerpartinfo insurerpartinfo){
        IdVO idVO = iInsurerpartinfoService.addInsurePartInfo(insurerpartinfo);
        return ResponseUtils.responseResult(idVO,Canst.RespMsg.SAVE_SUCCESS);
    }
    @ApiOperation(value = "更新分公司名称")
    @PutMapping
    public ResultVO updateInsurePartInfo(@RequestBody Insurerpartinfo insurerpartinfo, @RequestParam("id") String id){
        iInsurerpartinfoService.updateInsurePartInfo(insurerpartinfo,id);
        return ResponseUtils.responseResult(Canst.RespMsg.UPDATE_SUCCESS);
    }
    @ApiOperation(value = "分公司配置信息详细信息")
    @GetMapping
    public  ResultVO<Insurerpartinfo> getInsurePartInfoById(@RequestParam("id") String id){
        Insurerpartinfo insurePartInfoById = iInsurerpartinfoService.getInsurePartInfoById(id);
        return ResponseUtils.responseResult(insurePartInfoById,Canst.RespMsg.SUCCESS);
    }
    @ApiOperation(value="分页查询")
    @GetMapping("/getInsureInfoByPage")
    public ResultVO<IPage<Insurerpartinfo>> getInsurePartInfoByPage(@RequestParam(name="pageNum",required = false) Long pageNum,
                                                                    @RequestParam(name="pageSize",required = false) Long pageSize,
                                                                    @RequestParam(name="companyName",required = false) String companyName){
        IPage<Insurerpartinfo> insurePartInfoByPage = iInsurerpartinfoService.getInsurePartInfoByPage(pageNum, pageSize, companyName);
        return ResponseUtils.responseResult(insurePartInfoByPage,Canst.RespMsg.SUCCESS);
    }

}
