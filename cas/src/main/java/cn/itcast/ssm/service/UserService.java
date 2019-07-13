package cn.itcast.ssm.service;

import cn.itcast.ssm.entity.User;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/30 20:37
 */
public interface UserService {

    public User getUser(Long userId) throws Exception;

    public boolean login(String username,String password) throws Exception ;

    public void register(String username ,String password) throws Exception ;

}
