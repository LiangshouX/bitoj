package com.liangshou.bitoj.judge;

import com.liangshou.bitoj.judge.strategy.DefaultJudgeStrategy;
import com.liangshou.bitoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.liangshou.bitoj.judge.strategy.JudgeContext;
import com.liangshou.bitoj.judge.strategy.JudgeStrategy;
import com.liangshou.bitoj.judge.codesandbox.model.JudgeInfo;
import com.liangshou.bitoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext 判题上下文对象
     * @return JudgeInfo
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}

