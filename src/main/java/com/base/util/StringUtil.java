package com.base.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;

	/**
	 * 是否是数字
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isInteger(String input) {
		Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(input);
		return mer.find();
	}

	/**
	 * 截取手机号获得验证码
	 * 
	 * @param mobile
	 * @return
	 */
	public static String subMobile(String mobile) {
		if (StringUtils.isEmpty(mobile) || 11 != mobile.length()) {
			return null;
		}
		return mobile.substring(3, mobile.length());
	}

	/**
	 * @Description：分转换为元
	 * @example:
	 * @author: dimmer
	 * @date: 2015年11月18日 下午12:35:00
	 * @param fen
	 * @return
	 */
	public static String toYuan(String fen) {
		if (StringUtils.isEmpty(fen)) {
			return "0";
		}
		return new BigDecimal(fen).divide(new BigDecimal(100)).toString();
	}

	/**
	 * @Description：去除空格
	 * @example:
	 * @author: dimmer
	 * @date: 2015年11月11日 上午9:58:20
	 * @param obj
	 * @return
	 */
	public static String trim(Object obj) {
		if (null == obj) {
			return "";
		}
		return obj.toString().trim();
	}

	/**
	 * @Description：URLEncode
	 * @example:
	 * @author: dimmer
	 * @date: 2015年11月11日 上午9:59:36
	 * @param obj
	 * @return
	 */
	public static String urlEncode(Object obj) {
		if (null == obj) {
			return "";
		}
		try {
			return URLEncoder.encode(trim(obj), "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return obj.toString();
		}

	}

	/**
	 * decode @Description：
	 * 
	 * @author: 何帅斌
	 * @date: 2015年11月13日 上午9:55:06
	 * @param str
	 * @return
	 */
	public static String urlDecode(String str) {
		if (StringUtils.isNoneBlank(str)) {
			try {
				str = URLDecoder.decode(str, "utf8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * @Description：字节数组转换为字符串
	 * @author: dimmer
	 * @date: 2015年10月31日 下午9:32:08
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 转换为string
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStr(Object obj) {
		if (null == obj) {
			return "";
		}
		return obj.toString();
	}

	/**
	 * 根据list拼接字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String concatString(List<String> arr) {
		StringBuffer conc = new StringBuffer();
		if (null == arr || 0 == arr.size()) {
			return conc.toString();
		}
		for (String chil : arr) {
			conc.append(chil);
			conc.append("|");
		}
		return conc.toString();
	}

	// /**
	// * 根据字符串解析文件
	// *
	// * @param file
	// * @return
	// */
	// public static List<File> formFile(String file) {
	// List<File> fileList = new ArrayList<File>();
	// if (org.apache.commons.lang3.StringUtils.isEmpty(file)) {
	// return fileList;
	// }
	// String[] fileArr = file.split("\\|");
	// for (String ff : fileArr) {
	// String[] arr = ff.split("\\,");
	// File le = new File();
	// le.setFileId(arr[0]);
	// le.setAttachType(arr[1]);
	// le.setAttachPurpose(arr[2]);
	// fileList.add(le);
	// }
	// return fileList;
	// }

	/**
	 * 生成随机码
	 * 
	 * @return
	 */
	public static final String randomUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 生成随机码，去掉-，共32位
	 * 
	 * @return
	 */
	public static final String randomUUIDSplit() {
		return randomUUID().replaceAll("-", "");
	}

	/**
	 * 产生随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static void main(String[] args) {
		int size = 453;
		int serial = size / 200;
		int remain = size - serial * 200;
		System.out.println(serial);
		System.out.println(remain);
	}

	/**
	 * 解码 @Description：
	 * 
	 * @author: 何帅斌
	 * @date: 2016年6月7日 下午6:14:19
	 * @param text
	 * @return
	 */
	public static Object unescapeJs(String text) {
		if (StringUtils.isEmpty(text)) {
			return "";
		}
		ScriptEngineManager sem = new ScriptEngineManager();

		ScriptEngine engine = sem.getEngineByExtension("js");
		Object res = null;
		try {
			// 直接解析
			res = engine.eval(" unescape('" + text + "')");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	/**
	 * 编码 @Description：
	 * 
	 * @author: 何帅斌
	 * @date: 2016年6月7日 下午6:13:49
	 * @param text
	 * @return
	 */
	public static Object escapeJs(String text) {
		if (StringUtils.isEmpty(text)) {
			return "";
		}
		ScriptEngineManager sem = new ScriptEngineManager();

		ScriptEngine engine = sem.getEngineByExtension("js");
		Object res = null;
		try {
			// 直接解析
			res = engine.eval(" escape('" + text + "')");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	/**
	 * 产生随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static final String randomNumber(int length) {
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
		}
		return new String(randBuffer);
	}

	/**
	 * 汇付接口地址
	 * 
	 * @param url
	 * @param obj
	 * @param classPath
	 * @return
	 */
	public static String hfUrl(String url, Object obj, String classPath) {
		StringBuffer sb = new StringBuffer();
		try {
			Field[] fds = Class.forName(classPath).getDeclaredFields();
			for (int i = 0; i < fds.length; i++) {
				Field field = fds[i];
				field.setAccessible(true);
				String v = (String) field.get(obj);
				if (StringUtils.isNotEmpty(v)) {
					String name = field.getName();
					String firstStr = name.substring(0, 1).toUpperCase();
					name = new StringBuffer(firstStr).append(name.substring(1)).toString();
					sb.append("&").append(name).append("=").append(v);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		String result = sb.toString();
		if (StringUtils.isNotEmpty(result)) {
			result = result.substring(1);
			sb = new StringBuffer(result);
			result = sb.insert(0, "?").insert(0, url).toString();
		} else {
			result = url;
		}
		return result;
	}

	/**
	 * 获取新浪签名明文
	 * 
	 * @param obj
	 * @param classPath
	 * @return
	 */
	public static String getSinaPaySignMsg(Object obj, String classPath) {
		StringBuffer sb = new StringBuffer();
		try {
			Field[] fds = Class.forName(classPath).getDeclaredFields();
			for (int i = 0; i < fds.length; i++) {
				Field field = fds[i];
				field.setAccessible(true);
				String v = (String) field.get(obj);
				if (StringUtils.isNotEmpty(v)) {
					String name = field.getName();
					sb.append("&").append(name).append("=").append(v);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return sb.toString().substring(1);
	}

	/**
	 * 获取易宝签名明文
	 * 
	 * @param obj
	 * @param classPath
	 * @return
	 */
	public static String getYeePayHmacStr(Object obj, String classPath) {
		StringBuffer sb = new StringBuffer();
		try {
			Field[] fds = Class.forName(classPath).getDeclaredFields();
			for (int i = 0; i < fds.length; i++) {
				Field field = fds[i];
				field.setAccessible(true);
				String v = (String) field.get(obj);
				if (StringUtils.isNotEmpty(v)) {
					sb.append(v);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * 对象属性转换成map存储
	 * 
	 * @param obj
	 * @param classPath
	 * @return
	 */
	public static Map<String, String> objParseMap(Object obj, String classPath, Boolean flag) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			// 获取类属性
			Field[] fds = Class.forName(classPath).getDeclaredFields();
			for (int i = 0; i < fds.length; i++) {
				Field field = fds[i];
				field.setAccessible(true);
				String v = (String) field.get(obj);
				if (StringUtils.isNotEmpty(v)) {
					String name = field.getName();
					if (flag) {
						String firstStr = name.substring(0, 1).toUpperCase();
						name = new StringBuffer(firstStr).append(name.substring(1)).toString();
					}
					result.put(name, v);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 对象和父类属性转换成map存储
	 * 
	 * @param obj
	 * @param classPath
	 * @param flag
	 * @return
	 */
	public static Map<String, String> objParseMap2(Object obj, String classPath, Boolean flag) {
		Map<String, String> result = objParseMap(obj, classPath, flag);
		try {
			// 获取父类属性
			Field[] pFds = obj.getClass().getSuperclass().getDeclaredFields();
			for (int j = 0; j < pFds.length; j++) {
				Field field = pFds[j];
				field.setAccessible(true);
				String v = (String) field.get(obj);
				if (StringUtils.isNotEmpty(v)) {
					String name = field.getName();
					if (flag) {
						String firstStr = name.substring(0, 1).toUpperCase();
						name = new StringBuffer(firstStr).append(name.substring(1)).toString();
					}
					result.put(name, v);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String parseAmount(String amount) {
		DecimalFormat df = new DecimalFormat("#.00");
		Double number = Double.valueOf(amount);
		return df.format(number);
	}

	public static String parseStrCode(String str, String source, String pattern) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		try {
			str = new String(str.getBytes(source), pattern);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 分转换为元
	 * 
	 * @param fen
	 * @return
	 */
	public static String fromFenToYuan(final String fen) {
		String yuan = "0";
		final int MULTIPLIER = 100;
		Pattern pattern = Pattern.compile("^[1-9][0-9]*{1}");
		Matcher matcher = pattern.matcher(fen);
		if (matcher.matches()) {
			yuan = new BigDecimal(fen).divide(new BigDecimal(MULTIPLIER)).setScale(2).toString();
		}
		return yuan;
	}

	/**
	 * @Description：byte to string
	 * @author: dimmer
	 * @date: 2015年10月31日 下午9:26:55
	 * @param byteArray
	 * @return
	 */
	public static String toString(byte[] byteArray) {
		return new String(byteArray);
	}

	/**
	 * 元转换为分
	 * 
	 * @param yuan
	 * @return
	 */
	public static String fromYuanToFen(final String yuan) {
		Double d = Double.valueOf(yuan);
		DecimalFormat df = new DecimalFormat("0.00");
		String parseYuan = df.format(d);
		// 返回
		String fen = "0";
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");
		Matcher matcher = pattern.matcher(parseYuan);
		if (matcher.matches()) {
			try {
				NumberFormat format = NumberFormat.getInstance();
				Number number = format.parse(parseYuan);
				double temp = number.doubleValue() * 100.0;
				// 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012
				format.setGroupingUsed(false);
				// 设置返回数的小数部分所允许的最大位数
				format.setMaximumFractionDigits(0);
				fen = format.format(temp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return fen;
	}

	/**
	 * 判断字符串为空
	 * 
	 * @param str
	 * @return true:不为空 false：为空
	 */
	public static boolean isNotNull(String str) {
		if (str == null || str == "") {
			return false;
		} else {
			if (str.equals("") || str.equals("null"))
				return false;
		}
		return true;
	}

	/**
	 * 拼接字符串
	 * 
	 * @param cs
	 * @param value
	 */
	public static void createCs(StringBuffer cs, String value) {
		// 判判断是否为空
		if (StringUtils.isNotEmpty(value)) {
			// 去掉空格
			String parseValue = value.trim();
			cs.append(parseValue);
		}
	}

	/**
	 * @Description：匹配表达式
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月24日 上午9:14:16
	 * @param srcStr
	 * @param patternStr
	 * @return
	 */
	public static Boolean match(String srcStr, String patternStr) {
		if (StringUtils.isEmpty(srcStr)) {
			return Boolean.FALSE;
		}
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(srcStr);
		return matcher.matches();
	}

	/**
	 * @Description：截取字符串的前N位
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月29日 下午3:30:28
	 * @param data
	 * @param before
	 * @return
	 */
	public static String cut(String data, Integer before) {
		if (StringUtils.isEmpty(data)) {
			return "";
		}
		return data.substring(before);
	}

	/**
	 * @Description：转换为整数
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月29日 下午3:39:44
	 * @param data
	 * @return
	 */
	public static Integer toInt(String data) {
		if (StringUtils.isEmpty(data)) {
			return 0;
		}
		return Integer.parseInt(data);
	}

	/**
	 * @Description：转换为浮点数
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月29日 下午3:39:57
	 * @param data
	 * @return
	 */
	public static BigDecimal toBigDecimal(String data) {
		if (StringUtils.isEmpty(data)) {
			return new BigDecimal(0);
		}
		return new BigDecimal(data);
	}

	/**
	 * @Description：是否幸运
	 * @example:
	 * @author: dimmer
	 * @date: 2016年2月1日 下午2:59:21
	 * @param maxNum
	 * @param luckyNum
	 * @return
	 */
	public static Boolean isLucky(Integer maxNum, Integer luckyNum) {
		Integer rand = new Random().nextInt(maxNum);
		if (rand < luckyNum) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @Description：随机数
	 * @example:
	 * @author: dimmer
	 * @date: 2016年2月2日 下午4:34:29
	 * @param maxNum
	 * @return
	 */
	public static Integer luckyNumber(Integer maxNum) {
		return new Random().nextInt(maxNum);
	}

	/**
	 * @Description：组装机构码
	 * @example:
	 * @author: dimmer
	 * @date: 2016年3月29日 上午8:49:36
	 * @param orgCode
	 * @return
	 */
	public static String convertOrgCode(String orgCode) {
		// 如果orgCode不为空，且是已‘qrscene_’开头的，去除qrscene_
		if (StringUtils.isNoneEmpty(orgCode) && orgCode.startsWith("qrscene_")) {
			orgCode = orgCode.replace("qrscene_", "");
		}
		return orgCode;
	}
}
