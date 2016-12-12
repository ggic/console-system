package com.snoweagle.console.quartz;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import com.snoweagle.console.common.domain.order.OrderRequest;
import com.snoweagle.console.common.domain.pagination.PageResult;

import com.snoweagle.console.service.order.OrderQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by wangshaobo on 2015/7/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")
public class AppTest extends AbstractJUnit4SpringContextTests {
	private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

	@Resource
    OrderQueryService orderInfoService;
    
    @Test
    public void queryOrderInfoByDynamicParams() throws ParseException{
    	OrderRequest instOrderRequest = new OrderRequest();
    	instOrderRequest.setInstOrderNo("");
    	instOrderRequest.setPaymentSeqNo("");
    	instOrderRequest.setFundChannelApi("HTF");
    	PageResult result = orderInfoService.queryByDynamicParams(instOrderRequest);

    	assertEquals(result.getQueryPage().getPageSize(),1020);
    }
    
    @Test 
    public void queryOrderResultByInstOrderNo() {
    	String InstOrderNo = "CON0122041000070578";
    	
    	PageResult result = orderInfoService.queryByInstOrderNo(InstOrderNo);
    	
    	assertEquals(result.getQueryPage().getPageSize(),1);
    	assertEquals(result.getInfo().size(), 1);
    }
    
    @Test 
    public void queryControlOrderInfo() {
    	OrderRequest orderRequest = new OrderRequest();
    	orderRequest.setInstOrderNo("CON0122041000070571");
    }
}
