package com.liangshou.bitojbackendjudgeservice.judge.codesandbox;


import com.liangshou.bitojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.liangshou.bitojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest 执行代码请求
     * @return 执行响应
     */
    ExecuteCodeResponse executeCode (ExecuteCodeRequest executeCodeRequest);
}
