package com.liangshou.bitojbackendjudgeservice.judge.codesandbox.impl;


import com.liangshou.bitojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.liangshou.bitojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.liangshou.bitojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.liangshou.bitojbackendmodel.model.codesandbox.JudgeInfo;
import com.liangshou.bitojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.liangshou.bitojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 示例代码沙箱，仅为了跑通业务流程
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("执行示例代码沙箱");
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(1600L);

        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
