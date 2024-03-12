package com.mhc.myoj.judge.codesandbox;

import com.mhc.myoj.judge.strategy.DefaultJudgeStrategyImpl;
import com.mhc.myoj.judge.strategy.JavaJudgeStrategyImpl;
import com.mhc.myoj.judge.strategy.JudgeContext;
import com.mhc.myoj.judge.strategy.JudgeStrategy;
import com.mhc.myoj.model.dto.questionsubmit.JudgeInfo;
import com.mhc.myoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Component;

/**
 * 根据编程语言选用合适的判题策略
 */
@Component
public class JudgeManager {
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        // 策略模式，为了调用者只需直接调用，不用自己判断语言
        JudgeStrategy judgeStrategy;
        if("java".equals(language)) {
            judgeStrategy = new JavaJudgeStrategyImpl();
        } else {
            judgeStrategy = new DefaultJudgeStrategyImpl();
        }

        return judgeStrategy.doJudge(judgeContext);
    }
}
