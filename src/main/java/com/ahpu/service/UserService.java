package com.ahpu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahpu.bean.User;
import com.ahpu.intf.IUser;
/*
 * 用户信息业务实现
 */
@Service
public class UserService implements IUser {
    
    /*
     * 基于MyBatis的DAO
     */
    @Autowired
    SqlSession sqlSession;

    public void createUser(User user) {
        if(user != null){
            sqlSession.insert("COMMUNITY_USER.INSERT", user);
        }
    }

    public User queryByName(String username) {
        if(StringUtils.isNotBlank(username)){
            return sqlSession.selectOne("COMMUNITY_USER.SELECT", username);
        }
        return null;
    }

    public List<User> queryByEnable(int enable) {
        if(StringUtils.isNotBlank(String.valueOf(enable))){
            return sqlSession.selectList("COMMUNITY_USER.SELECTBYENABLE", String.valueOf(enable));
        }
        return null;
    }

    public void updatePwdByName(User user) {
        if(user != null){
            sqlSession.update("COMMUNITY_USER.UPDATEPWD", user);
        }
    }

    public void updateEnableByName(User user) {
        if(user != null){
            sqlSession.update("COMMUNITY_USER.UPDATEENABLE", user);
        }
    }

    @Override
    public List<User> queryAllUser() {
        return sqlSession.selectList("COMMUNITY_USER.SELECTALL");
    }

    @Override
    public boolean nameExist(String username) {
        boolean result = false;
        Map<String,String> param = new HashMap<String,String>();
        param.put("username", username);
        int count = sqlSession.selectOne("COMMUNITY_USER.CONUTNAME",param);
        if(count > 0){
            result = true;
        }
        return result;
    }

}
