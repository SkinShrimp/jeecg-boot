package org.jeecg.modules.hospital.hisinfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 医院表
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
@Data
@TableName("hisinfo")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hisinfo对象", description="医院表")
public class Hisinfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**code*/
	@Excel(name = "code", width = 15)
    @ApiModelProperty(value = "code")
    private String code;
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private String name;
	/**level*/
	@Excel(name = "level", width = 15)
    @ApiModelProperty(value = "level")
    private Integer level;
	/**type*/
	@Excel(name = "type", width = 15)
    @ApiModelProperty(value = "type")
    private Integer type;
	/**dept*/
	@Excel(name = "dept", width = 15)
    @ApiModelProperty(value = "dept")
    private Integer dept;
	/**phone*/
	@Excel(name = "phone", width = 15)
    @ApiModelProperty(value = "phone")
    private String phone;
	/**address*/
	@Excel(name = "address", width = 15)
    @ApiModelProperty(value = "address")
    private String address;
	/**areaid*/
	@Excel(name = "areaid", width = 15)
    @ApiModelProperty(value = "areaid")
    private Integer areaid;
	/**simcode*/
	@Excel(name = "simcode", width = 15)
    @ApiModelProperty(value = "simcode")
    private String simcode;
	/**monittime*/
	@Excel(name = "monittime", width = 15)
    @ApiModelProperty(value = "monittime")
    private String monittime;
	/**usercheckcode*/
	@Excel(name = "usercheckcode", width = 15)
    @ApiModelProperty(value = "usercheckcode")
    private String usercheckcode;
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
	/**longitude*/
	@Excel(name = "longitude", width = 15)
    @ApiModelProperty(value = "longitude")
    private String longitude;
	/**latitude*/
	@Excel(name = "latitude", width = 15)
    @ApiModelProperty(value = "latitude")
    private String latitude;
	/**km*/
	@Excel(name = "km", width = 15)
    @ApiModelProperty(value = "km")
    private BigDecimal km;
	/**inscode*/
	@Excel(name = "inscode", width = 15)
    @ApiModelProperty(value = "inscode")
    private String inscode;
	/**bed*/
	@Excel(name = "bed", width = 15)
    @ApiModelProperty(value = "bed")
    private Integer bed;
	/**applongitude*/
	@Excel(name = "applongitude", width = 15)
    @ApiModelProperty(value = "applongitude")
    private String applongitude;
	/**applatitude*/
	@Excel(name = "applatitude", width = 15)
    @ApiModelProperty(value = "applatitude")
    private String applatitude;
	/**gpsstatus*/
	@Excel(name = "gpsstatus", width = 15)
    @ApiModelProperty(value = "gpsstatus")
    private String gpsstatus;
	/**openid*/
	@Excel(name = "openid", width = 15)
    @ApiModelProperty(value = "openid")
    private Integer openid;
	/**autoauth*/
	@Excel(name = "autoauth", width = 15)
    @ApiModelProperty(value = "autoauth")
    private String autoauth;
}
