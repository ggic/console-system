package com.snoweagle.console.quartz;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snoweagle.console.common.domain.system.Permission;
import com.snoweagle.console.service.system.PermissionService;
import org.springframework.util.Log4jConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")
public class PermissionServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceTest.class);
	
	@Resource
    PermissionService permissionService;
    @Before
    public void setUp() throws Exception {
        Log4jConfigurer.initLogging("classpath:META-INF/log4j.properties");
    }
	@Test
	public void queryMenusByUserIdTest() {
		Long userId = (long) 2;
		List<Permission> permissionList = permissionService.queryPermissionsByUserId(userId);
		logger.debug(permissionList.get(0).toString());
		
		assertEquals(3, permissionList.size());
		assertEquals("查询", permissionList.get(0).getDescription());
	}
	
	@Test
	public void queryByPrimaryKeyTest() {
		Long menuId = (long) 2;
		Permission permission = permissionService.queryByPrimaryKey(menuId);
		logger.debug(permission.toString());
		
		assertEquals("删除", permission.getDescription());
		assertEquals("Y", permission.getValidFlg());
	}
    @Test
    public void findUserMenuTreeTest(){
        permissionService.findUserMenuTree(3L);
    }
}
