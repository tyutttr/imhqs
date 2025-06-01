package com.imhqs.web.modules.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imhqs.web.modules.repo.dto.RepoDTO;
import com.imhqs.web.modules.repo.dto.response.RepoRespDTO;
import com.imhqs.web.modules.repo.entity.Repo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 题库Mapper
*/
@Mapper
public interface RepoMapper extends BaseMapper<Repo> {

    /**
     * 分页查询题库
     * @param page
     * @param query
     * @return
     */
    IPage<RepoRespDTO> paging(Page page, @Param("query") RepoDTO query);

    /**
     * 更新统计数量
     * @param repoId
     */
    void refreshStat(@Param("repoId") String repoId);

}
