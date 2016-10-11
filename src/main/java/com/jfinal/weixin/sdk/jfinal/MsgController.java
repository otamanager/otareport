/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NotAction;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.kit.MsgEncryptKit;
import com.jfinal.weixin.sdk.msg.InMsgParser;
import com.jfinal.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.weixin.sdk.msg.in.InLinkMsg;
import com.jfinal.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.weixin.sdk.msg.in.InMsg;
import com.jfinal.weixin.sdk.msg.in.InNotDefinedMsg;
import com.jfinal.weixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.weixin.sdk.msg.in.event.InCustomEvent;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMassEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMerChantOrderEvent;
import com.jfinal.weixin.sdk.msg.in.event.InNotDefinedEvent;
import com.jfinal.weixin.sdk.msg.in.event.InPoiCheckNotifyEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InShakearoundUserShakeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InSubmitMemberCardEvent;
import com.jfinal.weixin.sdk.msg.in.event.InTemplateMsgEvent;
import com.jfinal.weixin.sdk.msg.in.event.InUpdateMemberCardEvent;
import com.jfinal.weixin.sdk.msg.in.event.InUserPayFromCardEvent;
import com.jfinal.weixin.sdk.msg.in.event.InUserViewCardEvent;
import com.jfinal.weixin.sdk.msg.in.event.InVerifyFailEvent;
import com.jfinal.weixin.sdk.msg.in.event.InVerifySuccessEvent;
import com.jfinal.weixin.sdk.msg.in.event.InWifiEvent;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.OutMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.jfinal.weixin.sdk.session.WxSession;
import com.jfinal.weixin.sdk.session.WxSessionManager;

/**
 * 接收微信服务器消息，自动解析成 InMsg 并分发到相应的处理方法
 */
public abstract class MsgController extends Controller {

    private static final Log log = Log.getLog(MsgController.class);
    private String inMsgXml = null;        // 本次请求 xml数据
    private InMsg inMsg = null;            // 本次请求 xml 解析后的 InMsg 对象

    /**
     * weixin 公众号服务器调用唯一入口，即在开发者中心输入的 URL 必须要指向此 action
     */
    @Before(MsgInterceptor.class)
    public void index() {
        // 开发模式输出微信服务发送过来的  xml 消息
        if (ApiConfigKit.isDevMode()) {
            System.out.println("接收消息:");
            System.out.println(getInMsgXml());
        }

        // 解析消息并根据消息类型分发到相应的处理方法
        InMsg msg = getInMsg();
        
        if (msg instanceof InTextMsg)
            processInTextMsg((InTextMsg) msg);
        else if (msg instanceof InImageMsg)
            processInImageMsg((InImageMsg) msg);
        else if (msg instanceof InSpeechRecognitionResults)  //update by unas at 2016-1-29, 由于继承InVoiceMsg，需要在InVoiceMsg前判断类型
            processInSpeechRecognitionResults((InSpeechRecognitionResults) msg);
        else if (msg instanceof InVoiceMsg)
            processInVoiceMsg((InVoiceMsg) msg);
        else if (msg instanceof InVideoMsg)
            processInVideoMsg((InVideoMsg) msg);
        else if (msg instanceof InShortVideoMsg)   //支持小视频
            processInShortVideoMsg((InShortVideoMsg) msg);
        else if (msg instanceof InLocationMsg)
            processInLocationMsg((InLocationMsg) msg);
        else if (msg instanceof InLinkMsg)
            processInLinkMsg((InLinkMsg) msg);
        else if (msg instanceof InCustomEvent)
            processInCustomEvent((InCustomEvent) msg);
        else if (msg instanceof InFollowEvent)
            processInFollowEvent((InFollowEvent) msg);
        else if (msg instanceof InQrCodeEvent)
            processInQrCodeEvent((InQrCodeEvent) msg);
        else if (msg instanceof InLocationEvent)
            processInLocationEvent((InLocationEvent) msg);
        else if (msg instanceof InMassEvent)
            processInMassEvent((InMassEvent) msg);
        else if (msg instanceof InMenuEvent)
            processInMenuEvent((InMenuEvent) msg);
        else if (msg instanceof InTemplateMsgEvent)
            processInTemplateMsgEvent((InTemplateMsgEvent) msg);
        else if (msg instanceof InShakearoundUserShakeEvent)
            processInShakearoundUserShakeEvent((InShakearoundUserShakeEvent) msg);
        else if (msg instanceof InVerifySuccessEvent)
            processInVerifySuccessEvent((InVerifySuccessEvent) msg);
        else if (msg instanceof InVerifyFailEvent)
            processInVerifyFailEvent((InVerifyFailEvent) msg);
        else if (msg instanceof InPoiCheckNotifyEvent)
            processInPoiCheckNotifyEvent((InPoiCheckNotifyEvent) msg);
        else if (msg instanceof InWifiEvent)
            processInWifiEvent((InWifiEvent) msg);
        else if (msg instanceof InUserViewCardEvent)
            processInUserViewCardEvent((InUserViewCardEvent) msg);
        else if (msg instanceof InSubmitMemberCardEvent)
            processInSubmitMemberCardEvent((InSubmitMemberCardEvent) msg);
        else if (msg instanceof InUpdateMemberCardEvent)
            processInUpdateMemberCardEvent((InUpdateMemberCardEvent) msg);
        else if (msg instanceof InUserPayFromCardEvent)
            processInUserPayFromCardEvent((InUserPayFromCardEvent) msg);
        else if (msg instanceof InMerChantOrderEvent)
            processInMerChantOrderEvent((InMerChantOrderEvent) msg);
        else if (msg instanceof InNotDefinedEvent) {
            log.error("未能识别的事件类型。 消息 xml 内容为：\n" + getInMsgXml());
            processIsNotDefinedEvent((InNotDefinedEvent) msg);
        } else if (msg instanceof InNotDefinedMsg) {
            log.error("未能识别的消息类型。 消息 xml 内容为：\n" + getInMsgXml());
            processIsNotDefinedMsg((InNotDefinedMsg) msg);
        }
    }

    /**
     * 在接收到微信服务器的 InMsg 消息后后响应 OutMsg 消息
     *
     * @param outMsg 输出对象
     */
    public void render(OutMsg outMsg) {
        String outMsgXml = outMsg.toXml();
        // 开发模式向控制台输出即将发送的 OutMsg 消息的 xml 内容
        if (ApiConfigKit.isDevMode()) {
            System.out.println("发送消息:");
            System.out.println(outMsgXml);
            System.out.println("--------------------------------------------------------------------------------\n");
        }

        // 是否需要加密消息
        if (ApiConfigKit.getApiConfig().isEncryptMessage()) {
            outMsgXml = MsgEncryptKit.encrypt(outMsgXml, getPara("timestamp"), getPara("nonce"));
        }

        renderText(outMsgXml, "text/xml");
    }

    /**
     * 消息输出
     * @param content 输出的消息
     */
    public void renderOutTextMsg(String content) {
        OutTextMsg outMsg = new OutTextMsg(getInMsg());
        outMsg.setContent(content);
        render(outMsg);
    }

    @Before(NotAction.class)
    public String getInMsgXml() {
        if (inMsgXml == null) {
            inMsgXml = HttpKit.readData(getRequest());

            // 是否需要解密消息
            if (ApiConfigKit.getApiConfig().isEncryptMessage()) {
                inMsgXml = MsgEncryptKit.decrypt(inMsgXml, getPara("timestamp"), getPara("nonce"), getPara("msg_signature"));
            }
        }
        if (StrKit.isBlank(inMsgXml)) {
            throw new RuntimeException("请不要在浏览器中请求该连接,调试请查看WIKI:http://git.oschina.net/jfinal/jfinal-weixin/wikis/JFinal-weixin-demo%E5%92%8C%E8%B0%83%E8%AF%95");
        }
        return inMsgXml;
    }

    @Before(NotAction.class)
    public InMsg getInMsg() {
        if (inMsg == null)
            inMsg = InMsgParser.parse(getInMsgXml());
        return inMsg;
    }

    /**
     * 处理接收到的文本消息
     * @param inTextMsg 处理接收到的文本消息
     */
    protected abstract void processInTextMsg(InTextMsg inTextMsg);

    /**
     * 处理接收到的图片消息
     * @param inImageMsg 处理接收到的图片消息
     */
    protected abstract void processInImageMsg(InImageMsg inImageMsg);

    /**
     * 处理接收到的语音消息
     * @param inVoiceMsg 处理接收到的语音消息
     */
    protected abstract void processInVoiceMsg(InVoiceMsg inVoiceMsg);

    /**
     * 处理接收到的视频消息
     * @param inVideoMsg 处理接收到的视频消息
     */
    protected abstract void processInVideoMsg(InVideoMsg inVideoMsg);

    /**
     * 处理接收到的小视频消息
     * @param inShortVideoMsg 处理接收到的小视频消息
     */
    protected abstract void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg);

    /**
     * 处理接收到的地址位置消息
     * @param inLocationMsg 处理接收到的地址位置消息
     */
    protected abstract void processInLocationMsg(InLocationMsg inLocationMsg);

    /**
     * 处理接收到的链接消息
     * @param inLinkMsg 处理接收到的链接消息
     */
    protected abstract void processInLinkMsg(InLinkMsg inLinkMsg);

    /**
     * 处理接收到的多客服管理事件
     * @param inCustomEvent 处理接收到的多客服管理事件
     */
    protected abstract void processInCustomEvent(InCustomEvent inCustomEvent);

    /**
     * 处理接收到的关注/取消关注事件
     * @param inFollowEvent 处理接收到的关注/取消关注事件
     */
    protected abstract void processInFollowEvent(InFollowEvent inFollowEvent);

    /**
     * 处理接收到的扫描带参数二维码事件
     * @param inQrCodeEvent 处理接收到的扫描带参数二维码事件
     */
    protected abstract void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent);

    /**
     * 处理接收到的上报地理位置事件
     * @param inLocationEvent 处理接收到的上报地理位置事件
     */
    protected abstract void processInLocationEvent(InLocationEvent inLocationEvent);

    /**
     * 处理接收到的群发任务结束时通知事件
     * @param inMassEvent 处理接收到的群发任务结束时通知事件
     */
    protected abstract void processInMassEvent(InMassEvent inMassEvent);

    /**
     * 处理接收到的自定义菜单事件
     * @param inMenuEvent 处理接收到的自定义菜单事件
     */
    protected abstract void processInMenuEvent(InMenuEvent inMenuEvent);

    /**
     * 处理接收到的语音识别结果
     * @param inSpeechRecognitionResults 处理接收到的语音识别结果
     */
    protected abstract void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults);

    /**
     * 处理接收到的模板消息是否送达成功通知事件
     * @param inTemplateMsgEvent 处理接收到的模板消息是否送达成功通知事件
     */
    protected abstract void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent);

    /**
     * 处理微信摇一摇事件
     * @param inShakearoundUserShakeEvent 处理微信摇一摇事件
     */
    protected abstract void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent);

    /**
     * 资质认证成功 || 名称认证成功 || 年审通知 || 认证过期失效通知
     * @param inVerifySuccessEvent 资质认证成功 || 名称认证成功 || 年审通知 || 认证过期失效通知
     */
    protected abstract void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent);

    /**
     * 资质认证失败 || 名称认证失败
     * @param inVerifyFailEvent 资质认证失败 || 名称认证失败
     */
    protected abstract void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent);

    /**
     * 门店在审核事件消息
     * @param inPoiCheckNotifyEvent 门店在审核事件消息
     */
    protected abstract void processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent);

    /**
     * WIFI连网后下发消息 by unas at 2016-1-29
     * @param inWifiEvent WIFI连网后下发消息
     */
    protected abstract void processInWifiEvent(InWifiEvent inWifiEvent);

    /**
     * 微信会员卡二维码扫描领取接口
     * @param inUserViewCardEvent 微信会员卡二维码扫描领取接口
     */
    protected abstract void processInUserViewCardEvent(InUserViewCardEvent inUserViewCardEvent);

    /**
     * 微信会员卡激活接口
     * @param inSubmitMemberCardEvent 微信会员卡激活接口
     */
    protected abstract void processInSubmitMemberCardEvent(InSubmitMemberCardEvent inSubmitMemberCardEvent);

    /**
     * 微信会员卡积分变更
     * @param inUpdateMemberCardEvent 微信会员卡积分变更
     */
    protected abstract void processInUpdateMemberCardEvent(InUpdateMemberCardEvent inUpdateMemberCardEvent);

    /**
     * 微信会员卡快速买单
     * @param inUserPayFromCardEvent 微信会员卡快速买单
     */
    protected abstract void processInUserPayFromCardEvent(InUserPayFromCardEvent inUserPayFromCardEvent);

    /**
     * 微信小店订单支付成功接口消息
     * @param inMerChantOrderEvent 微信小店订单支付成功接口消息
     */
    protected abstract void processInMerChantOrderEvent(InMerChantOrderEvent inMerChantOrderEvent);

    //
    /**
     * 没有找到对应的事件消息
     * @param inNotDefinedEvent 没有对应的事件消息
     */
    protected abstract void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent);

    /**
     * 没有找到对应的消息
     * @param inNotDefinedMsg 没有对应消息
     */
    protected abstract void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg);
    
    /**
     * 微信会话维持session Id，也就是FromUserName
     */
    private String getWxSessionId() {
        if (null == inMsg) {
            throw new NullPointerException("weixin xml 尚未解析或者解析失败！");
        }
        // 用户id
        return inMsg.getFromUserName();
    }
    
    /**
     * Return WxSession.
     */
    public WxSession getWxSession() {
        String sessionId = getWxSessionId();
        // 默认getSession(true)
        if (null == sessionId) {
            throw new NullPointerException("weixin FromUserName is null！");
        }
        WxSessionManager sessionManager = ApiConfigKit.getWxSessionManager();
        WxSession session = sessionManager.get(sessionId);
        if (null == session) {
            session = new WxSession(sessionId);
            sessionManager.save(session);
        }
        // 由于sessionManager不参与序列化，加上序列化的问题，故手动设置
        session.setManager(sessionManager);
        return session;
    }
    
    /**
     * Return WxSession.
     * @param create a boolean specifying create WxSession if it not exists
     */
    public WxSession getWxSession(boolean create) {
        if (create) {
            return this.getWxSession();
        }
        String sessionId = getWxSessionId();
        if (null == sessionId) {
            return null;
        }
        WxSessionManager sessionManager = ApiConfigKit.getWxSessionManager();
        WxSession session = sessionManager.get(sessionId);
        if (null != session) {
            // 由于sessionManager不参与序列化，加上序列化的问题，故手动设置
            session.setManager(sessionManager);
        }
        return session;
    }
    
    /**
     * Return a Object from session.
     * @param key a String specifying the key of the Object stored in session
     */
    @SuppressWarnings("unchecked")
    public <T> T getWxSessionAttr(String key) {
        WxSession session = this.getWxSession(false);
        return session != null ? (T) session.getAttribute(key) : null;
    }
    
    /**
     * Store Object to session.
     * @param key a String specifying the key of the Object stored in session
     * @param value a Object specifying the value stored in session
     */
    public Controller setWxSessionAttr(String key, Object value) {
        this.getWxSession(true).setAttribute(key, value);
        return this;
    }
    
    /**
     * Remove Object in session.
     * @param key a String specifying the key of the Object stored in session
     */
    public Controller removeWxSessionAttr(String key) {
        WxSession session = this.getWxSession(false);
        if (session != null)
            session.removeAttribute(key);
        return this;
    }
}













