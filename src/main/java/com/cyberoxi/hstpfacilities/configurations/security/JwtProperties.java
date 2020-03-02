package com.cyberoxi.hstpfacilities.configurations.security;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/30/2020
 */
class JwtProperties {
    static final String SECRET = "cyberoxi team";
    static final int EXPIRATION_TIME = 854_000_000; // 10 days
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
}
