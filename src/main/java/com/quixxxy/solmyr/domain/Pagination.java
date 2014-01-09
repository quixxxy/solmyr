package com.quixxxy.solmyr.domain;

import java.util.List;

public class Pagination <E> {
	
	private List<E> source;
	private int pageSize;
	private int currentPage;
	private long pages;

	public Pagination(List<E> source, int pageSize, long totalSize, int currentPage) {
		this.source = source;
		this.pageSize = pageSize;
		this.pages = (totalSize / pageSize) + 1;
		this.currentPage = currentPage;
	}

	public List<E> getSource() {
		return source;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public long getPages() {
		return pages;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public boolean isEmptySource(){
		return source == null ? true : source.isEmpty();
	}
}
