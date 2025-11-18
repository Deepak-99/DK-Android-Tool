package com.hawkshaw.library.datalayer.network.service;

import X1.B;
import a3.e;
import com.hawkshaw.library.datalayer.models.LoginRequest;
import t3.N;

public final class AuthServiceImpl implements AuthService {
    public Object deAuth(e eVar) {
        return B.B(N.f9292c, new AuthServiceImpl$deAuth$$inlined$apiCall$default$1("auth.Auth/DeAuth", new LoginRequest("", ""), false, (e) null), eVar);
    }

    public Object login(LoginRequest loginRequest, e eVar) {
        return B.B(N.f9292c, new AuthServiceImpl$login$$inlined$apiCall$default$1("auth.Auth/AppLogin", loginRequest, false, (e) null), eVar);
    }
}
