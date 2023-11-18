package com.liangshou.bitoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liangshou.bitoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.liangshou.bitoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.liangshou.bitoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liangshou.bitoj.model.entity.User;
import com.liangshou.bitoj.model.vo.QuestionSubmitVO;

/**
* @author X-L-S
* {@code @description} 针对表【question_submit(题目提交)】的数据库操作Service
* {@code @createDate} 2023-11-17 15:42:43
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser 登录的用户
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询包装类 （用户根据哪些字段查询；根据前端传来的请求对象，得到 MyBatis 框架支持的查询 QueryWrapper 类）
     *
     * @param questionSubmitQueryRequest 查询请求
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装类
     *
     * @param questionSubmit 提交实体
     * @param loginUser 登录的用户
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage 提交页
     * @param loginUser 登录用户
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
