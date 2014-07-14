package com.ahpu.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahpu.bean.UserInfo;
import com.ahpu.intf.IUserInfo;

@Service
public class UserInfoService implements IUserInfo {
    
    @Autowired
    SqlSession sqlSession;

    public void createUser(UserInfo userinfo) {
        if(userinfo != null){
            sqlSession.insert("COMMUNITY_USERINFO.INSERT", userinfo);
        }
    }

    public UserInfo queryByName(String username) {
        if(StringUtils.isNotBlank(username)){
            return sqlSession.selectOne("COMMUNITY_USERINFO.SELECT", username);
        }
        return null;
    }

    public void updateById(UserInfo userinfo) {
        if(userinfo != null){
            sqlSession.update("COMMUNITY_USERINFO.UPDATEBYID", userinfo);
        }
    }

    public void updateByName(UserInfo userinfo) {
        if(userinfo != null){
            sqlSession.update("COMMUNITY_USERINFO.UPDATEBYNAME", userinfo);
        }
    }

    public List<UserInfo> queryByCommunityId(Integer id) {
        if(id > 0){
            return sqlSession.selectList("COMMUNITY_USERINFO.SELECTBYCOMMUNITYID", id);
        }
        return null;
    }

}
