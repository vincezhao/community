package com.ahpu.intf;

import java.util.List;

import com.ahpu.bean.User;

/*
 * 用户登录相关API
 */
public interface IUser {
    
    public void createUser(User user);
    
    public User queryByName(String username);
    
    public void updatePwdByName(User user);
    
    public void updateEnableByName(User user);
    
    public List<User> queryByEnable(int enable);
    
    public List<User> queryAllUser();
    
    public boolean nameExist(String username);
    
}
