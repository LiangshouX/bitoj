package com.liangshou.bitoj.judge.strategy;

import com.liangshou.bitoj.judge.codesandbox.model.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext 判题上下文
     * @return 判题信息
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
