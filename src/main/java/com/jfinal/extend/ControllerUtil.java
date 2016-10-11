/**
 * 
 */
package com.jfinal.extend;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.jfinal.core.Controller;

/**
 * @author dimmer
 *
 */
public class ControllerUtil {
	/**
	 * 设置入参为出参
	 * 
	 * @param me
	 */
	public static void setAttrs(Controller me) {
		me.setAttrs(toMap(me.getParaMap()));
	}

	/**
	 * 转换Map
	 * 
	 * @param params
	 * @return
	 */
	public static Map<String, Object> toMap(Map<String, String[]> params) {
		Map<String, Object> datas = new HashMap<>();
		if (null == params) {
			return datas;
		}
		Set<String> keys = params.keySet();
		Iterator<String> keyIt = keys.iterator();
		while (keyIt.hasNext()) {
			String key = keyIt.next();
			datas.put(key, params.get(key)[0]);
		}
		return datas;
	}
}
