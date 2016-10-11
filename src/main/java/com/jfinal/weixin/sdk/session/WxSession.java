package com.jfinal.weixin.sdk.session;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 微信Session
 * @author L.cm
 */
public class WxSession implements Serializable {
    private static final long serialVersionUID = 8720213770943177196L;
    
    protected final String id;
    protected final Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();
    protected transient WxSessionManager manager;
    
    public WxSession(String id) {
        this.id = id;
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public Set<String> getAttributeNames() {
        return this.attributes.keySet();
    }

    public String getId() {
        return this.id;
    }
    
    public void removeAttribute(String key) {
        attributes.remove(key);
        manager.update(this);
    }
    
    public void setAttribute(String key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("setAttribute: name parameter cannot be null");
        }
        if (value == null) {
            removeAttribute(key);
            return;
        }
        attributes.put(key, value);
        manager.update(this);
    }
    
    public void setManager(WxSessionManager manager) {
        this.manager = manager;
    }
}
