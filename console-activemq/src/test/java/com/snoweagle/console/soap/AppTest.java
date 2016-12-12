package com.snoweagle.console.soap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class AppTest 
{
        @Autowired
        private JmsTemplate jmsTemplate;  
        String destinationName = "fsn.PEOrderResultNotifyQueue";
        @Test
        public  void send()  {
            jmsTemplate.send(destinationName,new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage("fdasfasfasfdasfasfdasf"+System.currentTimeMillis());
                }
            });
        }
        
        @Test
        public  void recevied() throws JMSException  {
            ActiveMQTextMessage msg= (ActiveMQTextMessage)jmsTemplate.receive(destinationName);
            System.out.println(msg);
        }
        
}
