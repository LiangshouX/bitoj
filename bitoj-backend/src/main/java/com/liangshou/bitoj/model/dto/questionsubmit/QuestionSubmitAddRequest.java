package com.liangshou.bitoj.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    @TableField(value = "language")
    private String language;

    /**
     * 用户代码
     */
    @TableField(value = "code")
    private String code;


    /**
     * 题目 id
     */
    @TableField(value = "questionId")
    private Long questionId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
