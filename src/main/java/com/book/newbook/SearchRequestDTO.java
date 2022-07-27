package com.book.newbook;

import java.util.List;

import org.elasticsearch.search.sort.SortOrder;

import com.book.newbook.PagedRRequestDTO;

public class SearchRequestDTO extends PagedRRequestDTO {
	private List<String> fields;
	private String searchTerm;
	private String sortBy;
    private SortOrder order;
	
	public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    
    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public SortOrder getOrder() {
        return order;
    }

    public void setOrder(SortOrder order) {
        this.order = order;
    }
}
