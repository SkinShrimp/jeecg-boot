package org.jeecg.modules.hospital.hisuserinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.hospital.hisuserinfo.entity.HisUserInfo;

/**
 * @Description: 登陆用户表
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface HisUserInfoMapper extends BaseMapper<HisUserInfo> {

    HisUserInfo selectByPercode(String token);
}
