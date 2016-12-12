package com.snoweagle.console.dal.persistence;

import java.util.List;

import com.snoweagle.console.dal.domain.ControlOrder;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.snoweagle.console.common.domain.order.OrderRequest;

/**
 * @author n.tang
 *
 */
@Repository
public class ControlOrderMapperImpl extends BaseDao implements ControlOrderMapper{

	/**
     * query the result of table tt_control_order by dynamic parameters.
     * @param OrderRequest instOrderRequest
     * @return List<ControlOrder>
     */
	public List<ControlOrder> selectByControlOrderParams(
			OrderRequest orderRequest) {	
		return sqlSessionTemplate.selectList("ControlOrderMapper.selectByControlOrderParams", orderRequest
				,new RowBounds(orderRequest.getPageNum(), orderRequest.getPageSize()));
	}
	
}
