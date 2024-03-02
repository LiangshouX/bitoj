package com.liangshou.bitoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liangshou.bitoj.model.dto.question.JudgeConfig;
import com.liangshou.bitoj.model.entity.Post;
import com.liangshou.bitoj.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目封装类，专门返回给前端的
 */
@Data
public class QuestionVO implements Serializable {

    private final static Gson GSON = new Gson();

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 标签列表（json 数组）
     */
    @TableField(value = "tags")
    private List<String> tags;

    /**
     * 题目提交数
     */
    @TableField(value = "submitNum")
    private Integer submitNum;

    /**
     * 题目通过数
     */
    @TableField(value = "acceptedNum")
    private Integer acceptedNum;

    /**
     * 判题用例（json 数组）
     */
    @TableField(value = "judgeCase")
    private String judgeCase;

    /**
     * 判题配置（json 对象）
     */
    @TableField(value = "judgeConfig")
    private JudgeConfig judgeConfig;

    /**
     * 点赞数
     */
    @TableField(value = "thumbNum")
    private Integer thumbNum;

    /**
     * 收藏数
     */
    @TableField(value = "favourNum")
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "isDelete")
    private Integer isDelete;

    /**
     * 创建题目人的信息
     */
    private UserVO userVO;


    /**
     * 包装类转对象
     *
     * @param questionVO Question包装类
     * @return Question对象
     */
    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }

        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);
        List<String> tagList = questionVO.getTags();

        if(tagList != null){
            question.setTags(JSONUtil.toJsonStr(tagList));
        }

        JudgeConfig voJudgeConfig = questionVO.getJudgeConfig();

        if(voJudgeConfig != null){
            question.setJudgeConfig(JSONUtil.toJsonStr(voJudgeConfig));
        }

        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question Question对象
     * @return QuestionVO包装类
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }

        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        List<String> tagList = JSONUtil.toList(question.getTags(), String.class);
        questionVO.setTags(tagList);

        String judgeConfigStr = question.getJudgeConfig();
        questionVO.setJudgeConfig(JSONUtil.toBean(judgeConfigStr, JudgeConfig.class));

        return questionVO;
    }

    private static final long serialVersionUID = 1L;
}
