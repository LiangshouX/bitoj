package com.liangshou.bitoj.judge.codesandbox;

import com.liangshou.bitoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.liangshou.bitoj.judge.codesandbox.model.ExecuteCodeResponse;

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
