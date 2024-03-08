package com.liangshou.bitojbackendquestionservice.controller;

import com.google.gson.Gson;
import com.liangshou.bitojbackendcommon.common.BaseResponse;
import com.liangshou.bitojbackendcommon.common.DeleteRequest;
import com.liangshou.bitojbackendcommon.common.ErrorCode;
import com.liangshou.bitojbackendcommon.common.ResultUtils;
import com.liangshou.bitojbackendcommon.exception.BusinessException;
import com.liangshou.bitojbackendcommon.exception.ThrowUtils;
import com.liangshou.bitojbackendmodel.model.dto.question.JudgeCase;
import com.liangshou.bitojbackendmodel.model.dto.question.JudgeConfig;
import com.liangshou.bitojbackendmodel.model.dto.question.QuestionAddRequest;
import com.liangshou.bitojbackendmodel.model.entity.Question;
import com.liangshou.bitojbackendmodel.model.entity.User;
import com.liangshou.bitojbackendquestionservice.service.QuestionService;
import com.liangshou.bitojbackendquestionservice.service.QuestionSubmitService;
import com.liangshou.bitojbackendserviceclient.service.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 题目 Controller 接口
 * 定义题目的添加、删除、修改（更新）、获取（按ID获取、分页获取等）、提交等方法
 */

@RestController
@RequestMapping("/")
@Slf4j
public class QuestionController {
    @Resource
    QuestionService questionService;

    @Resource
    UserFeignClient userFeignClient;

    @Resource
    QuestionSubmitService questionSubmitService;

    private final static Gson GSON = new Gson();

    /******* 题目增删改查 ********/

    @PostMapping("/add")
    public BaseResponse<Long> addQuestion(@RequestBody QuestionAddRequest questionAddRequest, HttpServletRequest request){
        if(questionAddRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "添加请求为空");
        }

        Question question = new Question();
        BeanUtils.copyProperties(questionAddRequest, question);
        List<String> tags = questionAddRequest.getTags();
        if(tags != null){
            question.setTags(GSON.toJson(tags));
        }

        List<JudgeCase> judgeCases = questionAddRequest.getJudgeCase();
        if(judgeCases != null){
            question.setJudgeCase(GSON.toJson(judgeCases));
        }

        JudgeConfig judgeConfig = questionAddRequest.getJudgeConfig();
        if(judgeConfig != null){
            question.setJudgeConfig(GSON.toJson(judgeConfig));
        }

        questionService.validQuestion(question, true);
        User loginUser = userFeignClient.getLoginUser(request);

        question.setThumbNum(0);
        question.setFavourNum(0);
        question.setUserId(loginUser.getId());

        boolean result = questionService.save(question);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newQuestionId = question.getId();

        return ResultUtils.success(newQuestionId);
    }

    /**
     * 删除题目
     * @param deleteRequest 删除请求
     * @param request HTTP请求
     * @return 是否成功删除
     */
    public BaseResponse<Boolean> deleteQuestion(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request){
        if(deleteRequest == null || deleteRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取当前登录的用户，仅管理员和创建题目的用户可删除
        User user = userFeignClient.getLoginUser(request);
    }

    /******* 增删改查 ********/
}

















