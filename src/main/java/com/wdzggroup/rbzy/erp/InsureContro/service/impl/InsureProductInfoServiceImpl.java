package com.wdzggroup.rbzy.erp.InsureContro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdzggroup.rbzy.erp.InsureContro.IdVO;
import com.wdzggroup.rbzy.erp.InsureContro.bean.InsureProductInfo;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResponseUtils;
import com.wdzggroup.rbzy.erp.InsureContro.exception.BaseException;
import com.wdzggroup.rbzy.erp.InsureContro.exception.Err;
import com.wdzggroup.rbzy.erp.InsureContro.mapper.InsureProductInfoMapper;
import com.wdzggroup.rbzy.erp.InsureContro.service.IInsureProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rbzy
 * @since 2021-01-13
 */
@Service
public class InsureProductInfoServiceImpl extends ServiceImpl<InsureProductInfoMapper, InsureProductInfo> implements IInsureProductInfoService {
    @Autowired
    private IInsureProductInfoService insureProductInfoService;
    @Override
    public IdVO addProductInfo(InsureProductInfo insureProductInfo) {
        LambdaQueryWrapper<InsureProductInfo> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        objectLambdaQueryWrapper.eq(InsureProductInfo::getInsName,insureProductInfo.getInsName());
        Integer integer = this.baseMapper.selectCount(objectLambdaQueryWrapper);
        if(integer!=0){
            throw new BaseException(Err.InsureInfo.NAME_HAS_EXIST);
        }
        this.baseMapper.insert(insureProductInfo);
        IdVO idVO = new IdVO();
        idVO.setIniId(insureProductInfo.getId());
        return idVO;
    }

    @Override
    public IPage<InsureProductInfo> selectProductByPage(Long pageNum, Long pageSize, String proid,
                                                        String insureid, String partInsureid,
                                                        Integer protype, Integer insuretype,
                                                        String begintime, String endtime) {
       if(pageNum==null||pageSize==null){
           pageNum=1l;
           pageSize=10l;
       }
        Page<InsureProductInfo> invo = new Page<>(pageNum,pageSize);

        LambdaQueryWrapper<InsureProductInfo> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        objectLambdaQueryWrapper.eq(InsureProductInfo::getId,insureid);
        long pages = this.baseMapper.selectPage(invo,objectLambdaQueryWrapper).getPages();

        if(pages<pageSize){
           invo=new Page<>(pageNum,pages);
        }
        Page<InsureProductInfo> insureProductInfoPage = this.baseMapper.selectPage(invo, objectLambdaQueryWrapper);

        return insureProductInfoPage;
    }

    @Override
    public void deleteProductInfo(String id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateProductInfo(String id, InsureProductInfo insureProductInfo) {
        LambdaQueryWrapper<InsureProductInfo> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        objectLambdaQueryWrapper.eq(InsureProductInfo::getId,id);
        this.baseMapper.update(insureProductInfo,objectLambdaQueryWrapper);

    }
}
