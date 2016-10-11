/**
 * @Description:
 * @author dimmer
 * @date 2015年10月26日 下午4:34:04
 *
 */
package com.base.util;

/**
 * 
 * @ProjectName：giftplat
 * @ClassName：Constants @Description：
 * @author: dimmer
 * @date: 2015年10月26日 下午4:34:04
 * 
 */
public class Constant {
	/**
	 * 公众号
	 */
	public static final String CHANNEL_FOLLOW = "CH001";
	/**
	 * 扫码
	 */
	public static final String CHANNEL_QRCODE = "CH002";
	/**
	 * 通过链接进来
	 */
	public static final String CHANNEL_MENU = "CH003";
	/**
	 * 客户二维码
	 */
	public static final String QRCODE_TYPE_CUSTOMER = "CUS";
	/**
	 * 订单二维码
	 */
	public static final String QRCODE_TYPE_ORDER = "ORD";
	/**
	 * 广告二维码
	 */
	public static final String QRCODE_TYPE_ADVTISEMENT = "ADV";
	/**
	 * 礼物
	 */
	public static final String QRCODE_TYPE_GIFT = "GIF";
	/**
	 * 申派中
	 */
	public static final String CHECK_STATUS_APPLY = "申派中";
	/**
	 * 处理中
	 */
	public static final String CHECK_STATUS_PROCESS = "处理中";
	/**
	 * 已处理
	 */
	public static final String CHECK_STATUS_PROCESSED = "已处理";
	/**
	 * 已完成
	 */
	public static final String CHECK_STATUS_COMPLETE = "已完成";
	/**
	 * 已删除
	 */
	public static final String CHECK_STATUS_DELETE = "已删除";

	/**
	 * 故障附件
	 */
	public static final String ATTACH_TYPE_FAULT = "AT001";
	/**
	 * 反馈附件
	 */
	public static final String ATTACH_TYPE_FEEDBACK = "AT002";
	/**
	 * 帮助
	 */
	public static final String ATTACH_TYPE_HELP = "AT003";
	/**
	 * 关键字
	 */
	public static final String ATTACH_TYPE_KEYWORD = "AT004";
	/**
	 * 产品
	 */
	public static final String ATTACH_TYPE_PRODUCT = "AT005";
	public static final String ATTACH_TYPE_ORDER = "AT006";
	/**
	 * 报表:订单
	 */
	public static final String ATTACH_TYPE_REPORT_ORDER = "order";
	/**
	 * 报表:售后
	 */
	public static final String ATTACH_TYPE_REPORT_FAULT = "fault";
	/**
	 * 报表:客户
	 */
	public static final String ATTACH_TYPE_REPORT_CUSTOMER = "customer";
	/**
	 * 报表:产品
	 */
	public static final String ATTACH_TYPE_REPORT_PRODUCT = "product";
	public static final String ATTACH_TYPE_REPORT_KEYWORD = "keyword";

	/**
	 * 礼物相关 消息类型
	 */
	public static final String MSG_TYPE_GIFT = "gift";
	/**
	 * 其他消息类型
	 */
	public static final String MSG_TYPE_ANY = "any";

	/**
	 * 文章相关消息类型
	 */
	public static final String MSG_TYPE_ARTICLE = "article";

	/**
	 * 充值
	 */
	public static final String MSG_TYPE_CHARGE = "charge";

	/**
	 * 审核中
	 */
	public static final String WAIT = "CST001";
	/**
	 * 通过
	 */
	public static final String PASS = "CST002";
	/**
	 * 拒绝
	 */
	public static final String REJECT = "CST003";
	/**
	 * 删除
	 */
	public static final String DELETE = "CST004";
	/**
	 * 是
	 */
	public static final String YES = "1";
	/**
	 * 否
	 */
	public static final String NO = "2";
	/**
	 * @Fields EVN: 环境
	 *
	 */
	public static final String EVN_DEV = "DEV";
	/**
	 * @Fields EVN: 环境
	 *
	 */
	public static final String EVN_LINE = "LINE";
	/**
	 * @Fields ARITH_RANDOM: 随机算法
	 *
	 */
	public static final String ARITH_RANDOM = "ARITH_RANDOM";
	/**
	 * @Fields LINK_STATUS_READ: 链接已读
	 *
	 */
	public static final String LINK_STATUS_READ = "LS02";
	/**
	 * @Fields LINK_STATUS_NOT_READ: 链接未读
	 *
	 */
	public static final String LINK_STATUS_NOT_READ = "LS01";
	/**
	 * @Fields DOMAIN_TYPE: 业务类型，默认发红包
	 *
	 */
	public static final String DOMAIN_TYPE = "DT0101";
	/**
	 * @Fields DOMAIN_TYPE_GET: 抢红包
	 *
	 */
	public static final String DOMAIN_TYPE_GET = "DT0102";
	/**
	 * @Fields DOMAIN_TYPE_LIST: 红包广场
	 *
	 */
	public static final String DOMAIN_TYPE_LIST = "DT0103";
	/**
	 * @Fields DOMAIN_TYPE_CHARGE: 充值
	 *
	 */
	public static final String DOMAIN_TYPE_CHARGE = "DT0104";
	/**
	 * @Fields TRADE_TRANSFER: 转账
	 *
	 */
	public static final String TRADE_TRANSFER = "TRANSFER";

	/**
	 * @Fields TRADE_MONEY: 红包
	 *
	 */
	public static final String TRADE_MONEY = "MONEY";
	/**
	 * @Fields ORG_CODE: 机构码
	 *
	 */
	public static final String ORG_CODE = "webao";
	/**
	 * @Fields ORG_CODE_TYPE: 机构码类型
	 *
	 */
	public static final String ORG_CODE_TYPE = "orgCode";
	/**
	 * @Fields ORG_CODE_VALUE: 分配的本系统结构码
	 *
	 */
	public static final String ORG_CODE_VALUE = "76d19cae50d6893aa57a02554ca63ba3";
	/**
	 * @Fields MSG_TYPE_DAILY: 消息类型：日消息
	 *
	 */
	public static final String MSG_TYPE_DAILY = "MTD0101";

	/**
	 * @Fields SEND_MSG_TMP: 发送红包消息模板
	 *
	 */
	public static final String SEND_MSG_TMP = "sendMsgTmp";
	/**
	 * @Fields getMsgTpm: 抢红包消息模板
	 *
	 */
	public static final String GET_MSG_TPM = "getMsgTpm";
	/**
	 * @Fields SEND_MSG_TMP: 回复消息模板
	 *
	 */
	public static final String REPLAY_MSG_TMP = "replyTmp";
	/**
	 * @Fields BUSINESS_OPERATOR: 运营人员
	 *
	 */
	public static final String BUSINESS_OPERATOR = "businessOperator";

	/**
	 * @Fields TOKEN_NAME: token名称
	 *
	 */
	public static final String TOKEN_NAME = "wechatToken";

	/**
	 * @Fields ENABLED: 启用状态
	 *
	 */
	public static final String ENABLED = "ST0101";

	/**
	 * @Fields DISENABLED: 禁用状态
	 *
	 */
	public static final String DISENABLED = "ST0102";

	/**
	 * 红包类型： 天使红包
	 */
	public static final String GIFT_SEND_METHOD_ANGLE = "1";

	/**
	 * 红包类型：私人订制红包
	 */
	public static final String GIFT_SEND_METHOD_PERSONAL = "2";

	/**
	 * 红包类型:定向红包
	 */
	public static final String GIFT_SEND_METHOD_ORIENTATION = "3";
	/**
	 * @Fields GIFT_SEND_METHOD_CONDITION: 有条件的红包
	 *
	 */
	public static final String GIFT_SEND_METHOD_CONDITION = "5";

	/**
	 * 消息 未读状态
	 */
	public static final String MSG_STATUS_NOT_READ = "1";

	/**
	 * 消息 已读状态
	 */
	public static final String MSG_STATUS_HAS_READED = "2";

	// 查询用
	/**
	 * 正在发送的红包
	 */
	public static final String GIFT_TYPE_SENDING = "0";

	/**
	 * 接收的红包
	 */
	public static final String GIFT_TYPE_GET = "1";

	/**
	 * 全部发送的红包
	 */
	public static final String GIFT_TYPE_SEND_ALL = "2";

	/**
	 * 发送的红包
	 */
	public static final String GIFT_TYPE_SEND = "3";

	/**
	 * 发送状态
	 */
	public static final String SEND_STATUS_SEND = "1";
	/**
	 * 发送结束状态
	 */
	public static final String SEND_STATUS_OVER = "2";

	/**
	 * @Fields CACHE_WECHAT_CODE: 微信code缓存
	 *
	 */
	public static final String CACHE_WECHAT_CODE = "wechatcode";
	/**
	 * @Fields CACHE_GETGIFTLIST: 获取礼物详情页面
	 *
	 */
	public static final String CACHE_GETGIFTLIST = "getGiftList";
	/**
	 * @Fields CACHE_GETGIFTLIST: 下次红包
	 *
	 */
	public static final String CACHE_NEXTGIFT = "nextGift";
	/**
	 * @Fields MEDIA_ORG: 机构
	 *
	 */
	public static final String CACHE_MEDIA_ORG = "mediaorg";

	/**
	 * @Fields CACHE_ALIVE_CUSTOMER: 活跃客户
	 *
	 */
	public static final String CACHE_ALIVE_CUSTOMER = "alivecustomer";
	/**
	 * @Fields CACHE_GIFTPARAM: 礼品参数缓存
	 *
	 */
	public static final String CACHE_GIFTPARAM = "giftparam";

	/**
	 * @Fields CACHE_SYSWISHES: 祝福语
	 *
	 */
	public static final String CACHE_SYSWISHES = "syswishes";

	/**
	 * @Fields CACHE_GET_GIFT: 获取礼物信息
	 *
	 */
	public static final String CACHE_GET_GIFT = "getgift";
	/**
	 * @Fields CACHE_SYS_DIC: 数据字典
	 *
	 */
	public static final String CACHE_SYS_DIC = "sysdic";

	/**
	 * @Fields MAX_CONCURRENCY: 最大并发数
	 *
	 */
	public static final String MAX_CONCURRENCY = "maxConcurrency";

	/***************** 文章操作（点赞、阅读、收藏） ****************/
	/**
	 * 阅读
	 */
	public static final Integer ARTICLE_OPERATE_TYPE_READ = 1;
	/**
	 * 点赞
	 */
	public static final Integer ARTICLE_OPERATE_TYPE_LIKE = 2;

	/**
	 * 收藏
	 */
	public static final Integer ARTICLE_OPERATE_TYPE_STORE = 3;

	/************ 红包是否可分享 ***************/
	/**
	 * 可分享
	 */
	public static final String GIFT_SHARE_ALLOWED = "1";

	/**
	 * 不可分享
	 */
	public static final String GIFT_SHARE_NOT_ALLOWED = "2";
	/**
	 * 分享有礼
	 */
	public static final String GIFT_SHARE_ONLY = "3";

	/************ 收藏文章 ***************/
	/**
	 * 有效文章
	 */
	public static final String ARTICLE_STATUS_VALID = "1";

	/**
	 * 已删除的文章
	 */
	public static final String ARTICLE_STATUS_DELETE = "2";

	/************ 文章排序 ***************/
	/**
	 * 置顶排序
	 */
	public static final String ARTICLE_SORT_TOP = "1";

	/**
	 * 其他排序（现在默认是时间）
	 */
	public static final String ARTICLE_SORT_NORMAL = "2";
	/***************** 用户级别（1本周、2本月、3汇总） ****************/
	/**
	 * 本周
	 */
	public static final String REPORT_TYPE_WEEK = "1";
	/**
	 * 本月
	 */
	public static final String REPORT_TYPE_MONTH = "2";
	/**
	 * 汇总
	 */
	public static final String REPORT_TYPE_ALL = "3";

	/**
	 * @Fields DIC_CODE_ADMINTAG: 管理员标签
	 *
	 */
	public static final String DIC_CODE_ADMINTAG = "adminTag";

	/**
	 * @Fields DIC_CODE_OPERATORTAG: 运维标签
	 *
	 */
	public static final String DIC_CODE_OPERATORTAG = "operatorTag";
	/**
	 * @Fields DIC_CODE_ADMINOPENID: 管理员Openid
	 *
	 */
	public static final String DIC_CODE_ADMINOPENID = "adminOpenId";

	/***************** 文章审核状态（0提交状态、1审核通过、2审核不通过） ****************/
	/**
	 * 文章审核通过
	 */
	public static final String ARTICLE_STATUS_INIT = "0";
	/**
	 * 文章审核通过
	 */
	public static final String ARTICLE_STATUS_PASS = "1";
	/**
	 * 文章审核不通过
	 */
	public static final String ARTICLE_STATUS_REFUSE = "2";

	/***************** 建言审核状态（0提交状态、1审核通过、2审核不通过） ****************/
	/**
	 * 建言审核通过
	 */
	public static final String FEEDBACK_STATUS_INIT = "0";
	/**
	 * 建言审核通过
	 */
	public static final String FEEDBACK_STATUS_PASS = "1";
	/**
	 * 建言审核不通过
	 */
	public static final String FEEDBACK_STATUS_REFUSE = "2";

	/******************** 企业版领取金钱类型 ******************/
	/**
	 * 该类型不记录流水 如抢红包
	 */
	public static final String CONSUME_TYPE_NORECORD = "NO";
	/**
	 * @Fields CONSUME_TYPE: 发红包
	 *
	 */
	public static final String CONSUME_TYPE_SEND_GIFT = "C1";
	/**
	 * @Fields CONSUME_TYPE_ARTICLE:文章奖励
	 *
	 */
	public static final String CONSUME_TYPE_ARTICLE = "C2";
	/**
	 * @Fields CONSUME_TYPE_ARTICLE_ACCEPT: 文章被采纳奖励
	 *
	 */
	public static final String CONSUME_TYPE_ARTICLE_ACCEPT = "C3";
	/**
	 * @Fields CONSUME_TYPE_FORWARD: 转发奖励
	 *
	 */
	public static final String CONSUME_TYPE_FORWARD = "C4";
	/**
	 * 建言审核奖励
	 */
	public static final String CONSUME_TYPE_FEEDBACK = "C5";
	/**
	 * @Fields CONSUME_TYPE_FORWARD: 关注奖励
	 *
	 */
	public static final String CONSUME_TYPE_REGIST = "C6";
	/**
	 * 充值
	 */
	public static final String CONSUME_TYPE_CHARGE = "C10";

	/******************** 客户级别 *****************/
	/**
	 * 管理员级别
	 */
	public static final String CUSTOMER_LEVEL_MANAGER = "1";
	/**
	 * 运维人员级别
	 */
	public static final String CUSTOMER_LEVEL_OPERATOR = "2";
	/**
	 * 店长级别
	 */
	public static final String CUSTOMER_LEVEL_SHOPMANAGER = "3";
	/**
	 * 销售级别
	 */
	public static final String CUSTOMER_LEVEL_SALLER = "4";
	/**
	 * 售后级别
	 */
	public static final String CUSTOMER_LEVEL_AFTERSALE = "5";
	/**
	 * 取消授权或者普通员工
	 */
	public static final String CUSTOMER_LEVEL_UNAUTH = "10";
	/**
	 * 注册时的默认级别
	 */
	public static final String CUSTOMER_LEVEL_DEFAULT = "0";

	/**
	 * 获得职位的描述
	 * 
	 * @param level
	 * @return
	 */
	public static String getLevelDescript(String level) {
		switch (level) {
		case CUSTOMER_LEVEL_AFTERSALE:
			return "售后";
		case CUSTOMER_LEVEL_MANAGER:
			return "管理员";
		case CUSTOMER_LEVEL_OPERATOR:
			return "运维";
		case CUSTOMER_LEVEL_SALLER:
			return "销售";
		case CUSTOMER_LEVEL_SHOPMANAGER:
			return "店长";
		default:
			break;
		}
		return "VIP";
	}

	/**
	 * @Fields TAG_ADMIN: 管理员标签
	 *
	 */
	public static final String TAG_ADMIN = "admin";
	/**
	 * @Fields TAG_OPERATOR: 运营标签
	 *
	 */
	public static final String TAG_OPERATOR = "operator";

	/********************* 微信菜单type标签 ****************/
	/**
	 * 菜单的响应动作类型 (跳转url)
	 */
	public static final String MENU_TYPE_VIEW = "view";

	/**
	 * 点击推事件 用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event
	 * 的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
	 */
	public static final String MENU_TYPE_CLICK = "click";

	/**
	 * scancode_push：扫码推事件
	 * 用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，
	 * 开发者可以下发消息。
	 */
	public static final String MENU_TYPE_SCANCODE_PUSH = "scancode_push";

	/**
	 * 扫码推事件且弹出“消息接收中”提示框
	 * 用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，
	 * 随后可能会收到开发者下发的消息。
	 */
	public static final String MENU_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";

	/**
	 * 弹出系统拍照发图 用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，
	 * 随后可能会收到开发者下发的消息
	 */
	public static final String MENU_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
	/**
	 * 弹出拍照或者相册发图 用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
	 */
	public static final String MENU_TYPE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";

	/**
	 * 弹出微信相册发图器 用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，
	 * 随后可能会收到开发者下发的消息。
	 */
	public static final String MENU_TYPE_PIC_WEIXIN = "pic_weixin";

	/**
	 * 弹出地理位置选择器 用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，
	 * 随后可能会收到开发者下发的消息。
	 */
	public static final String MENU_TYPE_LOCATION_SELECT = "location_select";

	/**
	 * 下发消息（除文本消息）
	 * 用户点击media_id类型按钮后，微信服务器会将开发者填写的永久素材id对应的素材下发给用户，永久素材类型可以是图片、音频、视频、图文消息。
	 * 请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
	 */
	public static final String MENU_TYPE_MEDIA_ID = "media_id";

	/**
	 * 跳转图文消息URL
	 * 用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，永久素材类型只支持图文消息。
	 * 请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
	 */
	public static final String MENU_TYPE_VIEW_LIMITED = "view_limited";
	/********************* 微信菜单状态 ****************/
	/**
	 * 1 可用
	 */
	public static final String MENU_STATUS_ENABLE = "1";
	/**
	 * 2 不可用
	 */
	public static final String MENU_STATUS_DISENABLE = "2";
	/********************* 生成短连接类型 ****************/
	/**
	 * 1 抢红包分享链接类型
	 */
	public static final String LINK_TYPE_GIFT = "GIFT";
	/**
	 * 积分类型：签到--1
	 */
	public static final String SCORE_TYPE_SIGN = "签到";
	/**
	 * 留言：2
	 */
	public static final String SCORE_TYPE_COMMENT = "留言";
	/**
	 * 阅读：1
	 */
	public static final String SCORE_TYPE_READ = "阅读";
	/**
	 * 实名认证：10
	 */
	public static final String SCORE_TYPE_REAL = "实名认证";
	/**
	 * 打开服务号：1
	 */
	public static final String SCORE_TYPE_OPEN = "打开服务号";
	/**
	 * 上传文章：2
	 */
	public static final String SCORE_TYPE_ARTICLE = "上传文章";
	/**
	 * 分享：3
	 */
	public static final String SCORE_TYPE_SHARE = "分享";

	/**
	 * 回复类型:文字
	 */
	public static final String REPLY_TYPE_CENTNET = "RTC0101";
	/**
	 * 图片
	 */
	public static final String REPLY_TYPE_PIC = "RTC0102";
	/**
	 * 链接
	 */
	public static final String REPLY_TYPE_URL = "RTC0103";
}
