/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.data;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.ViewType;
import com.jfinal.weixin.demo.WeixinApiController;
import com.jfinal.weixin.demo.WeixinMsgController;
import com.jfinal.weixin.demo.WeixinPayController;
import com.jfinal.weixin.extend.WechatApi;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.webao.cif.entity.Customer;

import freemarker.template.TemplateModelException;

public class SystemConfig extends JFinalConfig {

	/**
	 * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
	 *
	 * @param pro
	 *            生产环境配置文件
	 * @param dev
	 *            开发环境配置文件
	 */
	public void loadProp(String pro, String dev) {
		try {
			PropKit.use(pro);
		} catch (Exception e) {
			PropKit.use(dev);
		}
	}

	public void configConstant(Constants me) {
		loadProp("config_pro.txt", "config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));

		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.FREE_MARKER);
	}

	public void configRoute(Routes me) {
		me.add("/msg", WeixinMsgController.class);
		me.add("/api", WeixinApiController.class, "/api");
		me.add("/pay", WeixinPayController.class);
		me.add("/api/wechat", WechatApi.class);
		me.add("/", SystemApi.class);
		me.add("/report", ReportApi.class);
	}

	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		me.add(arp);

		// EhCachePlugin ecp = new EhCachePlugin();
		// me.add(ecp);

		// 使用redis分布accessToken
		// RedisPlugin redisPlugin = new RedisPlugin("weixin", "127.0.0.1");
		// redisPlugin.setSerializer(JdkSerializer.me); //
		// 需要使用fst高性能序列化的用户请删除这一行（Fst jar依赖请查看WIKI）
		// me.add(redisPlugin);
		arp.addMapping("cif_customer", Customer.class);
	}

	public void configInterceptor(Interceptors me) {
		// 设置默认的 appId 规则，默认值为appId，可采用url挂参数 ?appId=xxx 切换多公众号
		// ApiInterceptor.setAppIdParser(new
		// AppIdParser.DefaultParameterAppIdParser("appId")); 默认无需设置
		// MsgInterceptor.setAppIdParser(new
		// AppIdParser.DefaultParameterAppIdParser("appId")); 默认无需设置
	}

	public void configHandler(Handlers me) {

	}

	public void afterJFinalStart() {
		// 1.5 之后支持redis存储access_token、js_ticket，需要先启动RedisPlugin
		// ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
		// 1.6新增的2种初始化
		// ApiConfigKit.setAccessTokenCache(new
		// RedisAccessTokenCache(Redis.use("weixin")));
		// ApiConfigKit.setAccessTokenCache(new
		// RedisAccessTokenCache("weixin"));

		ApiConfig ac = new ApiConfig();
		// 配置微信 API 相关参数
		ac.setToken(PropKit.get("token"));
		ac.setAppId(PropKit.get("appId"));
		ac.setAppSecret(PropKit.get("appSecret"));

		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
		 * 2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));

		/**
		 * 多个公众号时，重复调用ApiConfigKit.putApiConfig(ac)依次添加即可，第一个添加的是默认。
		 */
		ApiConfigKit.putApiConfig(ac);

		// 微信 WxSession的配置
		// 启用默认的Session管理器
		// ApiConfigKit.enableDefaultWxSessionManager();
		// 启用redis Session管理器
		// ApiConfigKit.setWxSessionManager(new
		// RedisWxSessionManager("weixin"));

		String ctx = JFinal.me().getContextPath();
		try {
			FreeMarkerRender.getConfiguration().setSharedVariable("ctx", ctx);
		} catch (TemplateModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}
}
