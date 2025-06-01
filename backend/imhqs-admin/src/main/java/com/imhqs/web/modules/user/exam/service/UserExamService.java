package com.imhqs.web.modules.user.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imhqs.web.core.api.dto.PagingReqDTO;
import com.imhqs.web.modules.user.exam.dto.request.UserExamReqDTO;
import com.imhqs.web.modules.user.exam.dto.response.UserExamRespDTO;
import com.imhqs.web.modules.user.exam.entity.UserExam;

/**
* 考试记录业务类
*/
public interface UserExamService extends IService<UserExam> {

    /**
    * 分页查询数据
    * @param reqDTO
    * @return
    */
    IPage<UserExamRespDTO> paging(PagingReqDTO<UserExamReqDTO> reqDTO);

    /**
     * 分页查询数据
     * @param reqDTO
     * @return
     */
    IPage<UserExamRespDTO> myPaging(PagingReqDTO<UserExamReqDTO> reqDTO);


    /**
     * 考试完成后加入成绩
     * @param userId
     * @param examId
     * @param score
     * @param passed
     */
    void joinResult(String userId, String examId, Integer score, boolean passed);
}
