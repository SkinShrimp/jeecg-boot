package org.jeecg.modules.hospital.hisuserinfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.hospital.hisuserinfo.entity.HisUserInfo;

/**
 * @Description: 登陆用户表
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface IHisUserInfoService extends IService<HisUserInfo> {

    HisUserInfo selectByPercode(String token);
}
