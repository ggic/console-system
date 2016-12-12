package com.snoweagle.console.quartz;

import org.quartz.SchedulerException;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class AppTest 
{
        public static void main(String[] args) throws SchedulerException, InterruptedException{
//            StdScheduler quartzScheduler = (StdScheduler)applicationContext.getBean("startQuertz");
//                quartzScheduler.start();
            new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml");
        }
}
