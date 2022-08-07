package com.wdzggroup.rbzy.erp.InsureContro.service;

import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsurerUpdateLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */

public interface IInsurerUpdateLogService extends IService<InsurerUpdateLog> {
     public IdVO addInsureLog(String beforeName, String afterName);
     InsurerUpdateLog getUpdateLogById(String id);
}
