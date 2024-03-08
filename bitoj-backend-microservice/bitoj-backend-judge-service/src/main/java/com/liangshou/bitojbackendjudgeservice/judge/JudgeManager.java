package com.liangshou.bitojbackendjudgeservice.judge;


import com.liangshou.bitojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.liangshou.bitojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.liangshou.bitojbackendjudgeservice.judge.strategy.JudgeContext;
import com.liangshou.bitojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.liangshou.bitojbackendmodel.model.codesandbox.JudgeInfo;
import com.liangshou.bitojbackendmodel.model.entity.QuestionSubmit;
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

