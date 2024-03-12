package com.mhc.myoj.judge.strategy;

import com.mhc.myoj.model.dto.question.JudgeCase;
import com.mhc.myoj.model.dto.questionsubmit.JudgeInfo;
import com.mhc.myoj.model.entity.Question;
import com.mhc.myoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 判题需要的内容
 */
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;
    private List<JudgeCase> judgeCaseList;
    private QuestionSubmit questionSubmit;
    private Question question;
    private List<String> inputList;
    private List<String> outputList;
}
