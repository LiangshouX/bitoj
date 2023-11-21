package com.liangshou.bitoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangshou.bitoj.common.ErrorCode;
import com.liangshou.bitoj.constant.CommonConstant;
import com.liangshou.bitoj.exception.BusinessException;
import com.liangshou.bitoj.judge.JudgeService;
import com.liangshou.bitoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.liangshou.bitoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.liangshou.bitoj.model.entity.Question;
import com.liangshou.bitoj.model.entity.QuestionSubmit;
import com.liangshou.bitoj.model.entity.User;
import com.liangshou.bitoj.model.enums.QuestionSubmitLanguageEnum;
import com.liangshou.bitoj.model.enums.QuestionSubmitStatusEnum;
import com.liangshou.bitoj.model.vo.QuestionSubmitVO;
import com.liangshou.bitoj.service.QuestionService;
import com.liangshou.bitoj.service.QuestionSubmitService;
import com.liangshou.bitoj.mapper.QuestionSubmitMapper;
import com.liangshou.bitoj.service.UserService;
import com.liangshou.bitoj.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
* @author X-L-S
* {@code @description} 针对表【question_submit(题目提交)】的数据库操作Service实现
* {@code @createDate} 2023-11-17 15:42:43
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{

    @Resource
    QuestionService questionService;

    @Resource
    UserService userService;

    @Resource
    @Lazy
    private JudgeService judgeService;

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交请求
     * @param loginUser 登录的用户
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 校验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if(languageEnum == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言类型错误");
        }
        long questionId = questionSubmitAddRequest.getQuestionId();

        // 判断实体是否存在，根据 id 获取实体
        Question question = questionService.getById(questionId);
        if(question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 每个用户串行提交题目
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setCode(questionSubmit.getCode());
        questionSubmit.setLanguage(language);
        questionSubmit.setQuestionId(questionId);

        // 设置初始状态
        questionSubmit.setJudgeInfo("{}");
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());

        boolean save = this.save(questionSubmit);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入失败！");
        }
        Long questionSubmitId = questionSubmit.getId();
        // 执行判题服务
        CompletableFuture.runAsync(() -> {
            judgeService.doJudge(questionSubmitId);
        });
        return questionSubmitId;

    }

    /**
     * 获取查询包装类 （用户根据哪些字段查询；根据前端传来的请求对象，得到 MyBatis 框架支持的查询 QueryWrapper 类）
     *
     * @param questionSubmitQueryRequest 查询请求
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if(questionSubmitQueryRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.eq(StringUtils.isNotBlank(language), "language", language);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);

        return queryWrapper;
    }

    /**
     * 获取题目封装类
     *
     * @param questionSubmit 提交实体
     * @param loginUser 登录的用户
     */
    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        // 脱敏：仅本人和管理员能看见自己提交的代码
        long userId = loginUser.getId();
        // 脱敏处理
        if(userId != questionSubmit.getUserId() && !userService.isAdmin(loginUser)){
            questionSubmitVO.setCode(null);
        }
        return questionSubmitVO;
    }

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage 提交页
     * @param loginUser 登录用户
     */
    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(),
                questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if(CollectionUtils.isEmpty(questionSubmitList)){
            return questionSubmitVOPage;
        }
        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream()
                .map(questionSubmit -> getQuestionSubmitVO(questionSubmit, loginUser))
                .collect(Collectors.toList());
        questionSubmitVOPage.setRecords(questionSubmitVOList);
        return questionSubmitVOPage;
    }
}




