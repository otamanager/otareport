package com.webao.cif.entity;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.base.util.Constant;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
import com.jfinal.extend.WebaoModel;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.UserApi;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version 2015-10-24 12:32:38
 * @see com.webao.cif.entity.Customer
 */
public class Customer extends WebaoModel<Customer> {
	public static Customer me = new Customer();

	/**
	 * 查询
	 * 
	 * @param entity
	 * @return
	 */
	public List<Customer> query(Customer entity) {
		String[] keys = entity._getAttrNames();
		StringBuffer buffer = new StringBuffer("select * from cif_customer where 1=1 ");
		for (String key : keys) {
			buffer.append(" and ").append(key).append("='").append(entity.get(key) + "").append("' ");
		}
		buffer.append(" order by createTime desc");
		return me.find(buffer.toString());
	}

	/**
	 * 模糊查询
	 * 
	 * @param entity
	 * @return
	 */
	public List<Customer> queryFuzzy(String key) {
		StringBuffer buffer = new StringBuffer("select * from cif_customer  ");
		if (StringUtils.isNotEmpty(key)) {
			buffer.append(" where nickName like '%").append(key).append("%' or customerName like'%").append(key)
					.append("%' or mobile like '%").append(key).append("%' ");
		}
		buffer.append(" order by createTime desc");
		return me.find(buffer.toString());
	}

	public List<Customer> queryFuzzyNoAuth(String key) {
		key = StringUtil.urlEncode(key);
		StringBuffer buffer = new StringBuffer("select * from cif_customer where  ");
		if (StringUtils.isNotEmpty(key)) {
			buffer.append("  nickName like '%").append(key).append("%' or customerName like'%").append(key).append("%' or mobile like '%")
					.append(key).append("%' and ");
		}
		buffer.append(" position is null order by createTime desc");
		return me.find(buffer.toString());
	}

	public void unRegister(String openId) {
		Customer customerQuery = Customer.me.queryByExtendId(openId);
		if (null == customerQuery) {
			return;
		}
		customerQuery.set("status", Constant.DISENABLED);
		customerQuery.set("updateTime", DateUtil.nowTime());
		customerQuery.update();
	}

	public Customer reigster(String openId, String orgCode, String shareCustomerId, String channel) {
		ApiResult params = UserApi.getUserInfo(openId);
		Customer customer = new Customer();
		Customer customerQuery = Customer.me.queryByExtendId(openId);
		if (null != customerQuery) {
			customer = customerQuery;
		}
		customer.set("nickName", StringUtil.urlEncode(params.getStr("nickname")));
		customer.set("sex", params.get("sex"));
		customer.set("province", StringUtil.urlEncode(params.getStr("province")));
		customer.set("city", StringUtil.urlEncode(params.getStr("city")));
		customer.set("country", StringUtil.urlEncode(params.getStr("country")));
		customer.set("headImgUrl", params.getStr("headimgurl"));
		customer.set("extendId", params.getStr("openid"));
		customer.set("language", params.getStr("language"));
		// 如果是通过客服分享过来的客户，设置分享客户ID
		customer.set("registerTime", params.get("subscribe_time"));
		customer.set("remark", params.getStr("remark"));
		customer.set("orgCode", orgCode);
		customer.set("createTime", DateUtil.nowTime());
		customer.set("channel", channel);
		// 关注
		customer.set("status", Constant.ENABLED);
		String id = StringUtil.randomUUIDSplit();
		if (null != customerQuery) {
			id = customer.getStr("id");
			customer.update();
		} else {
			customer.set("level", Constant.CUSTOMER_LEVEL_DEFAULT);
			customer.set("id", id);
			customer.save();
		}

		return customer;

	}

	/**
	 * 获得金币数
	 * 
	 * @return
	 */
	public Integer getCoin() {
		BigDecimal coin = this.getBigDecimal("coin");
		if (null == coin) {
			return 0;
		}
		return coin.intValue();
	}

	/**
	 * 是否是管理员
	 * 
	 * @param customer
	 * @return
	 */
	public static boolean isManager(Customer customer) {
		if (null == customer.get("position")) {
			return false;
		}
		if (!customer.getStr("position").equalsIgnoreCase(Constant.CUSTOMER_LEVEL_MANAGER)) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是运营者
	 * 
	 * @param customer
	 * @return
	 */
	public static boolean isOperator(Customer customer) {
		if (null == customer.get("position")) {
			return false;
		}
		if (!customer.getStr("position").equalsIgnoreCase(Constant.CUSTOMER_LEVEL_OPERATOR)) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是销售
	 * 
	 * @param customer
	 * @return
	 */
	public static boolean isSaller(Customer customer) {
		if (null == customer.get("position")) {
			return false;
		}
		if (!customer.getStr("position").equalsIgnoreCase(Constant.CUSTOMER_LEVEL_SALLER)) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是店长
	 * 
	 * @param customer
	 * @return
	 */
	public static boolean isShopManager(Customer customer) {
		if (null == customer.get("position")) {
			return false;
		}
		if (!customer.getStr("position").equalsIgnoreCase(Constant.CUSTOMER_LEVEL_SHOPMANAGER)) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是管理员
	 * 
	 * @param customer
	 * @return
	 */
	public boolean isManager() {
		Customer customer = this;
		if (null == customer.get("position")) {
			return false;
		}
		if (!customer.getStr("position").equalsIgnoreCase(Constant.CUSTOMER_LEVEL_MANAGER)) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是运营者
	 * 
	 * @param customer
	 * @return
	 */
	public boolean isOperator() {
		Customer customer = this;
		if (null == customer.get("position")) {
			return false;
		}
		if (!customer.getStr("position").equalsIgnoreCase(Constant.CUSTOMER_LEVEL_OPERATOR)) {
			return false;
		}
		return true;
	}

	/**
	 * 根据外键查询客户
	 * 
	 * @param extendId
	 * @return
	 */
	public Customer queryByExtendId(String extendId) {
		return me.findFirst("select * from cif_customer where extendId = '" + extendId + "'");
	}
}
