package com.base.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.base.model.ResultData;

/**
 * HTTP访问工具类
 */
@SuppressWarnings("deprecation")
public class HttpConnectionUtil {
	// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
	// 字符串在编译时会被转码一次,所以是 "\\b"
	// \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
	static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i" + "|windows (phone|ce)|blackberry"
			+ "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp" + "|laystation portable)|nokia|fennec|htc[-_]"
			+ "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
	static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser" + "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

	// 移动设备正则匹配：手机端、平板
	static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
	static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

	/**
	 * 检测是否是移动设备访问
	 * 
	 * @Title: check
	 * @Date : 2014-7-7 下午01:29:07
	 * @param userAgent
	 *            浏览器标识
	 * @return true:移动设备接入，false:pc端接入
	 */
	public static boolean check(String userAgent) {
		if (null == userAgent) {
			userAgent = "";
		}
		// 匹配
		Matcher matcherPhone = phonePat.matcher(userAgent);
		Matcher matcherTable = tablePat.matcher(userAgent);
		if (matcherPhone.find() || matcherTable.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查是否是移动端的请求
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static boolean isMobile(HttpServletRequest request) {
		boolean isFromMobile = false;

		HttpSession session = request.getSession();
		// 检查是否已经记录访问方式（移动端或pc端）
		if (null == session.getAttribute("ua")) {
			try {
				// 获取ua，用来判断是否为移动端访问
				String userAgent = request.getHeader("USER-AGENT").toLowerCase();
				if (null == userAgent) {
					userAgent = "";
				}
				isFromMobile = check(userAgent);
				// 判断是否为移动端访问
				if (isFromMobile) {
					session.setAttribute("ua", "mobile");
				} else {
					session.setAttribute("ua", "pc");
				}
			} catch (Exception e) {
			}
		} else {
			isFromMobile = session.getAttribute("ua").equals("mobile");
		}

		return isFromMobile;
	}

	public static void main(String[] args) {
		System.out.println(get(
				"http://mp.weixin.qq.com/s?__biz=MzI3NTA3MjU5Nw==&mid=208654923&idx=1&sn=3740ecba65920499eb8d7d5dd66918e7&key=b410d3164f5f798e2b4a2e7086f249a1c5060ba67c84d15b0030df504c9c98eb9605821d2fefe166c706d1875fe35ec2&ascene=1&uin=MjU4MDA1MTk0MA%3D%3D&devicetype=webwx&version=70000001&pass_ticket=rkAO%2BQJpbZydyjBMcvOHjDTJbCmjrLyTOrUw7hHDFdAZjjHsNOkA1kHNPxjokkcF"));
	}

	/**
	 * post方式发送data
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postData(String url, Map<String, String> params) throws Exception {
		// 组装Data

		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postForm(url, params);
		body = invokeWithoutEncoding(httpclient, post);
		httpclient.getConnectionManager().shutdown();

		return body;
	}

	public static String postObject(String url, Object obj) throws Exception {
		// 组装Data
		Map<String, Object> params = BeanUtil.bean2Map(obj);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postFormWithMap(url, params);
		body = invokeWithoutEncoding(httpclient, post);
		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * post方式发送data
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postData(String url, Object param) throws Exception {
		// 组装Data
		Map<String, String> params = new HashMap<String, String>();
		if (param instanceof String) {
			params.put("data", param.toString());
		} else {
			params.put("data", GsonUtil.toJson(param));
		}

		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postForm(url, params);
		body = invokeWithoutEncoding(httpclient, post);
		httpclient.getConnectionManager().shutdown();

		ResultData data = ResultDataUtil.fromJson(body);
		if (!ResultDataUtil.isSuccess(data)) {
			throw new Exception(data.getMessage());
		}
		return data.getData().toString();
	}

	public static ResultData postDataNew(String url, Object param) throws Exception {
		// 组装Data
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", GsonUtil.toJson(param));

		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postForm(url, params);
		body = invokeWithoutEncoding(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		return ResultDataUtil.fromJson(body);
	}

	public static String postDataResultBody(String url, Object param) throws Exception {
		// 组装Data
		Map<String, String> params = new HashMap<String, String>();
		if (param instanceof String) {
			params.put("data", param.toString());
		} else {
			params.put("data", GsonUtil.toJson(param));
		}
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postForm(url, params);
		body = invokeWithoutEncoding(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	/**
	 * POST方式访问
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postForm(url, params);
		body = invoke(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String postWithoutEncoding(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postForm(url, params);
		body = invokeWithoutEncoding(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	/**
	 * get方式访问
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpGet get = new HttpGet(url);
		body = invoke(httpclient, get);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String getWithoutEncoding(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpGet get = new HttpGet(url);
		body = invokeWithoutEncoding(httpclient, get);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	private static String invokeWithoutEncoding(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String result = "";
		try {
			result = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static String invokeGBK(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String result = "";
		try {
			result = new String(EntityUtils.toString(response.getEntity()).getBytes("ISO-8859-1"), "GBK");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		return body;
	}

	private static String paseResponse(HttpResponse response) {
		String body = null;
		if (null == response) {
			return body;
		}
		HttpEntity entity = response.getEntity();
		try {
			body = EntityUtils.toString(entity);
			// body = new String(body.getBytes("ISO-8859-1"), "UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

	private static String paseResponseWithoutEncoding(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		String body = null;
		try {
			body = EntityUtils.toString(entity);
			// body = new String(body.getBytes("ISO-8859-1"), "UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

	@SuppressWarnings("deprecation")
	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpost);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("--------------------------http请求出错---------------------");
			System.out.println("http请求出错：" + e.getMessage() + "时间：" + DateUtil.getCurrentDateTime());
			System.out.println("--------------------------http请求出错---------------------");
		}
		return response;
	}

	@SuppressWarnings("deprecation")
	private static HttpPost postForm(String url, Map<String, String> params) {
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpost;
	}

	private static HttpPost postFormWithMap(String url, Map<String, Object> params) {
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, null == params.get(key) ? "" : params.get(key).toString()));
		}
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpost;
	}

	public static String postJsonStr(String url, String jsonStr) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost httpost = new HttpPost(url);
		StringEntity s = new StringEntity(jsonStr, ContentType.create("application/json", "UTF-8"));
		httpost.setEntity(s);
		body = invoke(httpclient, httpost);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String postJsonStr2(String url, String jsonStr) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost httpost = new HttpPost(url);
		StringEntity s = new StringEntity(jsonStr, ContentType.create("application/x-www-form-urlencoded", "UTF-8"));
		httpost.setEntity(s);
		body = invoke(httpclient, httpost);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String postJsonStrWithoutEncoding(String url, String jsonStr) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost httpost = new HttpPost(url);
		StringEntity s = new StringEntity(jsonStr, ContentType.create("application/x-www-form-urlencoded", "UTF-8"));
		httpost.setEntity(s);
		body = invokeWithoutEncoding(httpclient, httpost);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String postUrlWithoutEncoding(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost httpost = new HttpPost(url);
		body = invokeWithoutEncoding(httpclient, httpost);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String postJsonWithoutEncoding(String url, String jsonStr) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost httpost = new HttpPost(url);
		StringEntity s = new StringEntity(jsonStr, ContentType.create("application/json", "UTF-8"));
		httpost.setEntity(s);
		body = invokeWithoutEncoding(httpclient, httpost);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String postJsonGBK(String url, String jsonStr) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost httpost = new HttpPost(url);
		StringEntity s = new StringEntity(jsonStr, ContentType.create("text/xml", "gbk"));
		httpost.setEntity(s);
		body = invokeGBK(httpclient, httpost);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	/**
	 * 用json发送请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	private static HttpPost postFormJson(String url, Map<String, String> params) {
		HttpPost httpost = new HttpPost(url);
		StringEntity s = new StringEntity(JsonUtil.map2json(params), ContentType.create("application/json", "UTF-8"));
		httpost.setEntity(s);
		return httpost;
	}

	public static String postJson(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postFormJson(url, params);
		body = invoke(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	/**
	 * @Description：JSon POST
	 * @author: dimmer
	 * @date: 2015年10月28日 下午3:02:43
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postJson(String url, Object params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		return postJsonStr(url, GsonUtil.toJson(params));
	}

	public static String postJson(DefaultHttpClient httpclient, String url, Map<String, String> params) {
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postFormJson(url, params);
		Header[] headers = post.getAllHeaders();
		StringBuffer buffer = new StringBuffer();
		body = invoke(httpclient, post);
		// httpclient.getConnectionManager().shutdown();
		return body;
	}

	public static String postJsonWithoutEncoding(DefaultHttpClient httpclient, String url, Map<String, String> params) {
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;
		HttpPost post = postFormJson(url, params);
		Header[] headers = post.getAllHeaders();
		StringBuffer buffer = new StringBuffer();
		body = invokeWithoutEncoding(httpclient, post);
		// httpclient.getConnectionManager().shutdown();
		return body;
	}

	/**
	 * 从Servlet中解析json串
	 * 
	 * @param request
	 * @return
	 */
	public static String analyzeJson(HttpServletRequest request) {
		try {
			return IOUtils.toString(request.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	// if (null == request) {
	// return "";
	// }
	// Map<String, String[]> param = request.getParameterMap();
	// Set<String> keySet = param.keySet();
	// Iterator<String> keyIt = keySet.iterator();
	// while (keyIt.hasNext()) {
	// return keyIt.next();
	// }
	// return "";
	// }
}
