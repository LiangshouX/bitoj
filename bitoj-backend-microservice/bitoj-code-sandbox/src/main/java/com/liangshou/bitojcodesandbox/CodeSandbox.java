package com.liangshou.bitojcodesandbox;


import com.liangshou.bitojcodesandbox.model.ExecuteCodeRequest;
import com.liangshou.bitojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
