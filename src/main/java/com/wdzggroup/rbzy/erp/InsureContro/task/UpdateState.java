package com.wdzggroup.rbzy.erp.InsureContro.task;

import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class UpdateState {
    @Autowired
    IInsurerinfoService iInsurerinfoService;
    @Scheduled(cron = "0/5 * * * * *")
    public void updateState(){
        iInsurerinfoService.updateInsureConfigState();
    }
}
