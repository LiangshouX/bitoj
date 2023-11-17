package com.liangshou.bitoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangshou.bitoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.liangshou.bitoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.liangshou.bitoj.model.entity.QuestionSubmit;
import com.liangshou.bitoj.model.entity.User;
import com.liangshou.bitoj.model.vo.QuestionSubmitVO;
import com.liangshou.bitoj.service.QuestionSubmitService;
import com.liangshou.bitoj.mapper.QuestionSubmitMapper;
import org.springframework.stereotype.Service;

/**
* @author X-L-S
* {@code @description} 针对表【question_submit(题目提交)】的数据库操作Service实现
* {@code @createDate} 2023-11-17 15:42:43
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{

    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        return 0;
    }

    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        return null;
    }

    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        return null;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        return null;
    }
}




