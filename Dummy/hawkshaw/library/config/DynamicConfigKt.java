package com.hawkshaw.library.config;

import F3.b;
import com.hawkshaw.library.datalayer.models.DynamicConfig;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.preferences.Prefs;

public final class DynamicConfigKt {
    public static final DynamicConfig getDynamicConfig() {
        b json = ContentNegotiationInterceptorKt.getJson();
        String dynamicConfig = Prefs.INSTANCE.getDynamicConfig();
        if (dynamicConfig == null) {
            dynamicConfig = "{}";
        }
        json.getClass();
        return (DynamicConfig) json.a(DynamicConfig.Companion.serializer(), dynamicConfig);
    }

    public static final String getWebViewUrl() {
        return getDynamicConfig().getWebViewUrl();
    }
}
