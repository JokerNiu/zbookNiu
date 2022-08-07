package com.wdzggroup.rbzy.erp.InsureContro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.PartCompany;
import com.wdzggroup.rbzy.erp.InsureContro.bean.Insurerinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */
public interface IInsurerinfoService extends IService<Insurerinfo> {
    /*新增保险公司管理*/
    IdVO addInsureConfigInfo(Insurerinfo insurerinfo);
    /*编辑保险公司管理信息*/
    void updateInsureConfigInfo(String id,Insurerinfo insurerinfo);
    /*终止保险公司合作状态*/
    void updateInsureConfigStateByids(List<String> idList);
    /*启动保险公司合作状态*/
    void startInfoupdateInsureConfigStateByids(List<String> idList);
    /*定时提醒*/
    void updateInsureConfigState();
    /*根据保险公司总公司名称查询保险公司信息（分页查询）*/
    IPage<Insurerinfo> getInsureConfigByPage(Long pageNum,Long pageSize,
                                             Integer state,String cid,Integer type,
                                             String pid,Long beginDate,Long endDate);
   //*根据总公司名称查询关联公司名称*//*
    List<Insurerinfo> getInsureInfoByName( Integer state,String cid,Integer type,
                                 String pid,Long beginDate,Long endDate,String sheetName) throws IOException;

    /*上传文件*/
    String uploadInsureConfigFile( MultipartFile file);
    /*导出文件*/
     <T>void exportInsureConfigFile(List<T> insurerinfolist, String sheetname) throws IOException;

}
