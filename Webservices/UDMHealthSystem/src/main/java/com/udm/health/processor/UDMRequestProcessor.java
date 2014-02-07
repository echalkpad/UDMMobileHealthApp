package com.udm.health.processor;

import java.util.Map;

public class UDMRequestProcessor {
	private Map<Class<?>, RequestProcessor<?,?>> processorMap;
	
	public <T,R> R process(T request) {
		RequestProcessor<T,R> processor = lookupProcessor(request.getClass());
		return processor.process(request);
	}
	
	private <T,R> RequestProcessor<T,R> lookupProcessor(Class<?> requestClass) {
		RequestProcessor<T,R> processor = (RequestProcessor<T, R>) processorMap.get(requestClass);
		if (processor == null) {
			throw new RuntimeException(String.format("There is no RequestProcessor mapped for request class %s", requestClass));
		}
		return processor;
	}
	
	public void setProcessorMap(Map<Class<?>, RequestProcessor<?, ?>> processorMap) {
		this.processorMap = processorMap;
	}
}
