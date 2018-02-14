package com.dminc.dts.budget.tracker.security;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

class SecurityConstants {
    static final Key SECRET = MacProvider.generateKey();
    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
}
