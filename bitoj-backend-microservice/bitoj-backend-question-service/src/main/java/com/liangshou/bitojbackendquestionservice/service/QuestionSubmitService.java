package com.liangshou.bitojbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liangshou.bitojbackendmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.liangshou.bitojbackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.liangshou.bitojbackendmodel.model.entity.QuestionSubmit;
import com.liangshou.bitojbackendmodel.model.entity.User;
import com.liangshou.bitojbackendmodel.model.vo.QuestionSubmitVO;

/**
* @author X-L-S
* {@code @description} 针对表【question_submit(题目提交)】的数据库操作Service
* {@code @createDate} 2023-11-17 15:42:43
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交题目
     * @param questionSubmitAddRequest 题目提交请求
     * @param loginUser 当前登录用户
     * @return 提交ID
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取题目封装
     *
     * @param questionSubmit 提交题目
     * @param loginUser 当前登录用户
     * @return 封装
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 获取查询条件
     * @param questionSubmitQueryRequest 提交题目查询请求
     * @return 查询封装
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 分页获取题目封装
     * @param questionSubmitPage 提交页
     * @param loginUser 当前登录用户
     * @return 分页封装
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
