package com.base.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean || obj instanceof Short
				|| obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger
				|| obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	// ����ӵķ��� bywang
	public static String list2json2(List<?> list) {
		StringBuilder json = new StringBuilder();
		// json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(";");
			}
			// json.setCharAt(json.length() - 1, ']');
		} else {
			// json.append("]");
		}
		return json.toString();
	}

	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String string2json(String s) {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	public static JSONObject searchJSONObject(JSONObject jsonObject, String path) {
		if (AssertUtil.isNull(jsonObject) || AssertUtil.isEmptyText(path)) {
			return null;
		}
		JSONObject result = null;

		String[] nodes = path.trim().split("\\.");
		for (int i = 0; i < nodes.length; i++) {
			if (i == 0) {
				result = jsonObject.getJSONObject(nodes[i]);
			} else {
				result = result.getJSONObject(nodes[i]);
			}
			if (result == null) {
				break;
			}
		}
		return result;
	}

	public static JSONArray searchJSONArray(JSONObject jsonObject, String path) {
		if (AssertUtil.isNull(jsonObject) || AssertUtil.isEmptyText(path)) {
			return null;
		}
		JSONArray result = null;

		String[] nodes = path.trim().split("\\.");
		JSONObject temp = null;
		for (int i = 0; i < nodes.length; i++) {
			if (i == 0) {
				if (i == nodes.length - 1) {
					result = jsonObject.getJSONArray(nodes[i]);
					break;
				} else {
					temp = jsonObject.getJSONObject(nodes[i]);
				}
			} else {
				if (i == nodes.length - 1) {
					result = temp.getJSONArray(nodes[i]);
					break;
				} else {
					temp = temp.getJSONObject(nodes[i]);
				}
			}
			if (temp == null) {
				break;
			}
		}
		return result;
	}

	public static <T> T toObject(JSONObject jsonObj, Class<T> clazz) {
		if (AssertUtil.isNull(jsonObj) || clazz == null) {
			throw new IllegalArgumentException("Arguments of jsonObj and clazz must not null");
		}
		return GsonUtil.fromJson(jsonObj.toJSONString(), clazz);
	}

	public static <T> List<T> toList(JSONArray jsonArr, Class<T> clazz) {
		if (AssertUtil.isNull(jsonArr) || clazz == null) {
			throw new IllegalArgumentException("Arguments of jsonArr and clazz must not null");
		}
		int size = jsonArr.size();
		List<T> result = new ArrayList<T>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			if (jsonObj == null) {
				continue;
			}
			T obj = GsonUtil.fromJson(jsonObj.toJSONString(), clazz);
			result.add(obj);
		}
		return result;
	}

	public static String underscoreToCamel(String jsonText) {
		if (AssertUtil.isEmptyText(jsonText)) {
			return jsonText;
		}
		String regex = "(\")[a-zA-Z]+(_[a-zA-Z]+)+(\":)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(jsonText);
		Map<String, String> tokens = new HashMap<String, String>();
		while (matcher.find()) {
			String group = matcher.group();
			tokens.put(group, camelName(group));
		}
		matcher.reset();
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, tokens.get(matcher.group()));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String camelName(String name) {
		StringBuilder result = new StringBuilder();
		if (name == null || name.isEmpty()) {
			return "";
		} else if (!name.contains("_")) {
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		String camels[] = name.split("_");
		for (String camel : camels) {
			if (camel.isEmpty()) {
				continue;
			}
			if (result.length() == 0) {
				result.append(camel.toLowerCase());
			} else {
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	public static String keywordReplace(String jsonText) {
		if (AssertUtil.isEmptyText(jsonText)) {
			return jsonText;
		}
		String regex = "(\")((status)|(describe))(\":)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(jsonText);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			matcher.appendReplacement(sb, group.replace("\":", "Kw\":"));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
