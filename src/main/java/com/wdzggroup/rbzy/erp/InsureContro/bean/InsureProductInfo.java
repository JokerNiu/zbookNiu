package com.wdzggroup.rbzy.erp.InsureContro.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rbzy
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="InsureProductInfo对象", description="")
public class InsureProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "产品名称")
    private String proName;

    @ApiModelProperty(value = "保险公司")
    private String insName;

    @ApiModelProperty(value = "产品类型 线下--1 线上--2")
    private Integer proType;

    @ApiModelProperty(value = "销售区域")
    private String proSelarea;

    @ApiModelProperty(value = "联系电话")
    private String proPhone;

    @ApiModelProperty(value = "犹豫期")
    private Integer proYouyu;

    @ApiModelProperty(value = "条款编码")
    private String proTiaokuan;

    @ApiModelProperty(value = "分公司")
    private String proPartname;

    @ApiModelProperty(value = "字段模板")
    private String proMoban;

    @ApiModelProperty(value = "联系地址")
    private String proAddress;

    @ApiModelProperty(value = "是否代收保费 是--1 否--0")
    private Integer proBaofei;

    @ApiModelProperty(value = "销售渠道")
    private String proQudao;

    @ApiModelProperty(value = "险种 财险--1 人生险--2 ")
    private Integer proXianzhong;

    @ApiModelProperty(value = "合作日期")
    private String proTime;

    @ApiModelProperty(value = "产品状态 待发布--0 已发布--1 停售--2")
    private Integer proZhuangtai;

    @ApiModelProperty(value = "保险公司联系人")
    private String proPerson;

    @ApiModelProperty(value = "推广费率（选填）")
    private String proTuiguang;

    @ApiModelProperty(value = "佣金率")
    private String proYongjing;

    @ApiModelProperty(value = "创收率")
    private String proChuangshou;

    @ApiModelProperty(value = "手续费费率")
    private String proShouxufei;


}
