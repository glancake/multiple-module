package com.gl.util;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimitUtil {

    public static boolean tryAcquire(String key, double permits) {
        return RateLimiter.create(permits).tryAcquire();
    }

    public static  RateLimiter getRateLimiter(double permits) {
        return RateLimiter.create(permits);
    }

}
