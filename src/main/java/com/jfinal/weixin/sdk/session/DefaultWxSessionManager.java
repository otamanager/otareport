package com.jfinal.weixin.sdk.session;

import com.jfinal.weixin.sdk.session.cache.TimedCache;

/**
 * 默认的Session管理器
 * @author L.cm
 */
public class DefaultWxSessionManager implements WxSessionManager {

    private final TimedCache<String, WxSession> timedCache;
    
    public DefaultWxSessionManager() {
        this.timedCache = new TimedCache<String, WxSession>(TIME_OUT * 60 * 1000l);
    }

    @Override
    public WxSession get(String sessionId) {
        return timedCache.get(sessionId);
    }

    @Override
    public void save(WxSession session) {
        timedCache.put(session.id, session);
    }

    @Override
    public void update(WxSession session) {
        save(session);
    }

}
