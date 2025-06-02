package com.imhqs.web.modules.sys.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imhqs.web.core.utils.BeanMapper;
import com.imhqs.web.modules.sys.config.dto.SysConfigDTO;
import com.imhqs.web.modules.sys.config.entity.SysConfig;
import com.imhqs.web.modules.sys.config.mapper.SystemConfigMapper;
import com.imhqs.web.modules.sys.config.service.SystemConfigService;
import org.springframework.stereotype.Service;

/**
* 语言设置 服务实现类
*/
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SysConfig> implements SystemConfigService {

    @Override
    public SysConfigDTO find() {

        QueryWrapper<SysConfig> wrapper = new QueryWrapper<>();
        wrapper.last(" LIMIT 1");

        SysConfig entity = this.getOne(wrapper, false);
        SysConfigDTO dto = new SysConfigDTO();
        BeanMapper.copy(entity, dto);
        return dto;
    }
}
