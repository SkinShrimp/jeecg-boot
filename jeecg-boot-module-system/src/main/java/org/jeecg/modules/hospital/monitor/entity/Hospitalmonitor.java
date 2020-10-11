package org.jeecg.modules.hospital.monitor.entity;

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
import org.jeecg.modules.hospital.dictionary.entity.Dictionary;
import org.jeecg.modules.hospital.hisinfo.entity.Hisinfo;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 医院患者服务表
 * @Author: jeecg-boot
 * @Date:   2020-08-23
 * @Version: V1.0
 */
@Data
@TableName("hospitalmonitor")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hospitalmonitor对象", description="医院患者服务表")
public class Hospitalmonitor implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**hisid*/
	@Excel(name = "hisid", width = 15)
    @ApiModelProperty(value = "hisid")
    private Integer hisid;
	/**type*/
	@Excel(name = "type", width = 15)
    @ApiModelProperty(value = "type")
    private String type;
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
	/**indate*/
	@Excel(name = "indate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "indate")
    private Date indate;
	/**dept*/
	@Excel(name = "dept", width = 15)
    @ApiModelProperty(value = "dept")
    private Integer dept;
	/**diagnose*/
	@Excel(name = "diagnose", width = 15)
    @ApiModelProperty(value = "diagnose")
    private String diagnose;
	/**incode*/
	@Excel(name = "incode", width = 15)
    @ApiModelProperty(value = "incode")
    private String incode;
	/**wardcode*/
	@Excel(name = "wardcode", width = 15)
    @ApiModelProperty(value = "wardcode")
    private String wardcode;
	/**outdate*/
	@Excel(name = "outdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "outdate")
    private Date outdate;
	/**moneycode*/
	@Excel(name = "moneycode", width = 15)
    @ApiModelProperty(value = "moneycode")
    private String moneycode;
	/**money*/
	@Excel(name = "money", width = 15)
    @ApiModelProperty(value = "money")
    private BigDecimal money;
	/**image1*/
	@Excel(name = "image1", width = 15)
    @ApiModelProperty(value = "image1")
    private String image1;
	/**image2*/
	@Excel(name = "image2", width = 15)
    @ApiModelProperty(value = "image2")
    private String image2;
	/**image3*/
	@Excel(name = "image3", width = 15)
    @ApiModelProperty(value = "image3")
    private String image3;
	/**image4*/
	@Excel(name = "image4", width = 15)
    @ApiModelProperty(value = "image4")
    private String image4;
	/**image5*/
	@Excel(name = "image5", width = 15)
    @ApiModelProperty(value = "image5")
    private String image5;
	/**image6*/
	@Excel(name = "image6", width = 15)
    @ApiModelProperty(value = "image6")
    private String image6;
	/**image7*/
	@Excel(name = "image7", width = 15)
    @ApiModelProperty(value = "image7")
    private String image7;
	/**image8*/
	@Excel(name = "image8", width = 15)
    @ApiModelProperty(value = "image8")
    private String image8;
	/**hospdate*/
	@Excel(name = "hospdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "hospdate")
    private Date hospdate;
	/**hospoperator*/
	@Excel(name = "hospoperator", width = 15)
    @ApiModelProperty(value = "hospoperator")
    private String hospoperator;
	/**gpsstatus*/
	@Excel(name = "gpsstatus", width = 15)
    @ApiModelProperty(value = "gpsstatus")
    private String gpsstatus;
	/**longitude*/
	@Excel(name = "longitude", width = 15)
    @ApiModelProperty(value = "longitude")
    private String longitude;
	/**latitude*/
	@Excel(name = "latitude", width = 15)
    @ApiModelProperty(value = "latitude")
    private String latitude;
	/**monitorstatus*/
	@Excel(name = "monitorstatus", width = 15)
    @ApiModelProperty(value = "monitorstatus")
    private String monitorstatus;
	/**enddate*/
	@Excel(name = "enddate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "enddate")
    private Date enddate;
	/**hospnum*/
	@Excel(name = "hospnum", width = 15)
    @ApiModelProperty(value = "hospnum")
    private Integer hospnum;
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
	/**inid*/
	@Excel(name = "inid", width = 15)
    @ApiModelProperty(value = "inid")
    private String inid;
	/**outstatus*/
	@Excel(name = "outstatus", width = 15)
    @ApiModelProperty(value = "outstatus")
    private String outstatus;
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
	/**extractstatus*/
	@Excel(name = "extractstatus", width = 15)
    @ApiModelProperty(value = "extractstatus")
    private String extractstatus;
	/**gpscheckstatus*/
	@Excel(name = "gpscheckstatus", width = 15)
    @ApiModelProperty(value = "gpscheckstatus")
    private String gpscheckstatus;
	/**lifestatus*/
	@Excel(name = "lifestatus", width = 15)
    @ApiModelProperty(value = "lifestatus")
    private String lifestatus;
	/**supcode1*/
	@Excel(name = "supcode1", width = 15)
    @ApiModelProperty(value = "supcode1")
    private String supcode1;
	/**supcode2*/
	@Excel(name = "supcode2", width = 15)
    @ApiModelProperty(value = "supcode2")
    private String supcode2;
	/**supcode3*/
	@Excel(name = "supcode3", width = 15)
    @ApiModelProperty(value = "supcode3")
    private String supcode3;
	/** supcode4*/
	@Excel(name = " supcode4", width = 15)
    @ApiModelProperty(value = " supcode4")
    private String supcode4;
	/**pertype*/
	@Excel(name = "pertype", width = 15)
    @ApiModelProperty(value = "pertype")
    private String pertype;
	/**areaid*/
	@Excel(name = "areaid", width = 15)
    @ApiModelProperty(value = "areaid")
    private String areaid;

    @TableField(exist = false)
	private Hisinfo hisinfo;

    @TableField(exist = false)
    private Dictionary dictionary;


    @TableField(exist = false)
    private String token;

    @TableField(exist = false)
    private String image;

}
