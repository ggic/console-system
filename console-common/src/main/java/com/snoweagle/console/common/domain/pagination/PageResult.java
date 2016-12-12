package com.snoweagle.console.common.domain.pagination;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PageResult implements Serializable {

	/**
	 * Base Class for result for query 
	 */
	private static final long serialVersionUID = 1L;
	
	/*分页信息*/
	private QueryPage queryPage;
	
	/*返回的集合信息*/
	private List info;

	public QueryPage getQueryPage() {
		return queryPage;
	}

	public void setQueryPage(QueryPage queryPage) {
		this.queryPage = queryPage;
	}

	public List getInfo() {
		return info;
	}

	public void setInfo(List info) {
		this.info = info;
	}

	@Override
	public String toString(){
		
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		
	}
}
