package com.liangshou.bitojbackendmodel.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
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
    private String tags;

    /**
     * 题目答案
     */
    @TableField(value = "answer")
    private String answer;

    /**
     * 题目提交数
     */
    @TableField(value = "submitNum")
    private Integer submitNum;

    /**
     * 题目通过数
     */
    @TableField(value = "acceptedNum")
    private Integer acceptedNum;

    /**
     * 判题用例（json 数组）
     */
    @TableField(value = "judgeCase")
    private String judgeCase;

    /**
     * 判题配置（json 对象）
     */
    @TableField(value = "judgeConfig")
    private String judgeConfig;

    /**
     * 点赞数
     */
    @TableField(value = "thumbNum")
    private Integer thumbNum;

    /**
     * 收藏数
     */
    @TableField(value = "favourNum")
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "isDelete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}