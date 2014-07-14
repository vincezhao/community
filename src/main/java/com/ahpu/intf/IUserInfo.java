package com.ahpu.intf;

import java.util.List;

import com.ahpu.bean.UserInfo;

/*
 * 用户信息操作API
 */
public interface IUserInfo {
    /*
     * 创建一个新用户信息
     */
    public void createUser(UserInfo userinfo);
    
    public UserInfo queryByName(String username);
    
    public void updateById(UserInfo userinfo);
    /*
     * 根据用户名更新用户信息
     */
    public void updateByName(UserInfo userinfo);
    
    public List<UserInfo> queryByCommunityId(Integer id);
    
}
