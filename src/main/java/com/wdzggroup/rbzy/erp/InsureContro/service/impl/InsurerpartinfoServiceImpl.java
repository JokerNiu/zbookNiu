package com.wdzggroup.rbzy.erp.InsureContro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.PartCompany;
import com.wdzggroup.rbzy.erp.InsureContro.bean.Insurerpartinfo;
import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.exception.BaseException;
import com.wdzggroup.rbzy.erp.InsureContro.exception.Err;
import com.wdzggroup.rbzy.erp.InsureContro.mapper.InsurerpartinfoMapper;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerUpdateLogService;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerpartinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import javafx.scene.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-03
 */
@Service
public class InsurerpartinfoServiceImpl extends ServiceImpl<InsurerpartinfoMapper, Insurerpartinfo> implements IInsurerpartinfoService {
    @Autowired
    IInsurerUpdateLogService iInsurerUpdateLogService;
    @Override
    public IdVO addInsurePartInfo(Insurerpartinfo insurerpartinfo) {
        LambdaQueryWrapper<Insurerpartinfo> insurerpartinfoLambdaQueryWrapper = Wrappers.lambdaQuery();
        insurerpartinfoLambdaQueryWrapper.eq(Insurerpartinfo::getInsurerpartName,insurerpartinfo.getInsurerpartName());
        Integer integer = this.baseMapper.selectCount(insurerpartinfoLambdaQueryWrapper);
        if(integer>0){
            throw new  BaseException(Err.InsureInfo.NAME_HAS_EXIST);
        }
        this.baseMapper.insert(insurerpartinfo);
        IdVO idVO = new IdVO();
        idVO.setIniId(insurerpartinfo.getIpiId());
        return idVO;
    }

    @Override
    public void updateInsurePartInfo(Insurerpartinfo insurerpartinfo, String id) {
        /*判断输入得名称是否已经存在*/
        Insurerpartinfo insurerpartinfobefore = this.baseMapper.selectById(id);
        String beforeName=insurerpartinfobefore.getInsurerpartName();
        String afterName=insurerpartinfo.getInsurerpartName();
        LambdaQueryWrapper<Insurerpartinfo> InsurerpartinfoLambdaQueryWrapper = Wrappers.lambdaQuery();
        InsurerpartinfoLambdaQueryWrapper.eq(Insurerpartinfo::getInsurerpartName,afterName);
        Integer integer = this.baseMapper.selectCount(InsurerpartinfoLambdaQueryWrapper);
        if(integer>0){
            throw  new BaseException(Err.InsureInfo.NAME_HAS_EXIST);
        }
        LambdaUpdateWrapper<Insurerpartinfo> InsurerpartinfoLambdaUpdateWrapper = Wrappers.lambdaUpdate();
        InsurerpartinfoLambdaUpdateWrapper.eq(Insurerpartinfo::getIpiId,id).eq(Insurerpartinfo::getSoftDelete,Canst.DataCanst.SOFT_DELETE.getCode());
        this.baseMapper.update(insurerpartinfo,InsurerpartinfoLambdaUpdateWrapper);
        /*更新修改记录表*/
        iInsurerUpdateLogService.addInsureLog(beforeName,afterName);


    }

    @Override
    public void deleteInsurePartInfo(String id) {
        Insurerpartinfo insurerpartinfo = this.baseMapper.selectById(id);
        insurerpartinfo.setSoftDelete(Canst.DataCanst.SOFT_UDELETE_.getCode());
        this.baseMapper.updateById(insurerpartinfo);
    }

    @Override
    public IPage<Insurerpartinfo> getInsurePartInfoByPage(Long pageNum, Long pageSize, String companyName) {
        if(pageNum==null||pageSize==null){
            pageNum=1l;
            pageSize=10l;
        }
        Page<Insurerpartinfo> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Insurerpartinfo> InsurerpartinfoLambdaQueryWrapper = Wrappers.lambdaQuery();
        InsurerpartinfoLambdaQueryWrapper.like(StringUtils.isNotEmpty(companyName),Insurerpartinfo::getInsurerpartName,companyName)
                .eq(Insurerpartinfo::getSoftDelete,Canst.DataCanst.SOFT_DELETE.getCode());
        long pages = this.baseMapper.selectPage(page, InsurerpartinfoLambdaQueryWrapper).getPages();

        if(pageNum>pages){
            page =new Page<>(pages,pageSize);
        }
        Page<Insurerpartinfo> insurerpartinfoPage = this.baseMapper.selectPage(page, InsurerpartinfoLambdaQueryWrapper);
        return insurerpartinfoPage;
    }

    @Override
    public Insurerpartinfo getInsurePartInfoById(String id) {
        return  this.baseMapper.selectById(id);
    }


    @Override
    public List<Insurerpartinfo> getInsureInfoById(String id) {
        LambdaQueryWrapper<Insurerpartinfo> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        objectLambdaQueryWrapper.eq(Insurerpartinfo::getIniId,id);
        List<Insurerpartinfo> insurerpartinfos = this.baseMapper.selectList(objectLambdaQueryWrapper);
       /* List<PartCompany> list=new ArrayList<>();
        for (Insurerpartinfo ins:insurerpartinfos
             ) {
            PartCompany partCompany = new PartCompany();
            partCompany.setId(ins.getIpiId());
            partCompany.setPartCompany(ins.getInsurerpartName());
            list.add(partCompany);

        }*/
        return insurerpartinfos;
    }
}
