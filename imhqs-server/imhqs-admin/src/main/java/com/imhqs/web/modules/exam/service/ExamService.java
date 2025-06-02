package com.imhqs.web.modules.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imhqs.web.core.api.dto.PagingReqDTO;
import com.imhqs.web.modules.exam.dto.ExamDTO;
import com.imhqs.web.modules.exam.dto.request.ExamSaveReqDTO;
import com.imhqs.web.modules.exam.dto.response.ExamOnlineRespDTO;
import com.imhqs.web.modules.exam.dto.response.ExamReviewRespDTO;
import com.imhqs.web.modules.exam.entity.Exam;

/**
* 考试业务类
*/
public interface ExamService extends IService<Exam> {

    /**
     * 保存考试信息
     * @param reqDTO
     */
    void save(ExamSaveReqDTO reqDTO);

    /**
     * 查找考试详情
     * @param id
     * @return
     */
    ExamSaveReqDTO findDetail(String id);

    /**
     * 查找考试详情--简要信息
     * @param id
     * @return
     */
    ExamDTO findById(String id);

    /**
    * 分页查询数据
    * @param reqDTO
    * @return
    */
    IPage<ExamDTO> paging(PagingReqDTO<ExamDTO> reqDTO);


    /**
     * 在线考试分页响应类-考生视角
     * @param reqDTO
     * @return
     */
    IPage<ExamOnlineRespDTO> onlinePaging(PagingReqDTO<ExamDTO> reqDTO);


    /**
     * 待阅试卷列表
     * @param reqDTO
     * @return
     */
    IPage<ExamReviewRespDTO> reviewPaging(PagingReqDTO<ExamDTO> reqDTO);
}
