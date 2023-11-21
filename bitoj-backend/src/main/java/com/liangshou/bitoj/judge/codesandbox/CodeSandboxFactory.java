package com.liangshou.bitoj.judge.codesandbox;

import com.liangshou.bitoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.liangshou.bitoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.liangshou.bitoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂。根据字符串参数指定的代码沙箱实例
 */
public class CodeSandboxFactory {
    /**
     * 创建沙箱实例
     * @param type 沙箱类型
     * @return 沙箱实例
     */
    public static CodeSandbox newInstance(String type){
        switch (type){
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            case "example":
            default:
                return new ExampleCodeSandbox();
        }
    }
}
