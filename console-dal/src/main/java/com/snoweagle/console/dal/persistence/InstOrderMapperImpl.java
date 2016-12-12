package com.snoweagle.console.dal.persistence;

import java.util.List;

import com.snoweagle.console.dal.domain.InstOrder;
import com.snoweagle.console.dal.domain.InstOrderResult;
import com.snoweagle.console.common.domain.order.OrderRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

/**
 * @author n.tang
 *
 */
@Repository
public class InstOrderMapperImpl extends BaseDao implements InstOrderMapper{

	/**
	 * Persistence function to dynamic query the table tt_inst_order,and implements the paging
     * parameters:Map,for dynamic query
     * parameters:PageNum,pageSize for paging
     * return: List<OrderInfo>，list collect inst_order information
     */
	public List<InstOrder> selectByDynamicParams(OrderRequest orderRequest) {

		return sqlSessionTemplate.selectList("InstOrderMapper.selectByDynamicParams",
				orderRequest,new RowBounds(orderRequest.getPageNum(), orderRequest.getPageSize()));
	}

	/**
     * This method is to query the result of table tt_inst_order_result by instOrderNo.
     * @param String instOrderNo
     * @return List<InstOrderResult>
     */
	public List<InstOrderResult> selectByInstOrderNo(String instOrderNo) {
		return sqlSessionTemplate.selectList("com.netfinworks.fcm.dal.persistence.InstOrderResultMapper.selectByInstOrderNo",instOrderNo
				,new RowBounds(1000,1));
	}
	
}
