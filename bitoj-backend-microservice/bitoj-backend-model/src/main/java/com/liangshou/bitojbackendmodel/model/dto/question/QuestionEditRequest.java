package com.liangshou.bitojbackendmodel.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 编辑请求
 */
@Data
public class QuestionEditRequest implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 标签列表（json 数组）
     */
    @TableField(value = "tags")
    private List<String> tags;

    /**
     * 题目答案
     */
    @TableField(value = "answer")
    private String answer;


    /**
     * 判题用例（json 数组）
     */
    @TableField(value = "judgeCase")
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置（json 对象）
     */
    @TableField(value = "judgeConfig")
    private JudgeConfig judgeConfig;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}