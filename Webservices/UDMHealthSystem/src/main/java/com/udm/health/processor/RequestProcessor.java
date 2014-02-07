package com.udm.health.processor;

public interface RequestProcessor<T,R> {
	R process(T request);
}
