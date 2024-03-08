package com.mhc.myoj.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 提交问题请求
 *
 * @author <a href="https://github.com/limhc">程序员鱼皮</a>
 * @from <a href="https://mhc.icu">编程导航知识星球</a>
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;


    /**
     * 题目 id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}