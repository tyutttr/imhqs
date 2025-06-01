package com.imhqs.web.modules.qu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imhqs.web.core.api.dto.PagingReqDTO;
import com.imhqs.web.modules.qu.dto.QuDTO;
import com.imhqs.web.modules.qu.dto.export.QuExportDTO;
import com.imhqs.web.modules.qu.dto.ext.QuDetailDTO;
import com.imhqs.web.modules.qu.dto.request.QuQueryReqDTO;
import com.imhqs.web.modules.qu.entity.Qu;

import java.util.List;

/**
* 问题题目业务类
*/
public interface QuService extends IService<Qu> {

    /**
     * 分页查询数据
     * @param reqDTO
     * @return
     */
    IPage<QuDTO> paging(PagingReqDTO<QuQueryReqDTO> reqDTO);

    /**
     * 随机抽取题库的数据
     * @param repoId
     * @param quType
     * @param level  难度等级
     * @param excludes 要排除的ID列表
     * @param size
     * @return
     */
    List<Qu> listByRandom(String repoId,
                          Integer quType,
                          Integer level,
                          List<String> excludes,
                          Integer size);

    /**
     * 问题详情
     * @param id
     * @return
     */
    QuDetailDTO detail(String id);

    /**
     * 保存试题
     * @param reqDTO
     */
    void save(QuDetailDTO reqDTO);

    /**
     * 查找导出列表
     * @param query
     * @return
     */
    List<QuExportDTO> listForExport(QuQueryReqDTO query);

    /**
     * 导入Excel
     * @param dtoList
     * @return
     */
    int importExcel(List<QuExportDTO> dtoList);
}
