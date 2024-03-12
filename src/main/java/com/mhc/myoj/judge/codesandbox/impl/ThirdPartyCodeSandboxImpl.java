package com.mhc.myoj.judge.codesandbox.impl;

import com.mhc.myoj.judge.codesandbox.CodeSandbox;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱(调用现成的沙箱)
 */
public class ThirdPartyCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
