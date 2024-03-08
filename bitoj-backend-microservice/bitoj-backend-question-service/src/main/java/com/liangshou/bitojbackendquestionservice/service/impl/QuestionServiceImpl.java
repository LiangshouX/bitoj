package com.liangshou.bitojbackendquestionservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangshou.bitojbackendcommon.common.ErrorCode;
import com.liangshou.bitojbackendcommon.constant.CommonConstant;
import com.liangshou.bitojbackendcommon.exception.BusinessException;
import com.liangshou.bitojbackendcommon.exception.ThrowUtils;
import com.liangshou.bitojbackendcommon.utils.SqlUtils;
import com.liangshou.bitojbackendmodel.model.dto.question.QuestionQueryRequest;
import com.liangshou.bitojbackendmodel.model.entity.Question;
import com.liangshou.bitojbackendmodel.model.entity.User;
import com.liangshou.bitojbackendmodel.model.vo.QuestionVO;
import com.liangshou.bitojbackendmodel.model.vo.UserVO;
import com.liangshou.bitojbackendquestionservice.mapper.QuestionMapper;
import com.liangshou.bitojbackendquestionservice.service.QuestionService;
import com.liangshou.bitojbackendserviceclient.service.UserFeignClient;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {

    @Resource
    private UserFeignClient userFeignClient;

    /**
     * 校验题目是否合法
     * @param question 待校验的题目
     * @param add 是否为新增题目
     */
    @Override
    public void validQuestion(Question question, boolean add) {
        if(question == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }

        String title = question.getTitle();
        String content = question.getContent();
        String tags = question.getTags();
        String answer = question.getAnswer();
        String judgeCase = question.getJudgeCase();
        String judgeConfig = question.getJudgeConfig();


        // 创建题目时，标题、内容、标签 不能为空
        if(add){
            ThrowUtils.throwIf(StringUtils.isAnyBlank(title, content, tags), ErrorCode.OPERATION_ERROR);
        }

        // 有参数则校验
        if(StringUtils.isNotBlank(title) && title.length() > 80){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长（80以内）");
        }
        if(StringUtils.isNotBlank(content) && content.length() > 8192){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目内容过长（8192以内）");
        }
        if (StringUtils.isNotBlank(answer) && answer.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "答案过长");
        }
        if (StringUtils.isNotBlank(judgeCase) && judgeCase.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "判题用例过长");
        }
        if (StringUtils.isNotBlank(judgeConfig) && judgeConfig.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "判题配置过长");
        }
    }

    /**
     * 用户根据哪些字段查询，根据前端传来的请求对象，得到 mybatis 框架支持的查询 QueryWrapper 类
     * @param questionQueryRequest 查询请求
     * @return QueryWrapper
     */
    @Override
    public QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if(questionQueryRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "查询参数为空");
        }

        Long id = questionQueryRequest.getId();
        String title = questionQueryRequest.getTitle();
        String content = questionQueryRequest.getContent();
        List<String> tags = questionQueryRequest.getTags();
        String answer = questionQueryRequest.getAnswer();
        Long userId = questionQueryRequest.getUserId();
        String sortField = questionQueryRequest.getSortField();
        String sortOrder = questionQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
        queryWrapper.like(StringUtils.isNotBlank(answer), "answer", answer);

        if(CollectionUtils.isNotEmpty(tags)){
            for(String tag:tags){
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }

        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);

        return queryWrapper;
    }

    /**
     *
     * @param question 题目对象
     * @param request 网页请求
     * @return questionVO
     */
    @Override
    public QuestionVO getQuestionVO(Question question, HttpServletRequest request) {
        QuestionVO questionVO = new QuestionVO();
        // 与查询用户信息相关联
        Long userId = questionVO.getUserId();
        User user = null;

        if(userId != null && userId > 0){
            user = userFeignClient.getById(userId);
        }

        UserVO userVO = userFeignClient.getUserVO(user);
        questionVO.setUserVO(userVO);

        return questionVO;
    }

    /**
     *
     * @param questionPage 题目对象分页
     * @param request 网络请求
     * @return questionVOPage
     */
    @Override
    public Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request) {
        List<Question> questionList = questionPage.getRecords();
        Page<QuestionVO> questionVOPage = new Page<>(questionPage.getCurrent(), questionPage.getSize(), questionPage.getTotal());
        if(CollectionUtils.isEmpty(questionList)){
            return questionVOPage;
        }

        // 1. 关联查询用户
        Set<Long> userIdSet = questionList.stream().map(Question::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userFeignClient.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));

        // 2. 填充信息
        List<QuestionVO> questionVOList = questionList.stream().map(question -> {
            QuestionVO questionVO = QuestionVO.objToVo(question);
            Long userId = question.getUserId();
            User user = null;
            if(userIdUserListMap.containsKey(userId)){
                user = userIdUserListMap.get(userId).get(0);
            }
            questionVO.setUserVO(userFeignClient.getUserVO(user));

            return questionVO;
        }).collect(Collectors.toList());
        return questionVOPage;
    }

}
