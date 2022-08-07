package com.wdzggroup.rbzy.erp.InsureContro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.Insurerinfo;
import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.exception.BaseException;
import com.wdzggroup.rbzy.erp.InsureContro.exception.Err;
import com.wdzggroup.rbzy.erp.InsureContro.mapper.InsurerinfoMapper;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */
@Service
public class InsurerinfoServiceImpl extends ServiceImpl<InsurerinfoMapper, Insurerinfo> implements IInsurerinfoService {
   @Autowired
   HttpServletResponse response;
    @Override
    public IdVO addInsureConfigInfo(Insurerinfo insurerinfo) {
        LambdaQueryWrapper<Insurerinfo> LambdaQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper.eq(Insurerinfo::getInsurerName,insurerinfo.getInsurerName());
        Integer integer = this.baseMapper.selectCount(LambdaQueryWrapper);
        if(integer>0){
            throw new BaseException(Err.InsureInfo.NAME_HAS_EXIST);
        }
        this.baseMapper.insert(insurerinfo);
        IdVO idVO = new IdVO();
        idVO.setIniId(insurerinfo.getInsId());
        return idVO;
    }

    @Override
    public void updateInsureConfigInfo(String id, Insurerinfo insurerinfo) {
        Insurerinfo beforeInfo = this.baseMapper.selectById(id);
        LambdaQueryWrapper<Insurerinfo> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        objectLambdaQueryWrapper.eq(Insurerinfo::getInsurerName,insurerinfo.getInsurerName());
        Integer integer = this.baseMapper.selectCount(objectLambdaQueryWrapper);
        if(integer>0){
            throw new BaseException(Err.RESTfulRequest.DATA_PARSE_FAILED);
        }
        LambdaUpdateWrapper<Insurerinfo> objectLambdaUpdateWrapper = Wrappers.lambdaUpdate();
        objectLambdaUpdateWrapper.eq(Insurerinfo::getInsId,id);
        this.baseMapper.update(insurerinfo,objectLambdaUpdateWrapper);

    }

    @Override
    public void updateInsureConfigStateByids(List<String> ids) {
        this.updateStateByIds(ids,Canst.DataCanst.COMPANY_STATE1.getCode());
    }

    @Override
    public void startInfoupdateInsureConfigStateByids(List<String> idList) {
        this.updateStateByIds(idList,Canst.DataCanst.COMPANY_STATE.getCode());
    }

    @Override
    public void updateInsureConfigState() {
        LambdaQueryWrapper<Insurerinfo> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        objectLambdaQueryWrapper.gt(Insurerinfo::getEndDate,new Date().getTime()).notIn(Insurerinfo::getState,Canst.DataCanst.COMPANY_STATE1.getCode());
        List<Insurerinfo> insurerinfos = this.baseMapper.selectList(objectLambdaQueryWrapper);
        List<String> ids = insurerinfos.stream().map(Insurerinfo::getInsId).distinct().collect(Collectors.toList());
        this.updateStateByIds(ids,Canst.DataCanst.COMPANY_STATE1.getCode());
    }
    public void updateStateByIds(List<String> list,Integer parma){
        if(parma==2){
            LambdaUpdateWrapper<Insurerinfo> objectLambdaUpdateWrapper = Wrappers.lambdaUpdate();
            objectLambdaUpdateWrapper.in(Insurerinfo::getInsId,list).set(Insurerinfo::getState,Canst.DataCanst.COMPANY_STATE2.getCode());
            this.update(objectLambdaUpdateWrapper);
        }else if(parma==1){
            LambdaUpdateWrapper<Insurerinfo> objectLambdaUpdateWrapper = Wrappers.lambdaUpdate();
            objectLambdaUpdateWrapper.in(Insurerinfo::getInsId,list).set(Insurerinfo::getState,Canst.DataCanst.COMPANY_STATE1.getCode());
            this.update(objectLambdaUpdateWrapper);
        }
    }

    @Override
    public IPage<Insurerinfo> getInsureConfigByPage(Long pageNum, Long pageSize,
                                                    Integer state, String cid,
                                                    Integer type, String pid,
                                                    Long beginDate, Long endDate) {

        if(pageNum==null||pageSize==null){
            pageNum=1l;
            pageSize=10l;
        }
        Page<Insurerinfo> objectPage = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Insurerinfo> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        objectLambdaQueryWrapper.eq(Insurerinfo::getInsId,cid)
                .eq(Insurerinfo::getInsurerpartId,pid)
                .eq(Insurerinfo::getState,state)
                .eq(Insurerinfo::getType,type)
                .eq(Insurerinfo::getSoftDeete, Canst.DataCanst.SOFT_DELETE.getCode())
                .le(endDate!=null,Insurerinfo::getEndDate,endDate)
                .ge(beginDate!=null,Insurerinfo::getStartDate,beginDate);
        long pages = this.baseMapper.selectPage(objectPage, objectLambdaQueryWrapper).getPages();
        if(pageNum>pages){
            objectPage= new Page<>(pages,pageSize);
        }
        Page<Insurerinfo> insurerinfoPage = this.baseMapper.selectPage(objectPage, objectLambdaQueryWrapper);
        return insurerinfoPage;
    }

    @Override
    public List<Insurerinfo> getInsureInfoByName(Integer state, String cid, Integer type, String pid, Long beginDate, Long endDate,String sheetName) throws IOException {
        LambdaQueryWrapper<Insurerinfo> QueryWrapper = Wrappers.lambdaQuery();
        QueryWrapper.eq(Insurerinfo::getInsId,cid)
                .eq(Insurerinfo::getInsurerpartId,pid)
                .eq(Insurerinfo::getState,state)
                .eq(Insurerinfo::getType,type)
                .eq(Insurerinfo::getSoftDeete, Canst.DataCanst.SOFT_DELETE.getCode())
                .le(endDate!=null,Insurerinfo::getEndDate,endDate)
                .ge(beginDate!=null,Insurerinfo::getStartDate,beginDate);
        List<Insurerinfo> insurerinfos = this.baseMapper.selectList(QueryWrapper);
        this.exportInsureConfigFile(insurerinfos,sheetName);
        return insurerinfos;
    }

    @Override
    public String uploadInsureConfigFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "file is Empty";
        }
        String filename=file.getOriginalFilename();
        try{

            String  path = "D:/"+filename;
            File dest = new File(path);
            System.out.println(dest.getPath());
            file.transferTo(dest);
        }catch (IOException e){
            e.printStackTrace();
            return "上传文件异常";
        }
        return "success";
    }

    @Override
    public <T>void exportInsureConfigFile(List<T> insurerinfo, String sheetname) throws IOException {

        HSSFWorkbook wb=new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet(sheetname);
        HSSFRow row=null;
        row=sheet.createRow(0);
        row.setHeight((short)(26.25*20));
        row.createCell(0).setCellValue(sheetname);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0,0,0,0);
        //sheet.addMergedRegion(cellRangeAddress);
        row=sheet.createRow(1);
        row.setHeight((short)(22.50*20));
        for (T t:insurerinfo
        ) {
            Class<?> tClass=t.getClass();
            Field[] fields = tClass.getDeclaredFields();
            for(Field field :fields){
                field.getName();

            }
        }
      /*  for(int i=0;i<title.length;i++){
            row.createCell(i).setCellValue(title[i]);
        }*/
/*        row.createCell(0).setCellValue("序号");
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("保险公司全称");
        row.createCell(2).setCellValue("合作状态");
        row.createCell(3).setCellValue("签约机构");
        row.createCell(4).setCellValue("起始时间");
        row.createCell(5).setCellValue("终止时间");
        row.createCell(6).setCellValue("操作人");*/
        for(int i=0;i<insurerinfo.size();i++){

        /*    row=sheet.createRow(i+2);
            Insurerinfo ins=insurerinfo.get(i);
            row.createCell(0).setCellValue(0);
            row.createCell(i+1).setCellValue(0);
            row.createCell(1).setCellValue(ins.getInsurerName());
            row.createCell(2).setCellValue(ins.getState());
            row.createCell(3).setCellValue(ins.getBranchName());
            row.createCell(4).setCellValue(ins.getStartDate());
            row.createCell(5).setCellValue(ins.getEndDate());
            row.createCell(6).setCellValue(ins.getOperator());*/
            row=sheet.createRow(i+2);
            T ins=insurerinfo.get(i);
            row.createCell(0).setCellValue(0);
           // row.createCell(i).setCellValue(fields[i].getName());
   /*         row.createCell(2).setCellValue(ins.getState());
            row.createCell(3).setCellValue(ins.getBranchName());
            row.createCell(4).setCellValue(ins.getStartDate());
            row.createCell(5).setCellValue(ins.getEndDate());
            row.createCell(6).setCellValue(ins.getOperator());*/

        }
        sheet.setDefaultRowHeight((short)(16.5*20));
        /*列宽自适应*/
        for(int j=0;j<=13;j++){
            sheet.autoSizeColumn(j);
        }
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-disposition","attachment;filename=insureInfo.xls");
        try {
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){

        }

    }

}
