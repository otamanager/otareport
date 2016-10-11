/**
 * 
 */
package com.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Record;

/**
 * @author dimmer
 *
 */
public class ReportUtil {
	public static List<Map<String, Object>> formData(List<Record> datas, String label, String value) {
		List<Map<String, Object>> list = new ArrayList<>();
		if (null == datas || 0 == datas.size()) {
			return list;
		}
		for (Record data : datas) {
			Map<String, Object> entity = new HashMap<>();
			entity.put("label", data.get(label));
			entity.put("value", data.get(value));
			list.add(entity);
		}
		return list;
	}

	public static String formJson(List<Record> datas, String label, String value) {
		StringBuffer result = new StringBuffer("[");
		if (null == datas || 0 == datas.size()) {
			return "[]";
		}
		for (Record data : datas) {
			result.append("{").append(formJsonString("label")).append(":").append(formJsonString(data.get(label))).append(",")
					.append(formJsonString("value")).append(":").append(formJsonString(data.get(value))).append("},");
		}
		result.append("]");
		return result.toString();
	}

	public static String formJsonString(Object data) {
		return new StringBuffer("\"").append(data).append("\"").toString();
	}
}
