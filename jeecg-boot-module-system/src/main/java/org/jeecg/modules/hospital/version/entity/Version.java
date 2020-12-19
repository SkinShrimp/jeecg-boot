package org.jeecg.modules.hospital.version.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("version")
public class Version {
    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;

    @TableField("type")
    private String type;
    @TableField("url")
    private String url;
    @TableField("code")
    private String code;
    @TableField("check_times")
    private Integer checkTimes;

}
