package com.ahpu.intf;

import java.util.List;

import com.ahpu.bean.Answer;

/*
 * 回答操作API
 */
public interface IAnswer {
    
    public void createAnswer(Answer answer);
    
    public List<Answer> queryByAuthor(String name);
    
    public List<Answer> queryByQuestion(Long questionId);
    
    public Integer addReadCount(Long answerId, Integer delta);
    
    public Integer addPriaseCount(Long answerId, Integer delta);
    
}
