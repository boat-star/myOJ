package com.mhc.myoj.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mhc.myoj.common.ErrorCode;
import com.mhc.myoj.exception.BusinessException;
import com.mhc.myoj.judge.codesandbox.CodeSandbox;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱(实际调用接口的沙箱)
 */
public class RemoteCodeSandboxImpl implements CodeSandbox {
    // 约定请求头
    private static final String AUTH_REQUEST_HEADER = "auth";
    private static final String AUTH_REQUEST_SECRET = "secretKey";
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8080/codeSandbox";
        String requestBody = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(requestBody)
                .execute() // 这里的test是在windows下执行的，所以服务器不能在linux环境下执行，否则会拒绝来连接
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "执行远程代码沙箱错误:" + responseStr);
        }
//        String url = "http://localhost:8080/health";
//        String body = HttpUtil.createGet(url).execute().body();
//        System.out.println(body);
//        String responseStr = null;
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
