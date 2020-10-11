package org.jeecg.modules.hospital.monitor.vo;

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
import org.apache.commons.lang.StringUtils;
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
public class HospitalMonitorVo {

    private String url="/home/tomcat8/test/webapps/ROOT/image/";
    private String name;//姓名
    private Long sex;//性别
    private String sexName;//性别
    private String percode;//身份证号
    private String phone;//联系电话
    private String inscode;//社保编号
    private String incode;//入院编号
    private String hisName;//医院名称
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date indate;//入院日期
    private String deptName;//科室
    private String diagnose;//诊断
    private String wardcode;//病房编号
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date outdate;//出院日期
    private String moneycode;//费用编号
    private String money;//费用金额
    private String image;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
    private String image7;
    private String image8;

    public String getSexName() {
        if(this.sex !=null){
            if(this.sex==1){
                return "男";
            }else if(this.sex==2){
                return "女";
            }
        }
        return null;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }


    public String getImage() {
        if(StringUtils.isNotBlank(this.image)) {
            return this.url+ image;
        }else{
            return null;
        }
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        if(StringUtils.isNotBlank(this.image1)) {
            return this.url+ image1;
        }else{
            return null;
        }
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        if(StringUtils.isNotBlank(this.image2)) {
            return this.url+ image2;
        }else{
            return null;
        }
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        if(StringUtils.isNotBlank(this.image3)) {
            return this.url+ image3;
        }else{
            return null;
        }
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        if(StringUtils.isNotBlank(this.image4)) {
            return this.url+ image4;
        }else{
            return null;
        }
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        if(StringUtils.isNotBlank(this.image5)) {
            return this.url+ image5;
        }else{
            return null;
        }
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getImage6() {
        if(StringUtils.isNotBlank(this.image6)) {
            return this.url+ image6;
        }else{
            return null;
        }    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    public String getImage7() {
        if(StringUtils.isNotBlank(this.image7)) {
            return this.url+ image7;
        }else{
            return null;
        }
    }

    public void setImage7(String image7) {
        this.image7 = image7;
    }

    public String getImage8() {
        if(StringUtils.isNotBlank(this.image8)) {
            return this.url+ image8;
        }else{
            return null;
        }
    }

    public void setImage8(String image8) {
        this.image8 = image8;
    }
}
