/**
 * 
 */
package com.data;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

/**
 * @author dimmer
 *
 */
public class SystemApi extends Controller {
	public void index() {
		// String openId = getPara("token");
		// if (StringUtils.isEmpty(openId)) {
		// openId = WechatUtil.getOpenId(PropKit.get("appId"),
		// PropKit.get("appSecret"), getPara("code"));
		// }
		// if (StringUtils.isEmpty(openId)) {
		// render("/login.html");
		// return;
		// }
		// setAttr("token", openId);
		// // 如果不是,跳转到到登录页面
		// Customer customer = Customer.me.queryByExtendId(openId);
		// if (null == customer) {
		// Customer.me.reigster(openId, "", "", "");
		// render("/login.html");
		// return;
		// }
		// if (StringUtils.isEmpty(customer.get("tokenId"))) {
		// render("/login.html");
		// return;
		// }
		// // 判断客户是否是白名单
		// setAttr("token", openId);
		// render("/index.html");
		render("/login.html");
	}

	public void login() {
		String password = getPara("password");
		String openId = getPara("token");
		setAttr("token", openId);
		if (password.equalsIgnoreCase(PropKit.get("token"))) {
			// Customer customer = Customer.me.queryByExtendId(openId);
			// customer.set("tokenId", StringUtil.randomUUIDSplit());
			// customer.set("tokenStartTime", DateUtil.nowTime());
			// customer.update();
			// setAttr("token", openId);
			// setAttr("customer", customer);
			render("/index.html");
			return;
		}
		render("/login.html");
		return;
	}
}
