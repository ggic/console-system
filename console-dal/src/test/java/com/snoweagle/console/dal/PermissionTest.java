package com.snoweagle.console.dal;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snoweagle.console.common.domain.system.Permission;
import com.snoweagle.console.dal.persistence.PermissionMapper;
import org.springframework.util.Log4jConfigurer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/spring-mybatis.xml")
public class PermissionTest {

	private static final Logger logger = LoggerFactory.getLogger(MybatisTest.class);
	
	@Autowired
	private PermissionMapper permissionMapper;
    @Before
    public void setUp() throws Exception {
        Log4jConfigurer.initLogging("classpath:META-INF/log4j.properties");

    }
	@Test
	public void getMenuByPrimaryKeyTest() {
		Long menuId = (long) 2;
		Permission permission = permissionMapper.selectByPrimaryKey(menuId);
		logger.info(permission.toString());

		assertEquals("删除", permission.getDescription());
		assertEquals("Y", permission.getValidFlg());
	}
	
	@Test
	public void getMenuByUserId() {
		Long userId = (long) 2;
		List<Permission> permissionList = permissionMapper.selectByUserId(userId);
		logger.info(permissionList.get(0).toString());
		
		assertEquals(3, permissionList.size());
		assertEquals("查询", permissionList.get(0).getDescription());
		
	}

}
