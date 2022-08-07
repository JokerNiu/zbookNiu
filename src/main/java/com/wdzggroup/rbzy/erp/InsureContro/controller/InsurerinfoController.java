package com.wdzggroup.rbzy.erp.InsureContro.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.PartCompany;
import com.wdzggroup.rbzy.erp.InsureContro.bean.Insurerinfo;
import com.wdzggroup.rbzy.erp.InsureContro.bean.Insurerpartinfo;
import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResponseUtils;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResultVO;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerinfoService;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerpartinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */
@Api(tags = "管理信息")
@RestController
@RequestMapping("/InsureContro/insurerinfo")
public class InsurerinfoController {
    @Autowired
    IInsurerinfoService iInsurerinfoService;
    @Autowired
    IInsurerpartinfoService iInsurerpartinfoService;
    @ApiOperation("新增管理配置")
    @PostMapping
    public ResultVO<IdVO> addInsureConfigInfo(@RequestBody Insurerinfo insurerinfo){
        IdVO idVO = iInsurerinfoService.addInsureConfigInfo(insurerinfo);
            return ResponseUtils.responseResult(idVO, Canst.RespMsg.SUCCESS);
    }
    @ApiOperation("编辑保险公司管理信息")
    @PutMapping
    public void updateInsureConfigInfo(String id,@RequestBody Insurerinfo insurerinfo){
        iInsurerinfoService.updateInsureConfigInfo(id,insurerinfo);

    }
    @ApiOperation("分页查询")
    @GetMapping
    public ResultVO<IPage<Insurerinfo>> getInsureConfigByPage(@RequestParam(name = "pageNum" ,required = false) Long pageNum,
                                                              @RequestParam(name = "pageSize" ,required = false)Long pageSize,
                                                              @RequestParam(name = "state" ,required = false) Integer state,
                                                              @RequestParam(name = "companyName" ,required = false)String companyName,
                                                              @RequestParam(name = "type" ,required = false)Integer type,
                                                              @RequestParam(name = "partCompanyName" ,required = false)String partCompanyName,
                                                              @RequestParam(name = "beginDate" ,required = false)Long beginDate,
                                                              @RequestParam(name = "endDate" ,required = false)Long endDate){
        IPage<Insurerinfo> insureConfigByPage = iInsurerinfoService.getInsureConfigByPage(pageNum, pageSize, state, companyName, type, partCompanyName, beginDate, endDate);
        return ResponseUtils.responseResult(insureConfigByPage,Canst.RespMsg.SUCCESS);

    }
    @ApiOperation("终止合作")
    @PutMapping("/stop")
    public  void updateInsureConfigStateByids(@RequestBody List<String> List){
        iInsurerinfoService.updateInsureConfigStateByids(List);
    }

    @ApiOperation("启动合作")
    @PutMapping("/start")
    public  void startInsureConfigStateByids(@RequestBody List<String> List){
        iInsurerinfoService.startInfoupdateInsureConfigStateByids(List);
    }
    @ApiOperation("查询分公司")
    @GetMapping("/getPartCompanyById")
    public ResultVO<List<Insurerpartinfo>> getPartCompanyById(@RequestBody String id){
        List<Insurerpartinfo> insureInfoById = iInsurerpartinfoService.getInsureInfoById(id);
        return ResponseUtils.responseResult(insureInfoById,Canst.RespMsg.SUCCESS);
    }
    @ApiOperation("上传文件")
    @PostMapping("/uplodeFile")
    public  ResultVO<String> uploadFile( @RequestBody MultipartFile file){
        String result = iInsurerinfoService.uploadInsureConfigFile(file);
        return  ResponseUtils.responseResult(result,Canst.RespMsg.SUCCESS);

    }
    @ApiOperation("导出文件")
    @PostMapping("/exportFile")
    public String getInsureInfoByName( @RequestParam(name = "state" ,required = false) Integer state,
                                       @RequestParam(name = "companyName" ,required = false)String companyName,
                                       @RequestParam(name = "type" ,required = false)Integer type,
                                       @RequestParam(name = "partCompanyName" ,required = false)String partCompanyName,
                                       @RequestParam(name = "beginDate" ,required = false)Long beginDate,
                                       @RequestParam(name = "endDate" ,required = false)Long endDate,
                                       @RequestParam(name = "sheetname" ,required = false)String  sheetname) throws IOException {
        List<Insurerinfo> insureInfoByName = iInsurerinfoService.getInsureInfoByName(state, companyName, type, partCompanyName, beginDate, endDate, sheetname);

        return  "success";
    }
}
