package com.liangshou.bitoj.service;

import com.liangshou.bitoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.liangshou.bitoj.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionSubmitServiceTest {

    @Resource
    QuestionSubmitService questionSubmitService;

    @Test
    void doQuestionSubmit() throws ParseException {
        QuestionSubmitAddRequest questionSubmitAddRequest = new QuestionSubmitAddRequest();
        questionSubmitAddRequest.setLanguage("java");
        questionSubmitAddRequest.setCode("嘻嘻嘻，没有代码哦~~~");
        questionSubmitAddRequest.setQuestionId(1L);

        System.out.println(questionSubmitAddRequest.getCode());

        User loginUser = new User();
        loginUser.setId(1763927263604195329L);
        loginUser.setUserAccount("testUser4");
        loginUser.setUserName("测试用户4（管理员）");
        loginUser.setUserRole("admin");
        loginUser.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                parse("2024-03-02 22:00:06"));
        loginUser.setUpdateTime(new SimpleDateFormat("yy-MM-dd HH:mm:ss").
                parse("2024-03-03 13:29:30"));
        loginUser.setIsDelete(0);

        questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);

    }
}