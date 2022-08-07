package com.wdzggroup.rbzy.erp.InsureContro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsureInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */
public interface IInsureInfoService extends IService<InsureInfo> {
    //查询列表
     List<InsureInfo> getInsureByName();
    //根据Id查询
    InsureInfo getInsureById(String id);
    // 新增
     IdVO addInsure(InsureInfo insureInfo);
    //删除

    void  deleteInsure(@Param("id") String id);
    void updateInsure(String id,InsureInfo insureInfo);
    //分页查询
    IPage<InsureInfo> getInsureByPage(Long pageNum,Long pageSize,String companyName);
}
