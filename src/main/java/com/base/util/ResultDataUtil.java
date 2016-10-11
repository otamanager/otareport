package com.base.util;

import org.apache.commons.lang3.StringUtils;

import com.base.model.ResultData;

public class ResultDataUtil {

	/**
	 * 泛型Json转对象
	 * 
	 * @param data
	 * @param obj
	 * @return
	 */
	public static <T> Object fromJson(String data, T obj) {
		if (null == data) {
			return "";
		}
		ResultData result = GsonUtil.fromJson(data, ResultData.class);
		return GsonUtil.fromJson(GsonUtil.toJson(result.getData()), obj.getClass());
	}

	/**
	 * 字符串转换为resultdata
	 * 
	 * @param data
	 * @return
	 */
	public static ResultData fromJson(String data) {
		if (StringUtils.isEmpty(data)) {
			return ResultDataUtil.errorResult("数据为空");
		}
		return GsonUtil.fromJson(data, ResultData.class);
	}

	/**
	 * @Description 字符串转为resultdata并转码
	 * @example:
	 * @author: dimmer
	 * @date: 2016年5月31日 下午4:39:02
	 * @param data
	 * @return
	 */
	public static ResultData fromJsonWithUrlDecode(String data) {
		if (StringUtils.isEmpty(data)) {
			return ResultDataUtil.errorResult("数据为空");
		}
		data = StringUtil.urlDecode(data);
		return GsonUtil.fromJson(data, ResultData.class);
	}

	public static ResultData errorResult(String msg) {
		ResultData resultData = new ResultData();
		// resultData.setCode(-1);
		resultData.setStatus("0");
		resultData.setMessage(msg);
		resultData.setData(null);
		return resultData;
	}

	public static String error(String msg) {
		ResultData resultData = new ResultData();
		// resultData.setCode(-1);
		resultData.setStatus("0");
		resultData.setMessage(msg);
		resultData.setData(null);
		return GsonUtil.toJson(resultData);
	}

	public static String error(String msg, Object data) {
		ResultData resultData = new ResultData();
		// resultData.setCode(-1);
		resultData.setStatus("0");
		resultData.setMessage(msg);
		resultData.setData(data);
		return GsonUtil.toJson(resultData);
	}

	/**
	 * 错误的验证码。特殊标志11
	 * 
	 * @param msg
	 * @return
	 */
	public static ResultData errorCaptchaResult(String msg) {
		ResultData resultData = new ResultData();
		resultData.setCode(11);
		resultData.setStatus("0");
		resultData.setMessage(msg);
		resultData.setData(null);
		return resultData;
	}

	public static ResultData errorCaptchaResult(String msg, Object data) {
		ResultData resultData = new ResultData();
		resultData.setCode(11);
		resultData.setStatus("0");
		resultData.setMessage(msg);
		resultData.setData(data);
		return resultData;
	}

	public static ResultData errorResult(String msg, Object data) {
		ResultData resultData = new ResultData();
		// resultData.setCode(-1);
		resultData.setStatus("0");
		resultData.setMessage(msg);
		resultData.setData(data);
		return resultData;
	}

	public static ResultData errorCodeResult(int errCode, String msg) {
		ResultData resultData = new ResultData();
		resultData.setCode(errCode);
		resultData.setStatus("0");
		resultData.setMessage(msg);
		return resultData;
	}

	public static ResultData errorCodeResult(int errCode, String msg, Object data) {
		ResultData resultData = new ResultData();
		resultData.setCode(errCode);
		resultData.setStatus("0");
		resultData.setMessage(msg);
		resultData.setData(data);
		return resultData;
	}

	public static ResultData errorLoginResult(String msg, Object data) {
		ResultData resultData = new ResultData();
		resultData.setStatus("1001");
		resultData.setMessage(msg);
		resultData.setData(data);
		return resultData;
	}

	/**
	 * 认证失败
	 * 
	 * @param msg
	 * @return
	 */
	public static ResultData errorLoginResult(String msg) {
		ResultData resultData = new ResultData();
		resultData.setStatus("1001");
		resultData.setMessage(msg);
		resultData.setData(null);
		return resultData;
	}

	/**
	 * @Description：没有权限
	 * @example:
	 * @author: dimmer
	 * @date: 2016年6月7日 下午4:17:48
	 * @param msg
	 * @return
	 */
	public static ResultData errorAuthResult(String msg) {
		ResultData resultData = new ResultData();
		resultData.setStatus("1002");
		resultData.setMessage(msg);
		resultData.setData(null);
		return resultData;
	}

	/**
	 * @Description：token失效
	 * @example:
	 * @author: dimmer
	 * @date: 2016年6月7日 下午4:18:21
	 * @param msg
	 * @return
	 */
	public static ResultData errorTokenResult(String msg) {
		ResultData resultData = new ResultData();
		resultData.setStatus("1003");
		resultData.setMessage(msg);
		resultData.setData(null);
		return resultData;
	}

	/**
	 * @Description：未注册
	 * @example:
	 * @author: dimmer
	 * @date: 2016年6月7日 下午4:19:33
	 * @param msg
	 * @return
	 */
	public static ResultData errorRegisterResult(String msg, Object data) {
		ResultData resultData = new ResultData();
		resultData.setStatus("1004");
		resultData.setMessage(msg);
		resultData.setData(data);
		return resultData;
	}

	public static ResultData successResult(String msg, Object data) {
		ResultData resultData = new ResultData();
		// resultData.setCode(1);
		resultData.setStatus("1");
		if (msg != null) {
			resultData.setMessage(msg);
		}
		resultData.setData(data);
		return resultData;
	}

	public static ResultData successResult(String msg) {
		ResultData resultData = new ResultData();
		// resultData.setCode(1);
		resultData.setStatus("1");
		if (msg != null) {
			resultData.setMessage(msg);
		}
		return resultData;
	}

	public static String success(String msg, Object data) {
		ResultData resultData = new ResultData();
		// resultData.setCode(1);
		resultData.setStatus("1");
		if (msg != null) {
			resultData.setMessage(msg);
		}
		resultData.setData(data);
		return GsonUtil.toJson(resultData);
	}

	public static String success(String msg) {
		ResultData resultData = new ResultData();
		// resultData.setCode(1);
		resultData.setStatus("1");
		if (msg != null) {
			resultData.setMessage(msg);
		}
		return GsonUtil.toJson(resultData);
	}

	public static ResultData extendResult(String msg, Object data) {
		ResultData resultData = new ResultData();
		resultData.setStatus("2");
		resultData.setMessage(msg);
		resultData.setData(null);
		return resultData;
	}

	/**
	 * 是否成功
	 * 
	 * @param data
	 * @return
	 */
	public static Boolean isSuccess(ResultData data) {
		if (null == data) {
			return Boolean.FALSE;
		}
		if ("1".equalsIgnoreCase(data.getStatus())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
