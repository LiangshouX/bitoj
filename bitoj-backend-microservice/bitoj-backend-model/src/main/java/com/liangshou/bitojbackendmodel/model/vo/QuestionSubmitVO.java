package com.liangshou.bitojbackendmodel.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.gson.Gson;

import com.liangshou.bitojbackendmodel.model.codesandbox.JudgeInfo;
import com.liangshou.bitojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目提交封装类
 */
@Data
public class QuestionSubmitVO implements Serializable {

    private final static Gson GSON = new Gson();

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编程语言
     */
    @TableField(value = "language")
    private String language;

    /**
     * 用户代码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 判题信息（json 对象）
     */
    @TableField(value = "judgeInfo")
    private JudgeInfo judgeInfo;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 题目 id
     */
    @TableField(value = "questionId")
    private Long questionId;

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
     * 提交用户信息
     */
    private UserVO userVO;

    /**
     * 对应的题目信息
     */
    private QuestionVO questionVO;


    /**
     * 包装类转对象
     *
     * @param questionSubmitVO QuestionSubmit包装类
     * @return QuestionSubmit对象
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }

        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        JudgeInfo judgeInfo1Obj = questionSubmitVO.getJudgeInfo();
        if (judgeInfo1Obj != null){
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo1Obj));
        }

        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param questionSubmit QuestionSubmit对象
     * @return QuestionSubmitVO包装类
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }

        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        String judgeInfoStr = questionSubmit.getJudgeInfo();
        questionSubmitVO.setJudgeInfo(JSONUtil.toBean(judgeInfoStr, JudgeInfo.class));

        return questionSubmitVO;
    }

    private static final long serialVersionUID = 1L;
}
