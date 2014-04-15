package com.udm.health.response;


public interface ResponseAdapter<I,O> {

	boolean shouldAdapt(Object responseIn, Object responseOut);
	void adaptAndAttach(I responseIn, O responseOut);
	
}
