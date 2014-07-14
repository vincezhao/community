package com.ahpu.intf;

import java.util.List;

import com.ahpu.bean.Question;

/*
 * 提问操作API
 */
public interface IQuestion {
    
    public void createQuestion(Question question);
    
    public Question getById(Long id);
    
    public List<Question> queryByAuthor(Long authorId);
    
    public List<Question> queryByAnswerId(Long answerId);
    
    public List<Question> queryByAuthorName(String authorName);
    
    public List<Question> queryByAll(Integer startIndex, Integer length);
    
    public Long countAll();
    
    public Long countFinished();
    
}
