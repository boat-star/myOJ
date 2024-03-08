package com.mhc.myoj.service;

import com.mhc.myoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.mhc.myoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mhc.myoj.model.entity.User;

/**
* @author MHC
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-03-07 16:15:18
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交信息
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return 用户Id
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
