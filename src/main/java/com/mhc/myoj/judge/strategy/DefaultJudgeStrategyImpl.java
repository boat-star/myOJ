package com.mhc.myoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.mhc.myoj.model.dto.question.JudgeCase;
import com.mhc.myoj.model.dto.question.JudgeConfig;
import com.mhc.myoj.model.dto.questionsubmit.JudgeInfo;
import com.mhc.myoj.model.entity.Question;
import com.mhc.myoj.model.enums.QuestionSubmitJudgeInfoEnum;

import java.util.List;

/**
 * 执行默认判题策略
 */
public class DefaultJudgeStrategyImpl implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        // 题目用例列表
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();

        Question question = judgeContext.getQuestion();
        List<String> inputList = judgeContext.getInputList();

        // 沙箱执行结果
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> outputList = judgeContext.getOutputList();
        Long time = judgeInfo.getTime();
        Long memory = judgeInfo.getMemory();

        QuestionSubmitJudgeInfoEnum info = QuestionSubmitJudgeInfoEnum.WRONG_ANSWER;

        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);

        if (outputList.size() != inputList.size()) {
            judgeInfoResponse.setMessage(info.getValue());
            return judgeInfoResponse;
        }
        // 比较代码执行结果和测试用例的输出是否相等
        for (int i = 0; i < outputList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoResponse.setMessage(info.getValue());
                return judgeInfoResponse;
            }
        }

        // 判断题目时间空间限制
        // 题目要求限制
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long timeLimit = judgeConfig.getTimeLimit();
        Long memoryLimit = judgeConfig.getMemoryLimit();
        if (time > timeLimit) {
            info = QuestionSubmitJudgeInfoEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(info.getValue());
            return judgeInfoResponse;
        }
        if (memory > memoryLimit) {
            info = QuestionSubmitJudgeInfoEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(info.getValue());
            return judgeInfoResponse;
        }

        // 否则，执行成功
        info = QuestionSubmitJudgeInfoEnum.ACCEPTED;
        judgeInfoResponse.setMessage(info.getValue());
        return judgeInfoResponse;
    }
}
