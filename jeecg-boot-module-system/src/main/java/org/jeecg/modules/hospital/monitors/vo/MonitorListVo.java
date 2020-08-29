package org.jeecg.modules.hospital.monitors.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.jeecg.modules.hospital.monitor.entity.Hospitalmonitor;
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
@ApiModel(value="monitorlistVO对象", description="服务观察")
public class MonitorListVo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
    private String id;
	/**创建日期*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
	/**hmid*/
    private Integer hmid;
	/**type*/
    private String type;
	/**monitdate*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date monitdate;
	/**hospdate*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date hospdate;
	/**hospstatus*/
    private String hospstatus;
	/**hospoperator*/
    private String hospoperator;
	/**inid*/
    private Integer inid;
	/**status*/
    private String status;
	/**operator*/
    private String operator;
	/**modifydate*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date modifydate;
	/**remark*/
    private String remark;
	/**checkstatus*/
    private String checkstatus;
	/**checkdate*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date checkdate;
	/**checkoperator*/
    private String checkoperator;
	/**checkreason*/
    private String checkreason;
    private Hospitalmonitor hospitalmonitor;

}
