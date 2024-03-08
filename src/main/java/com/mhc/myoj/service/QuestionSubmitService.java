package com.mhc.myoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mhc.myoj.model.dto.question.QuestionQueryRequest;
import com.mhc.myoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.mhc.myoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.mhc.myoj.model.entity.Question;
import com.mhc.myoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mhc.myoj.model.entity.User;
import com.mhc.myoj.model.vo.QuestionSubmitVO;
import com.mhc.myoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MHC
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-03-07 16:15:18
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交信息
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return 用户Id
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
