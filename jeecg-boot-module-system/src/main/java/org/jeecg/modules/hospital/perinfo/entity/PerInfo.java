package org.jeecg.modules.hospital.perinfo.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 住院人信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Data
@TableName("perinfo")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="perinfo对象", description="住院人信息")
public class PerInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private String name;
	/**sex*/
	@Excel(name = "sex", width = 15)
    @ApiModelProperty(value = "sex")
    private String sex;
	/**percode*/
	@Excel(name = "percode", width = 15)
    @ApiModelProperty(value = "percode")
    private String percode;
	/**inscode*/
	@Excel(name = "inscode", width = 15)
    @ApiModelProperty(value = "inscode")
    private String inscode;
	/**phone*/
	@Excel(name = "phone", width = 15)
    @ApiModelProperty(value = "phone")
    private String phone;
	/**pertype*/
	@Excel(name = "pertype", width = 15)
    @ApiModelProperty(value = "pertype")
    private String pertype;
	/**instype*/
	@Excel(name = "instype", width = 15)
    @ApiModelProperty(value = "instype")
    private String instype;
	/**insdate*/
	@Excel(name = "insdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "insdate")
    private Date insdate;
	/**image*/
	@Excel(name = "image", width = 15)
    @ApiModelProperty(value = "image")
    private String image;
	/**photo*/
	@Excel(name = "photo", width = 15)
    @ApiModelProperty(value = "photo")
    private String photo;
	/**folk*/
	@Excel(name = "folk", width = 15)
    @ApiModelProperty(value = "folk")
    private String folk;
	/**birthday*/
	@Excel(name = "birthday", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "birthday")
    private Date birthday;
	/**address*/
	@Excel(name = "address", width = 15)
    @ApiModelProperty(value = "address")
    private String address;
	/**ogan*/
	@Excel(name = "ogan", width = 15)
    @ApiModelProperty(value = "ogan")
    private String ogan;
	/**beginterm*/
	@Excel(name = "beginterm", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "beginterm")
    private Date beginterm;
	/**endterm*/
	@Excel(name = "endterm", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "endterm")
    private Date endterm;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
    private String status;
	/**facestatus*/
	@Excel(name = "facestatus", width = 15)
    @ApiModelProperty(value = "facestatus")
    private String facestatus;
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
	/**areaid*/
	@Excel(name = "areaid", width = 15)
    @ApiModelProperty(value = "areaid")
    private Integer areaid;
	/**county*/
	@Excel(name = "county", width = 15)
    @ApiModelProperty(value = "county")
    private String county;
	/**town*/
	@Excel(name = "town", width = 15)
    @ApiModelProperty(value = "town")
    private String town;
	/**village*/
	@Excel(name = "village", width = 15)
    @ApiModelProperty(value = "village")
    private String village;
	/**confirmdate*/
	@Excel(name = "confirmdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "confirmdate")
    private Date confirmdate;
	/**icdcodes*/
	@Excel(name = "icdcodes", width = 15)
    @ApiModelProperty(value = "icdcodes")
    private String icdcodes;
	/**limitdate*/
	@Excel(name = "limitdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "limitdate")
    private Date limitdate;
	/**icdnames*/
	@Excel(name = "icdnames", width = 15)
    @ApiModelProperty(value = "icdnames")
    private String icdnames;
}
