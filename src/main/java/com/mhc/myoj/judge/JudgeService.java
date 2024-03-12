package com.mhc.myoj.judge;

import com.mhc.myoj.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit doJudge(long questionSubmitId);
}
