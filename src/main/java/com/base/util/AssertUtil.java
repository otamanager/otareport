package com.base.util;

public class AssertUtil {

	public static boolean isNull(Object obj) {
		return obj == null ? true : false;
	}

	public static boolean isNotNull(Object obj) {
		return obj != null ? true : false;
	}

	public static boolean isEmptyText(String str) {
		return (str == null || str.trim().equals("")) ? true : false;
	}
}
