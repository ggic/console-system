package com.snoweagle.console.dal;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snoweagle.console.common.domain.system.User;
import com.snoweagle.console.dal.persistence.UserMapperImpl;
import org.springframework.util.Log4jConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/spring-mybatis.xml")
public class UserTest extends AbstractJUnit4SpringContextTests{

	private static final Logger logger = LoggerFactory.getLogger(MybatisTest.class);
	
	@Autowired
    UserMapperImpl userMapperImpl;
    @Before
    public void setUp() throws Exception {
        Log4jConfigurer.initLogging("classpath:META-INF/log4j.properties");

    }
	@Test
	public void userCountTest() {
		User user = new User();
		user.setName("D");
		List<User> userinfo = userMapperImpl.selectUsersByDynamicParams(user,true);
		logger.info(userinfo.toString());
		
		assertEquals(1,userinfo.size());
	}
	
	@Test
	public void userLoginTest() {
		Map<String,String> map = new HashMap<String ,String>();
		map.put("userName", "David");
		map.put("passWord", "123@123");
		User userinfo = userMapperImpl.selectUserByUserNameAndPasswd(map);
		logger.info(userinfo.toString());
		
		assertEquals("3",userinfo.getValidFlg());
	}
	@Test
	public void selectUserByPrimaryKey() {
		Long userId = (long) 1;
		User user = userMapperImpl.selectByPrimaryKey(userId);
		logger.info(user.toString());
		
		assertEquals("David",user.getName());
	}
    @Test
    public void insert() {
        User user = new User();
        user.setName("lisi");
        user.setPassword("1");
        user.setPhone("123456789");
        user.setEmail("123@qq.com");
        int count = userMapperImpl.insert(user);

        logger.info(String.valueOf(count));

    }
}
