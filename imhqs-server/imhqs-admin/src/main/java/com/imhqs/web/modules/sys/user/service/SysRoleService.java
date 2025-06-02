package com.imhqs.web.modules.sys.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imhqs.web.core.api.dto.PagingReqDTO;
import com.imhqs.web.modules.sys.user.dto.SysRoleDTO;
import com.imhqs.web.modules.sys.user.entity.SysRole;

/**
* 角色业务类
*/
public interface SysRoleService extends IService<SysRole> {

    /**
    * 分页查询数据
    * @param reqDTO
    * @return
    */
    IPage<SysRoleDTO> paging(PagingReqDTO<SysRoleDTO> reqDTO);
}
