package com.wdzggroup.rbzy.erp.InsureContro.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@ApiModel(value="Branchinfo对象", description="")
@AllArgsConstructor
public class Branchinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String branchName;

    private Integer branchId;

    private Integer softDelete;
    @TableId("brhId")
    private String brhId;

    private Long creatTime;


}
