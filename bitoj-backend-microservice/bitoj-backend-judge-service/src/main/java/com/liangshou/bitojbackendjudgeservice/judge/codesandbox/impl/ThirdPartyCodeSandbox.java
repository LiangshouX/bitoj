package com.liangshou.bitojbackendjudgeservice.judge.codesandbox.impl;


import com.liangshou.bitojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.liangshou.bitojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.liangshou.bitojbackendmodel.model.codesandbox.ExecuteCodeResponse;

public class ThirdPartyCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("执行 ThirdParty 代码沙箱");
        return null;
    }
}
