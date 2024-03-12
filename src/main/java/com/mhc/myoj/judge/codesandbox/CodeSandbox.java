package com.mhc.myoj.judge.codesandbox;

import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {
    // 根据执行代码请求(code, input..)执行代码并返回结果
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
