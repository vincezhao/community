package com.ahpu.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahpu.bean.Answer;
import com.ahpu.intf.IAnswer;

@Service
public class AnswerService implements IAnswer {
    
    @Autowired
    SqlSession sqlSession;

    public void createAnswer(Answer answer) {
        if(answer != null){
            sqlSession.insert("COMMUNITY_ANSWER.INSERT", answer);
        }
    }

    public List<Answer> queryByAuthor(String name) {
        if(StringUtils.isNotBlank(name)){
            return sqlSession.selectList("COMMUNITY_ANSWER.SELECTBYNAME", name);
        }
        return null;
    }

    public List<Answer> queryByQuestion(Long questionId) {
        if(questionId > 0){
            return sqlSession.selectList("COMMUNITY_ANSWER.SELECTBYQUESTIONID", questionId);
        }
        return null;
    }

    public Integer addReadCount(Long answerId, Integer delta) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer addPriaseCount(Long answerId, Integer delta) {
        // TODO Auto-generated method stub
        return null;
    }

}
