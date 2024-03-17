package com.mhc.myoj.judge;

import cn.hutool.json.JSONUtil;
import com.mhc.myoj.common.ErrorCode;
import com.mhc.myoj.exception.BusinessException;
import com.mhc.myoj.judge.codesandbox.CodeSandbox;
import com.mhc.myoj.judge.codesandbox.CodeSandboxFactory;
import com.mhc.myoj.judge.codesandbox.CodeSandboxProxy;
import com.mhc.myoj.judge.codesandbox.JudgeManager;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.mhc.myoj.judge.strategy.JudgeContext;
import com.mhc.myoj.model.dto.question.JudgeCase;
import com.mhc.myoj.model.dto.questionsubmit.JudgeInfo;
import com.mhc.myoj.model.entity.Question;
import com.mhc.myoj.model.entity.QuestionSubmit;
import com.mhc.myoj.model.enums.QuestionSubmitStatusEnum;
import com.mhc.myoj.service.QuestionService;
import com.mhc.myoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JudgeServiceImpl implements JudgeService {
    // 沙箱类型
    @Value("${codesandbox.type}")
    private String type;
    @Resource
    private QuestionSubmitService questionSubmitService;
    @Resource
    private QuestionService questionService;
    @Resource
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 1）传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        // 提交不存在
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        // 题目不存在
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (questionId == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        Integer status = questionSubmit.getStatus();

        // 2）如果题目提交状态不为等待中，就不用重复执行了
        if (!status.equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }

        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行，也能让用户即时看到状态
        // 这里需要调用 service 层的方法来更改数据库中的信息
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        // 4）调用沙箱，获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type); // 静态工厂模式
        // 执行代理类
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        // 从判题用例中获取输入用例
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).toList();
        // 建造者模式，通过链式调用传参构造
        ExecuteCodeRequest codeRequest = ExecuteCodeRequest.builder().code(code).language(language).inputList(inputList).build();
        ExecuteCodeResponse codeResponse = codeSandbox.executeCode(codeRequest);

        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(codeResponse.getJudgeInfo()); // 沙箱传递的是 judgeInfo的时间和内存
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(codeResponse.getOutputList());
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext); // 策略模式

        // 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue()); // 这里是判题状态的成功，不是题目通过
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo)); // 修改判题信息
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        // 返回数据库中的最新状态
        return questionSubmitService.getById(questionSubmitId);
    }
}
