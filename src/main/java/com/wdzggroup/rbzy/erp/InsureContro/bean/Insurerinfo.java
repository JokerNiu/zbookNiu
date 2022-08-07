package com.wdzggroup.rbzy.erp.InsureContro.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Insurerinfo对象", description="")
public class Insurerinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String insurerName;

    @ApiModelProperty(value = "0-未合作，1-合作中，2-曾合作")
    private Integer state;

    @TableField("branchInsurer_name")
    private String branchinsurerName;

    @TableField("branchName")
    private String branchName;

    @TableField("endDate")
    private Long endDate;

    @TableField("startDate")
    private Long startDate;

    private String operator;

    @ApiModelProperty(value = "0-产险，1-寿险")
    private Integer type;

    private String email;

    private String appertment;

    private String username;

    private String userphone;

    private String code;

    @TableField("branchId")
    private Integer branchId;

    private Integer openAccountno2;

    private String openAccountname;

    private String address;

    private Integer phione;

    private String flag;

    private String ur;

    private String renarks;

    @TableId(type = IdType.UUID)
    private String insId;

    private Integer softDeete;

    private String creatName;

    private Long creatTime;

    private String updateName;

    private Long updateTime;

    private String insurerpartId;


}
