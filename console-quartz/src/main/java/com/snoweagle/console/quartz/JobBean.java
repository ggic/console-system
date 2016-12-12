package com.snoweagle.console.quartz;

import java.util.Date;
import java.util.UUID;




public class JobBean {
    
        public  void execute(){
                System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName()+UUID.randomUUID()+new Date());
            try {
                 throw  new RuntimeException("出错了！！！！");
            }catch (Exception e){
                e.printStackTrace();
            }

        }

}
