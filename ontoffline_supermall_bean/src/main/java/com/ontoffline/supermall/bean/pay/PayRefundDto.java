/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.pay;


import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public class PayRefundDto {

	/**
	 * 用户的openid
	 */
	private String openId;
	
	/**
	 * 订单对应的商家商户号
	 */
	private String mchId;
	
	/**
	 * 商家交易单号
	 */
	private String tradeNo;
	
	/**
	 * 支付单号
	 */
	private String txnId;
	
	/**
	 * 商家退款单号
	 */
	private String refundNo;
	
	/**
	 * 订单总金额
	 */
	private Double totalAmt;
	
	/**
	 * 退款金额
	 */
	private Double refundAmt;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Double getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(Double refundAmt) {
		this.refundAmt = refundAmt;
	}
	
	

}
