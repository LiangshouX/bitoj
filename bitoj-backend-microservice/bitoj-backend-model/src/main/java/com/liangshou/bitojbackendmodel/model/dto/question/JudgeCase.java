package com.liangshou.bitojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * 题目用例，包括输入用例和输出用例
 *
 * @author X-L-S
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
