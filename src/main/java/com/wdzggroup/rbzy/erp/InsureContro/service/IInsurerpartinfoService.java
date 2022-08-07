package com.wdzggroup.rbzy.erp.InsureContro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.PartCompany;
import com.wdzggroup.rbzy.erp.InsureContro.bean.Insurerpartinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */
public interface IInsurerpartinfoService extends IService<Insurerpartinfo> {
    //新增
    IdVO addInsurePartInfo(Insurerpartinfo insurerpartinfo);
    //修改
    void updateInsurePartInfo(Insurerpartinfo insurerpartinfo,String id);
    //删除
    void deleteInsurePartInfo(String id);
    //分页查询
    IPage<Insurerpartinfo> getInsurePartInfoByPage(Long pageNum,Long pageSize,String companyName);
    //根据id查询
    Insurerpartinfo getInsurePartInfoById(String id);
    /*根据总公司名称查询关联公司名称*/
    List<Insurerpartinfo> getInsureInfoById(String id);
}
