package com.udm.health.domain.internal;

public enum SortOrder {

	ASC,DESC;
	
	public static SortOrder fromString(String sortOrder) {
		for (SortOrder order : values()) {
			if (order.toString().equalsIgnoreCase(sortOrder)) {
				return order;
			}
		}
		throw new IllegalArgumentException("No sort order mapped to " + sortOrder);
	}
	
}
