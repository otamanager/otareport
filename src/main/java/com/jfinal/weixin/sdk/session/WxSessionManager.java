package com.jfinal.weixin.sdk.session;

/**
 * session管理类
 * @author L.cm
 */
public interface WxSessionManager {
    /**
     * 默认30分钟的缓存
     */
    int TIME_OUT = 30;
    
    /**
     * 获取WxSession
     * @param sessionId sessionId
     * @return {WxSession}
     */
    WxSession get(String sessionId);
    
    /**
     * 保存 WxSession
     * @param session WxSession
     */
    void save(WxSession session);
    
    /**
     * 更新 WxSession
     * @param session WxSession
     */
    void update(WxSession session);
}
