package com.udm.health.dozer;

import org.dozer.DozerConverter;

public class StringToLongConverter extends DozerConverter<String, Long> {

	public StringToLongConverter() {
		super(String.class, Long.class);
	}
	
	@Override
	public Long convertTo(String source, Long destination) {
		return Long.valueOf(source);
	}

	@Override
	public String convertFrom(Long source, String destination) {
		return String.valueOf(source);
	}
}
