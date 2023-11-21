package com.liangshou.bitoj.judge.codesandbox.impl;

import com.liangshou.bitoj.judge.codesandbox.CodeSandbox;
import com.liangshou.bitoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.liangshou.bitoj.judge.codesandbox.model.ExecuteCodeResponse;

public class ThirdPartyCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("执行 ThirdParty 代码沙箱");
        return null;
    }
}
