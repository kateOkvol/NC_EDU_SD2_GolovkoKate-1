package com.netcracker.fapi.security;

public abstract class SecurityJwtConstants {
    static final long ACCESS_TOKEN_VALIDITY_SECONDS = 20*60*60;
    static final String SIGNING_KEY = "j546h87378hfchg7dcvjkuugbhhbjfm";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static final String AUTHORITIES_KEY = "scopes";
}
