package com.wdzggroup.rbzy.erp.InsureContro.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Userinfo对象", description="")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("UI_id")
    private String uiId;

    @ApiModelProperty(value = "用户名")
    @TableField("UI_name")
    private String uiName;

    @ApiModelProperty(value = "年龄")
    @TableField("UI_age")
    private Integer uiAge;

    @ApiModelProperty(value = "邮箱")
    @TableField("UI_email")
    private String uiEmail;

    @ApiModelProperty(value = "创建时间")
    @TableField("UI_create_time")
    private LocalDateTime uiCreateTime;

    @ApiModelProperty(value = "证件号码")
    @TableField("UI_certno")
    private String uiCertno;

    @ApiModelProperty(value = "密码")
    @TableField("UI_password")
    private String uiPassword;


}
