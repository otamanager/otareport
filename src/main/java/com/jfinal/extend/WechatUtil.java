/**
 * @Description:
 * @author dimmer
 * @date 2015年10月27日 下午1:55:58
 *
 */
package com.jfinal.extend;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.base.util.GsonUtil;
import com.base.util.HttpConnectionUtil;
import com.jfinal.weixin.sdk.api.CustomServiceApi;
import com.jfinal.weixin.sdk.api.CustomServiceApi.Articles;
import com.jfinal.weixin.sdk.msg.in.event.EventInMsg;
import com.jfinal.weixin.sdk.msg.out.News;
import com.jfinal.weixin.sdk.msg.out.OutNewsMsg;

/**
 * 
 * @ProjectName：wechatform
 * @ClassName：WechatUtil @Description：
 * @author: dimmer
 * @date: 2015年10月27日 下午1:55:58
 * 
 */
public class WechatUtil {

	public static String splitContent(String text) {
		if (StringUtils.isEmpty(text)) {
			return "";
		}
		if (text.indexOf("@") < 0) {
			return "";
		}
		return text.substring(text.indexOf("@") + 1);
	}

	/**
	 * @Description：获得json入参
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月7日 下午1:58:25
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getRequestJson(HttpServletRequest request) throws Exception {
		StringBuilder json = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line = null;
		while ((line = reader.readLine()) != null) {
			json.append(line);
		}
		reader.close();
		return json.toString();
	}

	/**
	 * @Description：调用微信接口获得openId
	 * @author: dimmer
	 * @date: 2015年10月29日 下午2:35:56
	 * @param appId
	 * @param appSecret
	 * @param code
	 * @return
	 */
	public static String getOpenId(String appId, String appSecret, String code) {
		// 如果code不存在，则调用微信失败
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		String openId = "";
		Map<String, Object> user = null;
		String result = HttpConnectionUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret
				+ "&code=" + code + "&grant_type=authorization_code");
		user = GsonUtil.fromJson(result, Map.class);
		if (null == user || null == user.get("openid")) {
			return "";
		}
		return user.get("openid").toString();
	}

	/**
	 * @Description：组装链接，改为可查看阅读数的链接
	 * @example:
	 * @author: dimmer
	 * @date: 2016年4月22日 上午11:24:47
	 * @param link
	 * @return
	 */
	public static String formLink(String link) {
		if (StringUtils.isEmpty(link) || !link.contains("&scene=")) {
			return link;
		}
		return link.substring(0, link.indexOf("&scene=")) + "&scene=20#wechat_redirect";
	}

	/**
	 * 客服聊天消息 图文消息 @Description：
	 * 
	 * @author: 何帅斌
	 * @date: 2016年7月8日 下午6:03:39
	 * @param openId
	 * @param title
	 * @param url
	 */
	public static void sendChatMsg(String openId, String title, String url) {
		List<Articles> articles = new ArrayList<>();
		Articles article = new Articles();
		article.setDescription("");
		article.setPicurl("http://7xotvy.com1.z0.glb.clouddn.com/y.png");
		article.setTitle(title);
		article.setUrl(url);
		articles.add(article);
		System.out.println(CustomServiceApi.sendNews(openId, articles));
	}

	/**
	 * 组装图文消息
	 * 
	 * @param eventInMsg
	 * @param title
	 * @param description
	 * @param url
	 * @param picUrl
	 * @return
	 */
	public static OutNewsMsg formNewsMsg(EventInMsg eventInMsg, String title, String description, String url, String picUrl) {
		OutNewsMsg msg = new OutNewsMsg(eventInMsg);
		List<News> articles = new ArrayList<>();
		News article = new News();
		article.setDescription(description);
		article.setPicUrl(picUrl);
		article.setTitle(title);
		article.setUrl(url);
		articles.add(article);
		msg.setArticles(articles);
		return msg;
	}
}
