package com.liangshou.bitoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liangshou.bitoj.model.entity.Question;
import com.liangshou.bitoj.service.QuestionService;
import com.liangshou.bitoj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author X-L-S
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2023-11-17 15:42:43
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




