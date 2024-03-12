package com.mhc.myoj.judge.codesandbox.impl;

import com.mhc.myoj.judge.codesandbox.CodeSandbox;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.mhc.myoj.model.dto.questionsubmit.JudgeInfo;
import com.mhc.myoj.model.enums.QuestionSubmitJudgeInfoEnum;
import com.mhc.myoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 实例代码沙箱(仅为跑通业务流程)
 */
public class ExampleCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse codeResponse = new ExecuteCodeResponse();
        codeResponse.setOutputList(inputList);
        codeResponse.setMessage("测试执行成功");
        codeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(QuestionSubmitJudgeInfoEnum.ACCEPTED.getValue());
        judgeInfo.setTime(1000L);
        judgeInfo.setMemory(1000L);

        codeResponse.setJudgeInfo(judgeInfo);
        return codeResponse;
    }
}
