package com.snoweagle.console.dal;

import java.util.List;

import static org.junit.Assert.assertEquals;

import com.snoweagle.console.dal.domain.ControlOrder;
import com.snoweagle.console.dal.domain.InstOrder;
import com.snoweagle.console.dal.domain.InstOrderResult;
import com.snoweagle.console.dal.persistence.ControlOrderMapper;
import com.snoweagle.console.dal.persistence.InstOrderMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.snoweagle.console.common.domain.order.OrderRequest;
import org.springframework.util.Log4jConfigurer;


/**
  * Created by wangshaobo on 2015/7/14.
  */
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration(locations = "classpath:/META-INF/spring/spring-mybatis.xml")
 public class MybatisTest extends AbstractJUnit4SpringContextTests {
 	
	 private static final Logger logger = LoggerFactory.getLogger(MybatisTest.class);

	 @Autowired
	 private InstOrderMapper orderMapper;
	 @Autowired
	 private ControlOrderMapper controlOrderMapper;

     @Before
     public void setUp() throws Exception {
         Log4jConfigurer.initLogging("classpath:META-INF/log4j.properties");
     }

     @Test
     public void queryOrderInfoByDynamicParams(){
		 
		 OrderRequest orderRequest = new OrderRequest();
		 orderRequest.setInstOrderNo("CON0122041000070574");
		 List<InstOrder> list = orderMapper.selectByDynamicParams(orderRequest);
		 PageInfo<InstOrder> page = new PageInfo<InstOrder>(list);
		 
		 assertEquals(page.getTotal(), 1);
		 assertEquals(page.getPageNum(), 1);
		 assertEquals(page.getPageSize(), 1);
		 assertEquals(list.size(), 1);
		 
     }
	 
	 @Test
	 public void queryOrderResultByInstOrderNo() {
		 
		 String instOrderNo = "CON0122041000070578";
		 List<InstOrderResult> list = orderMapper.selectByInstOrderNo(instOrderNo);
		 
		 assertEquals(list.size(), 1);
		 
	 }
	 
	 @Test 
	 public void queryControlOrderInfo(){
		 OrderRequest instOrderRequest = new OrderRequest();
		 instOrderRequest.setInstOrderNo("CON0122041000070571");
		 logger.info(instOrderRequest.toString());
		 List<ControlOrder> list = controlOrderMapper.selectByControlOrderParams(instOrderRequest);
		 PageInfo<ControlOrder> page = new PageInfo<ControlOrder>(list);
		 assertEquals(page.getTotal(), 1);
	 }
     
 }