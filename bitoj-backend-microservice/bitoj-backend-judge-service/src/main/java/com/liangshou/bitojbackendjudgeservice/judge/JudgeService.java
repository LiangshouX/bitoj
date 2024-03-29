package com.liangshou.bitojbackendjudgeservice.judge;

import com.liangshou.bitojbackendmodel.model.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId 提交题目的 id
     * @return QuestionSubmit
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
