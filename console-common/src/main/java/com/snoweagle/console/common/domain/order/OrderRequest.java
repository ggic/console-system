package com.snoweagle.console.common.domain.order;

import com.snoweagle.console.common.domain.pagination.QueryPage;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OrderRequest  extends QueryPage {

	/**
	 * Base InstOrder Request for Query tt_inst_order ，include the Order information and page
	 */
	private static final long serialVersionUID = 1L;
	

	/*机构订单号*/
	private String instOrderNo;
	
	/*支付订单号*/
	private String paymentSeqNo;
	
	/*渠道API代码*/
	private String fundChannelApi;
	
	/*查询起始日*/
	private String gmtStart;
	
	/*查询结束日*/
	private String gmtEnd;


	public String getInstOrderNo() {
		return instOrderNo;
	}

	public void setInstOrderNo(String instOrderNo) {
		this.instOrderNo = instOrderNo;
	}

	public String getPaymentSeqNo() {
		return paymentSeqNo;
	}

	public void setPaymentSeqNo(String paymentSeqNo) {
		this.paymentSeqNo = paymentSeqNo;
	}

	public String getFundChannelApi() {
		return fundChannelApi;
	}

	public void setFundChannelApi(String fundChannelApi) {
		this.fundChannelApi = fundChannelApi;
	}

    public String getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(String gmtStart) {
        this.gmtStart = gmtStart;
    }

    public String getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    @Override
    public String toString(){

        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);

    }
}
