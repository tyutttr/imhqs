package com.imhqs.web.modules.sys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imhqs.web.modules.sys.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;


/**
* 管理用户Mapper
*/
@Mapper
public interface SystemUserMapper extends BaseMapper<SysUser> {

}
