package com.mhc.myoj.judge.codesandbox;

import com.mhc.myoj.judge.codesandbox.impl.ExampleCodeSandboxImpl;
import com.mhc.myoj.judge.codesandbox.impl.RemoteCodeSandboxImpl;
import com.mhc.myoj.judge.codesandbox.impl.ThirdPartyCodeSandboxImpl;

/**
 * 代码沙箱静态工厂(根据字符串创建指定沙箱实例)
 */
public class CodeSandboxFactory {
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandboxImpl();
            case "remote":
                return new RemoteCodeSandboxImpl();
            case "thirdParty":
                return new ThirdPartyCodeSandboxImpl();
            default:
                // 默认返回实例代码沙箱
                return new ExampleCodeSandboxImpl();
        }
    }
}
