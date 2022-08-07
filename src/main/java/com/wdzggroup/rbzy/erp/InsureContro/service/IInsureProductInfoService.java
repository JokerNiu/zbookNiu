package com.wdzggroup.rbzy.erp.InsureContro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsureProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rbzy
 * @since 2021-01-13
 */
public interface IInsureProductInfoService extends IService<InsureProductInfo> {
    /*新增产品信息*/
    IdVO addProductInfo(InsureProductInfo insureProductInfo);
    /*分页查询查询产品信息*/
    IPage<InsureProductInfo> selectProductByPage(Long pageNum,Long pageSize,String proid,String insureid,String partInsureid,Integer protype,Integer insuretype
            ,String begintime,String endtime);
    /*删除产品信息*/
    void deleteProductInfo(String  id);
    /*修改产品信息*/
    void updateProductInfo(String id,InsureProductInfo insureProductInfo);


}
