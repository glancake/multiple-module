package com.gl.controller;


import cn.hutool.core.lang.UUID;
import com.gl.exception.BizException;
import com.gl.util.JedisPoolSingleton;
import com.gl.util.RateLimitUtil;
import com.google.code.kaptcha.Producer;
import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.RateLimitFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class KaptchaController {

    private final Producer producer;
    private final RateLimiter getVerifyCode = RateLimitUtil.getRateLimiter( 1.0 / 5);

    @GetMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session) throws IOException, BizException {

        if (!getVerifyCode.tryAcquire()){
            throw new BizException("请求过于频繁");
        }

        // 生成唯一的 client_id
        String clientId = UUID.randomUUID().toString();

        // 创建 Cookie 并设置 client_id
        Cookie cookie = new Cookie("client_id", clientId);
//        cookie.setSecure(true);    // 设置为 Secure 以确保通过 HTTPS 传输
        cookie.setPath("/");       // 设置 Cookie 的路径
        cookie.setMaxAge(3600);    // 设置 Cookie 的过期时间（例如 1 小时）

        // 将 Cookie 添加到响应中
        response.addCookie(cookie);
        // 设置响应内容类型为图片
        response.setContentType("image/jpeg");
        // 获取随机验证码文本
        String text = producer.createText();
        session.setAttribute("verifyCode", text);
        // 存储到redis
        Jedis resource = JedisPoolSingleton.getInstance().getResource();
        SetParams setParams = new SetParams();
        // 三分钟过期时间
        setParams.ex(60 * 3);
        resource.set(clientId, text,setParams);
        // 生成验证码图片
        BufferedImage image = producer.createImage(text);
        // 将图片写入响应中
        try (ServletOutputStream sos = response.getOutputStream()) {
            ImageIO.write(image, "jpg", sos);
        }
    }
}
