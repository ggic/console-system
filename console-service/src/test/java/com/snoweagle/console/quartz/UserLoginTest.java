package com.snoweagle.console.quartz;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snoweagle.console.common.domain.system.User;
import com.snoweagle.console.service.system.UserService;
import org.springframework.util.Log4jConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")
public class UserLoginTest extends AbstractJUnit4SpringContextTests{

	private static final Logger logger = LoggerFactory.getLogger(UserLoginTest.class);
	
	@Resource
	UserService userService;
    @Before
    public void setUp() throws Exception {
        Log4jConfigurer.initLogging("classpath:META-INF/log4j.properties");
    }
	@Test
	public void LoginTest() {
		String userName = "wangshaobo";
		String passWord = "1";
		
		User user = userService.queryUserByUserNameAndPasswd(userName, passWord);
		
		logger.info(user.toString());
		assertEquals("Y",user.getValidFlg());
	}
	
	@Test 
    public void queryUserByPrimaryKey() {
    	Long userId = (long) 2;
    	
    	User user = userService.queryUserByPrimaryKey(userId);
    	
    	logger.debug(user.toString());
    	
    	assertEquals("Lucy", user.getName());
    	assertEquals("123@163.com", user.getEmail());
    	assertEquals("123@163.com", user.getEmail());
    	assertEquals("021-56213306", user.getPhone());
    	assertEquals("2", user.getValidFlg());
    	
    }
}
