package com.liangshou.bitojbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liangshou.bitojbackendmodel.model.dto.question.QuestionQueryRequest;
import com.liangshou.bitojbackendmodel.model.entity.Question;
import com.liangshou.bitojbackendmodel.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author X-L-S
* {@code @description} 针对表【question(题目)】的数据库操作Service
* {@code @createDate} 2023-11-17 15:42:43
*/
public interface QuestionService extends IService<Question> {

    /**
     * 对题目进行校验
     *
     * @param question 待校验的题目
     * @param add 是否为新增题目
     */
    void validQuestion(Question question, boolean add);

    /**
     * 题目查询封装
     *  QueryWrapper是MyBatis-Plus中的一个查询构造器，用于**构建查询条件**。
     *        它提供了一种方便的方式来构建SQL查询语句，使得代码更加简洁和易于维护。
     *
     * @param questionQueryRequest 查询请求
     * @return QueryWrapper
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);


    /**
     * 获取题目的封装对象，根据 Question 对象获取到一个传递给前端的VO封装对象
     *
     * @param question 题目对象
     * @param request 网页请求
     * @return QuestionVO
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);


    /**
     * 分页获取题目封装对象，根据分页题目对象，获取传递给前端的分页的题目封装对象
     * @param questionPage 题目对象分页
     * @param request 网络请求
     * @return Page<QuestionVO>
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);

}
