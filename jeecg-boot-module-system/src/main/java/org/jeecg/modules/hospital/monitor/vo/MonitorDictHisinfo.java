package org.jeecg.modules.hospital.monitor.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.hospital.monitors.Tools;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MonitorDictHisinfo {


    /**主键*/
    @TableField(exist = false)
    @ApiModelProperty(value = "主键")
    private String id;
    /**创建日期*/
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    /**hisid*/
    @TableField(exist = false)
    @Excel(name = "hisid", width = 15)
    @ApiModelProperty(value = "hisid")
    private Integer hisid;
    /**type*/
    @TableField(exist = false)
    @Excel(name = "type", width = 15)
    @ApiModelProperty(value = "type")
    private String type;
    /**name*/
    @TableField(exist = false)
    @Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private String name;
    /**sex*/
    @TableField(exist = false)
    @Excel(name = "sex", width = 15)
    @ApiModelProperty(value = "sex")
    private String sex;
    /**percode*/
    @TableField(exist = false)
    @Excel(name = "percode", width = 15)
    @ApiModelProperty(value = "percode")
    private String percode;
    /**inscode*/
    @TableField(exist = false)
    @Excel(name = "inscode", width = 15)
    @ApiModelProperty(value = "inscode")
    private String inscode;
    /**phone*/
    @TableField(exist = false)
    @Excel(name = "phone", width = 15)
    @ApiModelProperty(value = "phone")
    private String phone;
    /**indate*/
    @TableField(exist = false)
    @Excel(name = "indate", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "indate")
    private Date indate;
    /**dept*/
    @TableField(exist = false)
    @Excel(name = "dept", width = 15)
    @ApiModelProperty(value = "dept")
    private Integer dept;
    /**diagnose*/
    @TableField(exist = false)
    @Excel(name = "diagnose", width = 15)
    @ApiModelProperty(value = "diagnose")
    private String diagnose;
    /**incode*/
    @TableField(exist = false)
    @Excel(name = "incode", width = 15)
    @ApiModelProperty(value = "incode")
    private String incode;
    /**wardcode*/
    @TableField(exist = false)
    @Excel(name = "wardcode", width = 15)
    @ApiModelProperty(value = "wardcode")
    private String wardcode;
    /**outdate*/
    @TableField(exist = false)
    @Excel(name = "outdate", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "outdate")
    private Date outdate;
    /**moneycode*/
    @TableField(exist = false)
    @Excel(name = "moneycode", width = 15)
    @ApiModelProperty(value = "moneycode")
    private String moneycode;
    /**money*/
    @TableField(exist = false)
    @Excel(name = "money", width = 15)
    @ApiModelProperty(value = "money")
    private BigDecimal money;
    /**image1*/
    @Excel(name = "image1", width = 15)
    @ApiModelProperty(value = "image1")
    private String image1;
    /**image2*/
    @TableField(exist = false)
    @Excel(name = "image2", width = 15)
    @ApiModelProperty(value = "image2")
    private String image2;
    /**image3*/
    @TableField(exist = false)
    @Excel(name = "image3", width = 15)
    @ApiModelProperty(value = "image3")
    private String image3;
    /**image4*/
    @TableField(exist = false)
    @Excel(name = "image4", width = 15)
    @ApiModelProperty(value = "image4")
    private String image4;
    /**image5*/
    @TableField(exist = false)
    @Excel(name = "image5", width = 15)
    @ApiModelProperty(value = "image5")
    private String image5;
    /**image6*/
    @TableField(exist = false)
    @Excel(name = "image6", width = 15)
    @ApiModelProperty(value = "image6")
    private String image6;
    /**image7*/
    @TableField(exist = false)
    @Excel(name = "image7", width = 15)
    @ApiModelProperty(value = "image7")
    private String image7;
    /**image8*/
    @TableField(exist = false)
    @Excel(name = "image8", width = 15)
    @ApiModelProperty(value = "image8")
    private String image8;
    /**hospdate*/
    @TableField(exist = false)
    @Excel(name = "hospdate", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "hospdate")
    private Date hospdate;
    /**hospoperator*/
    @TableField(exist = false)
    @Excel(name = "hospoperator", width = 15)
    @ApiModelProperty(value = "hospoperator")
    private String hospoperator;
    /**gpsstatus*/
    @TableField(exist = false)
    @Excel(name = "gpsstatus", width = 15)
    @ApiModelProperty(value = "gpsstatus")
    private String gpsstatus;
    /**longitude*/
    @TableField(exist = false)
    @Excel(name = "longitude", width = 15)
    @ApiModelProperty(value = "longitude")
    private String longitude;
    /**latitude*/
    @TableField(exist = false)
    @Excel(name = "latitude", width = 15)
    @ApiModelProperty(value = "latitude")
    private String latitude;
    /**monitorstatus*/
    @TableField(exist = false)
    @Excel(name = "monitorstatus", width = 15)
    @ApiModelProperty(value = "monitorstatus")
    private String monitorstatus;
    /**enddate*/
    @TableField(exist = false)
    @Excel(name = "enddate", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "enddate")
    private Date enddate;
    /**hospnum*/
    @TableField(exist = false)
    @Excel(name = "hospnum", width = 15)
    @ApiModelProperty(value = "hospnum")
    private Integer hospnum;
    /**checkstatus*/
    @TableField(exist = false)
    @Excel(name = "checkstatus", width = 15)
    @ApiModelProperty(value = "checkstatus")
    private String checkstatus;
    /**checkdate*/
    @TableField(exist = false)
    @Excel(name = "checkdate", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "checkdate")
    private Date checkdate;
    /**checkoperator*/
    @TableField(exist = false)
    @Excel(name = "checkoperator", width = 15)
    @ApiModelProperty(value = "checkoperator")
    private String checkoperator;
    /**checkreason*/
    @TableField(exist = false)
    @Excel(name = "checkreason", width = 15)
    @ApiModelProperty(value = "checkreason")
    private String checkreason;
    /**inid*/
    @TableField(exist = false)
    @Excel(name = "inid", width = 15)
    @ApiModelProperty(value = "inid")
    private String inid;
    /**outstatus*/
    @TableField(exist = false)
    @Excel(name = "outstatus", width = 15)
    @ApiModelProperty(value = "outstatus")
    private String outstatus;
    /**status*/
    @TableField(exist = false)
    @Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
    private String status;
    /**operator*/
    @TableField(exist = false)
    @Excel(name = "operator", width = 15)
    @ApiModelProperty(value = "operator")
    private String operator;
    /**modifydate*/
    @TableField(exist = false)
    @Excel(name = "modifydate", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "modifydate")
    private Date modifydate;
    /**remark*/
    @TableField(exist = false)
    @Excel(name = "remark", width = 15)
    @ApiModelProperty(value = "remark")
    private String remark;
    /**extractstatus*/
    @TableField(exist = false)
    @Excel(name = "extractstatus", width = 15)
    @ApiModelProperty(value = "extractstatus")
    private String extractstatus;
    /**gpscheckstatus*/
    @TableField(exist = false)
    @Excel(name = "gpscheckstatus", width = 15)
    @ApiModelProperty(value = "gpscheckstatus")
    private String gpscheckstatus;
    /**lifestatus*/
    @TableField(exist = false)
    @Excel(name = "lifestatus", width = 15)
    @ApiModelProperty(value = "lifestatus")
    private String lifestatus;
    /**supcode1*/
    @TableField(exist = false)
    @Excel(name = "supcode1", width = 15)
    @ApiModelProperty(value = "supcode1")
    private String supcode1;
    /**supcode2*/
    @TableField(exist = false)
    @Excel(name = "supcode2", width = 15)
    @ApiModelProperty(value = "supcode2")
    private String supcode2;
    /**supcode3*/
    @TableField(exist = false)
    @Excel(name = "supcode3", width = 15)
    @ApiModelProperty(value = "supcode3")
    private String supcode3;
    /** supcode4*/
    @TableField(exist = false)
    @Excel(name = " supcode4", width = 15)
    @ApiModelProperty(value = " supcode4")
    private String supcode4;
    /**pertype*/
    @TableField(exist = false)
    @Excel(name = "pertype", width = 15)
    @ApiModelProperty(value = "pertype")
    private String pertype;
    /**areaid*/
    @TableField(exist = false)
    @Excel(name = "areaid", width = 15)
    @ApiModelProperty(value = "areaid")
    private String areaid;

    @TableField(exist = false)
    @Excel(name = "dictName", width = 15)
    @ApiModelProperty(value = "dictName")
    private String dictName;

    @TableField(exist = false)
    @Excel(name = "hospitalName", width = 15)
    @ApiModelProperty(value = "hospitalName")
    private String hospitalName;

    private Integer lengthOfStay;

    public Integer getLengthOfStay() {
        if(this.outstatus != null){
            if(this.outstatus.equals("1")){
                lengthOfStay = Tools.currDay(this.indate,null);
            }else if(this.outstatus.equals("2")){
                lengthOfStay = Tools.currDay(this.indate,outdate);
            }
        }else{
            lengthOfStay =0;
        }
        return lengthOfStay;
    }

    public void setLengthOfStay(Integer lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }
}
