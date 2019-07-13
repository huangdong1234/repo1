package cn.itcast.ssm.service;

import cn.itcast.ssm.dao.UserMapper;
import cn.itcast.ssm.entity.User;
import cn.itcast.ssm.util.BytesToString;
import cn.itcast.ssm.util.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/30 20:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(Long userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public boolean login(String username, String password) throws Exception {

        User user = userMapper.selectByUsername(username);

        if(user==null){
            return false;
        }
        String shaPassword = DigestUtil.getSHA1((password + user.getSalt()).getBytes());//生成密码摘要

        if(shaPassword.equals(user.getUserPassword())){
            return true;
        }else{
            return false;
        }

    }

    @Transactional
    @Override
    public void register(String username ,String password) throws Exception {

        String salt = BytesToString.getRandomString(8);//生成随机盐
        String shaPassword = DigestUtil.getSHA1((password + salt).getBytes());
        User user=new User();
        user.setUserName(username);
        user.setUserPassword(shaPassword);
        user.setSalt(salt);
        userMapper.insert(user);

    }


}
