package com.liangshou.bitojbackendmodel.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.TableField;
import com.liangshou.bitojbackendcommon.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 编程语言
     */
    @TableField(value = "language")
    private String language;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 题目 id
     */
    @TableField(value = "questionId")
    private Long questionId;

    /**
     * 创建用户 id
     */
    @TableField(value = "userId")
    private Long userId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
