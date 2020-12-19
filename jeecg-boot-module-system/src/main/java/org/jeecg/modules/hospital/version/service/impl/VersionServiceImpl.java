package org.jeecg.modules.hospital.version.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.hospital.version.entity.Version;
import org.jeecg.modules.hospital.version.mapper.VersionMapper;
import org.jeecg.modules.hospital.version.service.IVersionService;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl extends ServiceImpl<VersionMapper, Version> implements IVersionService {
}
