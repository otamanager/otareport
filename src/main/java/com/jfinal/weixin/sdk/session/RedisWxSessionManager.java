package com.jfinal.weixin.sdk.session;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * redis session 管理器
 * @author L.cm
 */
public class RedisWxSessionManager implements WxSessionManager {
    private final String SESSION_PREFIX = "jfinal-weixin:session:";

    private final Cache cache;

    public RedisWxSessionManager() {
        this.cache = Redis.use();
    }

    public RedisWxSessionManager(String cacheName) {
        this.cache = Redis.use(cacheName);
    }

    public RedisWxSessionManager(Cache cache) {
        this.cache = cache;
    }
    
    @Override
    public WxSession get(String sessionId) {
        return cache.get(SESSION_PREFIX.concat(sessionId));
    }

    @Override
    public void save(WxSession session) {
        cache.setex(SESSION_PREFIX.concat(session.id), TIME_OUT * 60, session);
    }

    @Override
    public void update(WxSession session) {
        save(session);
    }

}
