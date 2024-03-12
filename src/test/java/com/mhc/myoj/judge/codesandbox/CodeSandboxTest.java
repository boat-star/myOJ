package com.mhc.myoj.judge.codesandbox;

import com.mhc.myoj.judge.codesandbox.impl.ExampleCodeSandboxImpl;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mhc.myoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.mhc.myoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


@SpringBootTest
class CodeSandboxTest {
    @Value("${codesandbox.type}")
    private String type;

    @Test
    public void testSandbox() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        String code = "int main() { }";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> list = Arrays.asList("1 2", "3 4");
        // 建造者模式，通过链式调用传参构造
        ExecuteCodeRequest codeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(list)
                .build();
        ExecuteCodeResponse codeResponse = codeSandbox.executeCode(codeRequest);
        // codeResponse 为 null 则报错
        // Assertions.assertNotNull(codeResponse);
    }

    @Test
    public void testSandboxProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        // 变成代理类
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = "int main() { }";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> list = Arrays.asList("1 2", "3 4");
        // 建造者模式，通过链式调用传参构造
        ExecuteCodeRequest codeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(list)
                .build();
        ExecuteCodeResponse codeResponse = codeSandbox.executeCode(codeRequest);
    }
}