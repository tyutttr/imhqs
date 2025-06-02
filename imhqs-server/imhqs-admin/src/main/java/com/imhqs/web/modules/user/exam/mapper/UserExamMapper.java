package com.imhqs.web.modules.user.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imhqs.web.modules.user.exam.dto.request.UserExamReqDTO;
import com.imhqs.web.modules.user.exam.dto.response.UserExamRespDTO;
import com.imhqs.web.modules.user.exam.entity.UserExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* 考试记录Mapper
*/
@Mapper
public interface UserExamMapper extends BaseMapper<UserExam> {

    /**
     * 我的考试分页
     * @param page
     * @param query
     * @return
     */
    IPage<UserExamRespDTO> paging(Page page, @Param("query") UserExamReqDTO query);
    IPage<UserExamRespDTO> pagingAll(Page page, @Param("query") UserExamReqDTO query);

}
