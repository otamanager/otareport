/**
 * 
 */
package com.data;

import java.util.List;

import com.base.util.ReportUtil;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author dimmer
 *
 */
public class ReportApi extends Controller {
	public void index() {
		String key = getPara("key");
		List<Record> datas = Db.find("SELECT	*,	LEFT (submitTime, 10) as time FROM	ept_report WHERE	instance = '" + key
				+ "' GROUP BY	LEFT (submitTime, 10) ORDER BY	createTime");
		setAttr("customer", ReportUtil.formJson(datas, "time", "customerCount"));
		setAttr("order", ReportUtil.formJson(datas, "time", "orderCount"));
		setAttr("score", ReportUtil.formJson(datas, "time", "scoreCount"));
		setAttr("fault", ReportUtil.formJson(datas, "time", "faultCount"));
		setAttr("staff", ReportUtil.formJson(datas, "time", "staffCount"));
		setAttr("menu", ReportUtil.formJson(datas, "time", "menuCount"));
		setAttr("text", ReportUtil.formJson(datas, "time", "textCount"));
		render("/report/report.html");
	}
}
