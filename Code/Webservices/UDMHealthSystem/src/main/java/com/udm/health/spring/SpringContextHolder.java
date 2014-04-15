package com.udm.health.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	private static SpringContextLookup lookup;

	public SpringContextHolder() {
		if (SpringContextHolder.lookup == null) {
			SpringContextHolder.lookup = new SpringContextLookup();
		}
	}
	
	public static SpringContextLookup getLookup() {
		return lookup; 
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}
	
	// testing
	ApplicationContext getApplicationContext() {
		return SpringContextHolder.applicationContext;
	}
	
	public static class SpringContextLookup {
		
		public <T> T getBean(Class<T> beanClass) {
			if (applicationContext != null) { 
				return applicationContext.getBean(beanClass);
			}
			return null;
		}
		
	}

}
