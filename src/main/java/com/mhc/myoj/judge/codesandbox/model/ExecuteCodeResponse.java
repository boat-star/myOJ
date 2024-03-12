package com.mhc.myoj.judge.codesandbox.model;

import com.mhc.myoj.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder // 自动生成构建者模式相关的代码
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    private List<String> outputList;
    /**
     * 接口信息
     */
    private String message;
    /**
     * 执行状态
     */
    private Integer status;
    /**
     * 判题信息(消耗时间，内存，程序执行信息)
     */
    private JudgeInfo judgeInfo;
}
