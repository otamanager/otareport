/**
 * @Description:
 * @author dimmer
 * @date 2016年1月14日 上午9:10:42
 *
 */
package com.jfinal.extend;

import java.util.List;

import com.base.util.StringUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * 
 * @ProjectName：contestplat
 * @ClassName：WebaoModel @Description：
 * @author: dimmer
 * @date: 2016年1月14日 上午9:10:42
 * 
 */
public abstract class WebaoModel<M extends WebaoModel<M>> extends Model<M> {

	/**
	 * @Description：保存
	 * @example:
	 * @author: dimmer
	 * @date: 2016年1月14日 上午9:14:00
	 * @return
	 */
	public String store() {
		String pk = StringUtil.randomUUIDSplit();
		this.set("id", pk);
		save();
		return pk;
	}

	/**
	 * 报表查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Record> report(String sql) {
		return Db.find(sql);
	}

	/**
	 * 批量保存
	 * 
	 * @param modelList
	 */
	public void batchSave(List modelList) {
		// 判断list的大小,如果大于200条,则size/200分批次保存
		int size = modelList.size();
		if (200 >= size) {
			Db.batchSave(modelList, size);
			return;
		}
		int serial = size / 200;
		for (int i = 0; i < serial; i++) {
			Db.batchSave(modelList.subList(i * 200, i * 200 + 200), 200);
		}
		int remain = size - serial * 200;
		Db.batchSave(modelList.subList(serial * 200, size), remain);
	}
}
