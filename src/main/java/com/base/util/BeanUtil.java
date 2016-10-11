/**
 * 
 */
package com.base.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 *
 */
public class BeanUtil {

	public static <T> Map<String, Object> bean2Map(T o) throws Exception {
		if (null == o) {
			return null;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			// 过滤class属性
			if (!key.equals("class")) {
				// 得到property对应的getter方法
				Method getter = property.getReadMethod();
				Object value = getter.invoke(o);
				if (null != value) {
					result.put(key, value);
				}
			}
		}
		return result;
	}

	/**
	 * 将map转换为bean
	 * 
	 * @param type
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static Object map2Bean(Class type, Map map) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;

				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	/**
	 * 将map转换为bean列表
	 * 
	 * @param type
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<Object> map2BeanList(Class type, List<Map<String, Object>> list) throws Exception {
		List<Object> objList = new ArrayList<Object>();
		if (null == list || 0 == list.size()) {
			return objList;
		}
		for (Map map : list) {
			objList.add(map2Bean(type, map));
		}
		return objList;
	}

	public static <T> String bean2String(T o) throws Exception {
		if (null == o) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			// 过滤class属性
			if (!key.equals("class")) {
				// 得到property对应的getter方法
				Method getter = property.getReadMethod();
				Object value = getter.invoke(o);
				if (null != value) {
					str.append(key);
					str.append("=");
					str.append(value);
					str.append("&");
				}
			}
		}
		return str.toString();
	}

	/**
	 * 生成待签名串
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String genSignData(JSONObject jsonObject) {
		StringBuffer content = new StringBuffer();

		// 按照key做首字母升序排列
		List<String> keys = new ArrayList<String>(jsonObject.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			if ("sign".equals(key)) {
				continue;
			}
			String value = jsonObject.getString(key);
			// 空串不参与签名
			if (null == value || "".endsWith(value)) {
				continue;
			}
			content.append((i == 0 ? "" : "&") + key + "=" + value);

		}
		String signSrc = content.toString();
		if (signSrc.startsWith("&")) {
			signSrc = signSrc.replaceFirst("&", "");
		}
		return signSrc;
	}
}
