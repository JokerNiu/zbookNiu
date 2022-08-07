package com.wdzggroup.rbzy.erp.InsureContro.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

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
@ApiModel(value="Insurerpartinfo对象", description="")
public class Insurerpartinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotBlank
    private String insurerpartName;

    private String insurrerName;
    private String iniId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long creatTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String operator;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    private String updateName;
    @TableId(type = IdType.UUID)
    private String ipiId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer softDelete;


}
