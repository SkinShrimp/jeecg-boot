package org.jeecg.modules.hospital.monitors.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 服务观察
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
@Data
@TableName("monitorlist")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="monitorlist对象", description="服务观察")
public class MonitorList implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**hmid*/
	@Excel(name = "hmid", width = 15)
    @ApiModelProperty(value = "hmid")
    private Integer hmid;
	/**type*/
	@Excel(name = "type", width = 15)
    @ApiModelProperty(value = "type")
    private String type;
	/**monitdate*/
	@Excel(name = "monitdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "monitdate")
    private Date monitdate;
	/**hospdate*/
	@Excel(name = "hospdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "hospdate")
    private Date hospdate;
	/**hospstatus*/
	@Excel(name = "hospstatus", width = 15)
    @ApiModelProperty(value = "hospstatus")
    private String hospstatus;
	/**hospoperator*/
	@Excel(name = "hospoperator", width = 15)
    @ApiModelProperty(value = "hospoperator")
    private String hospoperator;
	/**inid*/
	@Excel(name = "inid", width = 15)
    @ApiModelProperty(value = "inid")
    private Integer inid;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
    private String status;
	/**operator*/
	@Excel(name = "operator", width = 15)
    @ApiModelProperty(value = "operator")
    private String operator;
	/**modifydate*/
	@Excel(name = "modifydate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "modifydate")
    private Date modifydate;
	/**remark*/
	@Excel(name = "remark", width = 15)
    @ApiModelProperty(value = "remark")
    private String remark;
	/**checkstatus*/
	@Excel(name = "checkstatus", width = 15)
    @ApiModelProperty(value = "checkstatus")
    private String checkstatus;
	/**checkdate*/
	@Excel(name = "checkdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "checkdate")
    private Date checkdate;
	/**checkoperator*/
	@Excel(name = "checkoperator", width = 15)
    @ApiModelProperty(value = "checkoperator")
    private String checkoperator;
	/**checkreason*/
	@Excel(name = "checkreason", width = 15)
    @ApiModelProperty(value = "checkreason")
    private String checkreason;

    @TableField(exist = false)
	private String monitorstatus;

    @TableField(exist = false)
    private String gpscheckstatus;

    @TableField(exist = false)
    private String image;
}
