package com.udm.health.displaytag;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

public class DefaultPaginatedList<T> implements PaginatedList {

	private List<T> list;
	private int pageNumber;
	private int objectsPerPage;
	private int fullListSize;
	private String sortCriterion;
	private SortOrderEnum sortDirection;
	private String searchId;
	
	@Override
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public int getObjectsPerPage() {
		return objectsPerPage;
	}
	
	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	@Override
	public int getFullListSize() {
		return fullListSize;
	}
	
	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}

	@Override
	public String getSortCriterion() {
		return sortCriterion;
	}
	
	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	@Override
	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}
	
	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	@Override
	public String getSearchId() {
		return searchId;
	}
	
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

}
