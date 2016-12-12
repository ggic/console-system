package com.snoweagle.console.common.utils;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.snoweagle.console.common.domain.pagination.PageResult;
import com.snoweagle.console.common.domain.pagination.QueryPage;

/**
 * @author n.tang
 *
 */
public class ResultConvert {

	/**
	 * convert the parameters to the base result ,this is PageResult to query tt_inst_order table
	 * @param list
	 * @return PageResult
	 * */
	public static PageResult ConvertToPageResult(List list) {
		
		PageResult pageResult = new PageResult();
		//to get the page info thought the plug-in of pegehelper
		PageInfo page = new PageInfo(list);
		//convert to QueryPage from pageInfo plug-in
		QueryPage qPage = new QueryPage();
		//qPage.setPageNum(page.getPageNum());
		qPage.setPageSize(page.getPageSize());
		qPage.setPages(page.getPages());
		qPage.setTotal(page.getTotal());
		pageResult.setQueryPage(qPage);
		
		pageResult.setInfo(list);
		
		return pageResult;
	}
	
}
