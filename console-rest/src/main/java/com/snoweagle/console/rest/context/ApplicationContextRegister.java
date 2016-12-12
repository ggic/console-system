/**
 *
 */
package com.snoweagle.console.rest.context;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

/**
 *应用启动注册类
 */
public class ApplicationContextRegister implements ApplicationContextAware {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		WebApplicationContext wc = (WebApplicationContext) applicationContext;
		ServletContext sc = (ServletContext) wc.getBean(WebApplicationContext.SERVLET_CONTEXT_BEAN_NAME);
		sc.setAttribute("WEB_RESOURCE", sc.getRealPath("/"));

		sc.setAttribute("APP_CONFIG", "");


	}

}
