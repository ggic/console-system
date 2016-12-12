package com.snoweagle.console.service.order;

import java.util.List;

import javax.annotation.Resource;

import com.snoweagle.console.common.domain.pagination.PageResult;
import com.snoweagle.console.dal.persistence.ControlOrderMapper;
import com.snoweagle.console.dal.persistence.InstOrderMapper;
import org.springframework.stereotype.Service;

import com.snoweagle.console.common.domain.order.OrderRequest;
import com.snoweagle.console.common.utils.ResultConvert;

/**
 * @author n.tang
 *
 */
@Service("orderQueryService")
public class OrderQueryService{
	@Resource
    InstOrderMapper orderInfoMapper;
	@Resource
	ControlOrderMapper controlOrderMapper;
	
	/**
	 * Service function to dynamic query the table tt_inst_order,and implements the paging
     * @param: InstOrderRequest
     * @return: PageResult，list collect inst_order information and Page
     */
	public PageResult queryByDynamicParams(OrderRequest instOrderRequest){
			
		List list = orderInfoMapper.selectByDynamicParams(instOrderRequest);
		PageResult pageResult = ResultConvert.ConvertToPageResult(list);
		
		return pageResult;
			
	}
	/**
	 * Query the inst_order_result table by the instOrderNo
     * @param: String instOderNo
     * @return: PageResult
     */
	public PageResult queryByInstOrderNo(String instOderNo) {
		
		List list = orderInfoMapper.selectByInstOrderNo(instOderNo);
		return ResultConvert.ConvertToPageResult(list);
	}
	
	/**
	 * Query the tt_control_order table by the instOrderRequest
     * @param: InstOrderRequest instOrderRequest
     * @return: PageResult
     */
	public PageResult queryByControlOrderInfo(OrderRequest orderRequest) {
		
		List list = controlOrderMapper.selectByControlOrderParams(orderRequest);
		return ResultConvert.ConvertToPageResult(list);
		
	}
}
