package com.mhc.myoj.judge.codesandbox.impl;

import com.mhc.myoj.judge.codesandbox.CodeSandbox;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱(实际调用接口的沙箱)
 */
public class RemoteCodeSandboxImpl implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
