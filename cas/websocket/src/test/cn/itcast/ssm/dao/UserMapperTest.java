package cn.itcast.websocket.dao;

import cn.itcast.websocket.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/30 15:50
 */
public class UserMapperTest {
    private ApplicationContext applicationContext;

    @Autowired
    private UserMapper userMapper;


    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mvc.xml");
        // 导入需要测试的
        userMapper = applicationContext.getBean(UserMapper.class);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void insert() throws Exception {
        User user = new User();
        user.setUserName("yang");
        user.setUserPassword("sdsdds");
        int result = userMapper.insert(user);
        System.out.println(result);
        assert (result == 1);
    }
}