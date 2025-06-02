package com.imhqs.web.modules.repo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imhqs.web.core.api.dto.PagingReqDTO;
import com.imhqs.web.core.utils.BeanMapper;
import com.imhqs.web.modules.repo.dto.RepoDTO;
import com.imhqs.web.modules.repo.dto.response.RepoRespDTO;
import com.imhqs.web.modules.repo.entity.Repo;
import com.imhqs.web.modules.repo.mapper.RepoMapper;
import com.imhqs.web.modules.repo.service.RepoService;
import org.springframework.stereotype.Service;

/**
* 语言设置 服务实现类
*/
@Service
public class RepoServiceImpl extends ServiceImpl<RepoMapper, Repo> implements RepoService {

    @Override
    public IPage<RepoRespDTO> paging(PagingReqDTO<RepoDTO> reqDTO) {

        //创建分页对象
        Page page = new Page(reqDTO.getCurrent(), reqDTO.getSize());

        return baseMapper.paging(page, reqDTO.getParams());
     }

    @Override
    public void save(RepoDTO reqDTO) {

        //复制参数
        Repo entity = new Repo();
        BeanMapper.copy(reqDTO, entity);
        this.saveOrUpdate(entity);
    }

    @Override
    public void refreshStat(String repoId) {
        baseMapper.refreshStat(repoId);
    }


    @Override
    public String findByName(String name) {
        QueryWrapper<Repo> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Repo::getTitle, name);
        Repo repo = this.getOne(wrapper);

        if(repo!=null){
            return repo.getId();
        }

        return null;
    }
}
