package com.mhc.myoj.model.dto.questionsubmit;

import com.mhc.myoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 提交查询请求
 *
 * @author <a href="https://github.com/limhc">程序员鱼皮</a>
 * @from <a href="https://mhc.icu">编程导航知识星球</a>
 */
@Data
// 当@Data注解在派生子类User上时，默认等同于@EqualsAndHashCode(callSuper = false)
// 这就相当于重写 equals 和 hashcode 方法时不考虑父类的属性
@EqualsAndHashCode(callSuper = true) // 我们需要考虑父类的属性，因此加这个
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 提交状态
     */
    private Integer status;


    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}