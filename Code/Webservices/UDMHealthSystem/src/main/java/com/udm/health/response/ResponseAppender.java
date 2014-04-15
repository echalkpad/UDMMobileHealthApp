package com.udm.health.response;

import java.util.Collection;
import java.util.List;

public class ResponseAppender {

	private List<ResponseAdapter<?,?>> adapters;
	
	public <I,O> void appendToResponse(I responseIn, O responseOut) {
		if (isNotEmpty(responseIn)) {
			boolean run = false;
			for (ResponseAdapter adapter : adapters) {
				if (adapter.shouldAdapt(responseIn, responseOut)) {
					adapter.adaptAndAttach(responseIn, responseOut);
					run = true;
					break;
				}
			}
			
			if ( ! run) {
				throw new IllegalStateException("There is no ResponseAdapter mapped to class java.lang.String.");
			}
		}
	}
	
	private boolean isNotEmpty(Object responseIn) {
		if (responseIn == null) {
			return false;
		} else if (Collection.class.isAssignableFrom(responseIn.getClass())) {
			return ! ((Collection<?>)responseIn).isEmpty();
		}
		return true;
	}
	
	public void setAdapters(List<ResponseAdapter<?, ?>> adapters) {
		this.adapters = adapters;
	}
	
}
