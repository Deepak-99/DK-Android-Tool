package com.hawkshaw.library.datalayer.network.service;

import a3.e;
import com.hawkshaw.library.datalayer.models.LoginRequest;

public interface AuthService {
    Object deAuth(e eVar);

    Object login(LoginRequest loginRequest, e eVar);
}
