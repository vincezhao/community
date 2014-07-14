package com.ahpu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahpu.bean.Question;
import com.ahpu.intf.IQuestion;

@Service
public class QuestionService implements IQuestion {
    
    @Autowired
    SqlSession sqlSession;

    public void createQuestion(Question question) {
        if(question != null){
            sqlSession.insert("COMMUNITY_QUESTION.INSERT", question);
        }
    }

    public Question getById(Long id) {
        if(id > 0){
            return sqlSession.selectOne("COMMUNITY_QUESTION.SELECTBYID", id);
        }
        return null;
    }

    public List<Question> queryByAuthor(Long authorId) {
        if(authorId > 0){
            return sqlSession.selectList("COMMUNITY_QUESTION.SELECTBYAUTHORID", authorId);
        }
        return null;
    }

    public List<Question> queryByAnswerId(Long answerId) {
        if(answerId > 0){
            return sqlSession.selectList("COMMUNITY_QUESTION.SELECTBYANSWERID", answerId);
        }
        return null;
    }

    public List<Question> queryByAuthorName(String authorName) {
        if(StringUtils.isNotBlank(authorName)){
            return sqlSession.selectList("COMMUNITY_QUESTION.SELECTBYAUTHORNAME", authorName);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return sqlSession.selectOne("COMMUNITY_QUESTION.COUNTALL");
    }

    @Override
    public Long countFinished() {
        return sqlSession.selectOne("COMMUNITY_QUESTION.COUNTFINISHED");
    }

    @Override
    public List<Question> queryByAll(Integer startIndex, Integer length) {
        Map<String,Integer> param = new HashMap<String,Integer>();
        param.put("startIndex", startIndex);
        param.put("length", length);
        return sqlSession.selectList("COMMUNITY_QUESTION.SELECTALL", param);
    }

}
