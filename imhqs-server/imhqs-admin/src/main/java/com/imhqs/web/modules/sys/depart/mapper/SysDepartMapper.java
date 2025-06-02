package com.imhqs.web.modules.sys.depart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imhqs.web.modules.sys.depart.dto.SysDepartDTO;
import com.imhqs.web.modules.sys.depart.dto.response.SysDepartTreeDTO;
import com.imhqs.web.modules.sys.depart.entity.SysDepart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 部门信息Mapper
*/
@Mapper
public interface SysDepartMapper extends BaseMapper<SysDepart> {

    /**
     * 部门树分页
     * @param page
     * @param query
     * @return
     */
    IPage<SysDepartTreeDTO> paging(Page page, @Param("query") SysDepartDTO query);
}
