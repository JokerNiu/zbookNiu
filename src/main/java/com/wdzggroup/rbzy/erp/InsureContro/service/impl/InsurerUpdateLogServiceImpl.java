package com.wdzggroup.rbzy.erp.InsureContro.service.impl;

import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsureInfo;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsurerUpdateLog;
import com.wdzggroup.rbzy.erp.InsureContro.mapper.InsurerUpdateLogMapper;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerUpdateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */
@Service("IInsurerUpdateLogService")
public class InsurerUpdateLogServiceImpl extends ServiceImpl<InsurerUpdateLogMapper, InsurerUpdateLog> implements IInsurerUpdateLogService {

    @Override
    public IdVO addInsureLog(String beforeName, String afterName) {
        InsurerUpdateLog insurerUpdateLog = new InsurerUpdateLog();
        insurerUpdateLog.setBeforeMessage(beforeName);
        insurerUpdateLog.setUpdateMessage(afterName);
        this.baseMapper.insert(insurerUpdateLog);
        IdVO idVO = new IdVO();
        idVO.setIniId(insurerUpdateLog.getIplId());
        return idVO;
    }

    @Override
    public InsurerUpdateLog getUpdateLogById(String id) {
        InsurerUpdateLog insurerUpdateLog = this.baseMapper.selectById(id);
        return insurerUpdateLog;
    }

}
