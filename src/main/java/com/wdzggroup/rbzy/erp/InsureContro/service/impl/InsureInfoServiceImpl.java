package com.wdzggroup.rbzy.erp.InsureContro.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsureInfo;


import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.common.IRespStatus;
import com.wdzggroup.rbzy.erp.InsureContro.exception.BaseException;
import com.wdzggroup.rbzy.erp.InsureContro.exception.Err;
import com.wdzggroup.rbzy.erp.InsureContro.mapper.InsureInfoMapper;

import com.wdzggroup.rbzy.erp.InsureContro.service.IInsureInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wdzggroup.rbzy.erp.InsureContro.service.IInsurerUpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
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
public class InsureInfoServiceImpl extends ServiceImpl<InsureInfoMapper, InsureInfo> implements IInsureInfoService {


    @Autowired
    IInsurerUpdateLogService iInsurerUpdateLogService;
    @Autowired
    IInsureInfoService iInsureInfoService;
    @Override
    public List<InsureInfo> getInsureByName( ) {
        return this.baseMapper.selectList(null);
    }

    @Override
    public InsureInfo getInsureById(String id) {
        return  this.baseMapper.selectById(id);
    }

    @Override
    public IdVO addInsure(InsureInfo insureInfo) {
        /*QueryWrapper<InsureInfo> query = new QueryWrapper<>();
        query.eq("insurercon_name",insureInfo.getInsurerconName());*/
        //lambda表达式校验
        LambdaQueryWrapper<InsureInfo> insure = Wrappers.lambdaQuery();
        insure.eq(InsureInfo::getInsurerconName,insureInfo.getInsurerconName());
        Integer integer = this.baseMapper.selectCount(insure);
        System.out.println(integer);
        if(integer>0){
            throw new BaseException(Err.InsureInfo.NAME_HAS_EXIST);
        }
        this.baseMapper.insert(insureInfo);
        IdVO idVO = new IdVO();
        idVO.setIniId(insureInfo.getIniId());

        return idVO;
    }

    @Override

    public void deleteInsure(String id) {
        //硬删除
     /*   QueryWrapper<InsureInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ini_Id",id);
        int delete=this.baseMapper.delete(queryWrapper);*/

        //软删除
        InsureInfo insureInfo = this.baseMapper.selectById(id);
        insureInfo.setSoftDelete(Canst.DataCanst.SOFT_UDELETE_.getCode());
        this.baseMapper.updateById(insureInfo);

    }

    @Override
    public void updateInsure(String id ,InsureInfo insureInfo) {

        InsureInfo insureInfobefore = this.baseMapper.selectById(id);
        LambdaQueryWrapper<InsureInfo> LambdaQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper.eq(InsureInfo::getInsurerconName,insureInfo.getInsurerconName());
        Integer integer = this.baseMapper.selectCount(LambdaQueryWrapper);
        if(integer>0){
            throw new BaseException(Err.InsureInfo.NAME_HAS_EXIST);
        }
        LambdaUpdateWrapper<InsureInfo> LambdaUpdateWrapper = Wrappers.lambdaUpdate();
        LambdaUpdateWrapper.eq(InsureInfo::getIniId,id).eq(InsureInfo::getSoftDelete, Canst.DataCanst.SOFT_DELETE.getCode());
        this.baseMapper.update(insureInfo, LambdaUpdateWrapper);
            //修改记录表
        IdVO idVO = iInsurerUpdateLogService.addInsureLog(insureInfobefore.getInsurerconName(), insureInfo.getInsurerconName());
    }

    @Override
    public IPage<InsureInfo> getInsureByPage(Long pageNum,Long pageSize,String companyName) {
        if(pageNum==null|| pageSize==null){
            pageNum=1l;
            pageSize=10l;
        }
        Page<InsureInfo> invo = new Page<>(pageNum,pageSize);
      //lambda表达式
        LambdaQueryWrapper<InsureInfo> LambdaQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper.eq(InsureInfo::getSoftDelete, Canst.DataCanst.SOFT_DELETE).like(StringUtils.isNotEmpty(companyName),
                InsureInfo::getInsurerconName,companyName);

        long pages = this.baseMapper.selectPage(invo, LambdaQueryWrapper).getPages();
        if(pageNum>pages){
            invo=new Page<>(pages,pageSize);
        }
        return this.baseMapper.selectPage(invo,LambdaQueryWrapper);
    }

}
