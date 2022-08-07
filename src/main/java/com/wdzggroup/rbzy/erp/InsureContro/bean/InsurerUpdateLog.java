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
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="InsurerUpdateLog对象", description="")
public class InsurerUpdateLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "ipl_id",type = IdType.UUID)
    private String iplId;

    private Long updateTime;

    private String updateName;

    private String message;

    private String beforeMessage;

    private String updateMessage;


}
