package com.imhqs.web.modules.sys.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imhqs.web.core.api.dto.PagingReqDTO;
import com.imhqs.web.modules.sys.user.dto.SysRoleDTO;
import com.imhqs.web.modules.sys.user.entity.SysRole;
import com.imhqs.web.modules.sys.user.mapper.SystemRoleMapper;
import com.imhqs.web.modules.sys.user.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
* 语言设置 服务实现类
*/
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SysRole> implements SysRoleService {

    @Override
    public IPage<SysRoleDTO> paging(PagingReqDTO<SysRoleDTO> reqDTO) {

        //创建分页对象
        IPage<SysRole> query = new Page<>(reqDTO.getCurrent(), reqDTO.getSize());

        //查询条件
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();

        //获得数据
        IPage<SysRole> page = this.page(query, wrapper);
        //转换结果
        IPage<SysRoleDTO> pageData = JSON.parseObject(JSON.toJSONString(page), new TypeReference<Page<SysRoleDTO>>(){});
        return pageData;
     }
}
