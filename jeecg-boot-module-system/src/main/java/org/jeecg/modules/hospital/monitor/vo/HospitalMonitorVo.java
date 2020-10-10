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
}
