/**
 * 
 */
package com.jfinal.weixin.extend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CallbackIpApi;
import com.jfinal.weixin.sdk.api.GroupsApi;
import com.jfinal.weixin.sdk.api.MediaApi;
import com.jfinal.weixin.sdk.api.MediaApi.MediaType;
import com.jfinal.weixin.sdk.api.MediaFile;
import com.jfinal.weixin.sdk.api.MenuApi;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.utils.IOUtils;

/**
 * @author dimmer
 *
 */
public class WechatApi extends ApiController {

	public void getMediaAll() {
		renderJson(MediaApi.batchGetMaterial(MediaType.NEWS, 0, 20));
	}

	public void getMediaTemp() {
		MediaFile file = MediaApi.getMedia(getPara("mediaId"));
		System.out.println(file.getFileStream());
	}

	public void getMedia() {
		InputStream input = MediaApi.getMaterial(getPara("mediaId"));
		try {
			renderJson(IOUtils.toString(input));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removecache() {
		CacheKit.removeAll(getPara("key"));
		renderJson("success");
	}

	public void createQrcode() throws Exception {
		ApiResult result = QrcodeApi.createTemporary(2592000, getParaToInt("code"));
		System.out.println(result.get("ticket").toString());
		// result = QrcodeApi.show(result);
		renderJson(result);
	}

	public void createPermanentCode() {
		ApiResult result = QrcodeApi.createPermanent(getPara("code"));
		System.out.println(getPara("code") + "--" + result.getStr("url"));
		renderJson(result);
	}

	/**
	 * 获取微信服务器IP地址
	 */
	public void getCallbackIp() {
		ApiResult apiResult = CallbackIpApi.getCallbackIp();
		renderText(apiResult.getJson());
	}

	public void getMenu() {
		ApiResult result = MenuApi.getMenu();
		renderJson(result.getJson());
	}

	public void createMenu() throws Exception {
		String json = getRequestJson(getRequest());
		ApiResult apiResult = MenuApi.createMenu(json);
		renderJson(apiResult.getJson());
	}

	public void createGroup() {
		ApiResult apiResult = GroupsApi.create(getPara("groupName"));
		renderJson(apiResult.getJson());
	}

	public void queryGroup() {
		ApiResult apiResult = GroupsApi.get();
		renderJson(apiResult.getJson());
	}

	public void memberToGroup() {
		ApiResult apiResult = GroupsApi.membersUpdate(getPara("openId"), getParaToInt("groupId"));
		renderJson(apiResult.getJson());
	}

	/**
	 * @Description：删除菜单
	 * @example:
	 * @author: dimmer
	 * @date: 2015年11月19日 上午8:30:48
	 */
	public void deleteMenu() {
		ApiResult apiResult = MenuApi.deleteMenu();
		renderJson(apiResult);
	}

	/**
	 * @Description：查询接口审核状态
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月2日 上午8:59:10
	 */
	public void queryShakeAround() {
		// Map<String, Object> data = new HashMap<>();
		// data.put("name", "肖宾");
		// data.put("phone_number", "13526632922");
		// data.put("email", "tool@bighongbao.com");
		// data.put("industry_id", "0309");
		// List<String> urlList = new ArrayList<>();
		// data.put("qualification_cert_urls", urlList);
		// data.put("apply_reason", "");
		String jsonResult = HttpKit.post("https://api.weixin.qq.com/shakearound/account/auditstatus?access_token="
				+ AccessTokenApi.getAccessToken().getAccessToken(), "");
		ApiResult result = new ApiResult(jsonResult);
		renderJson(result);
	}

	public void user() {
		// ApiResult apiResult =
		// com.jfinal.weixin.sdk.api.UserApi.getAuthUserInfo(getPara("id"));
		// renderText(apiResult.getJson());
	}

	public void queryOrg() {
		String orgCode = getPara("orgCode");
		// renderJson(Organization.dao.queryByOrgCode(orgCode));
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

}
