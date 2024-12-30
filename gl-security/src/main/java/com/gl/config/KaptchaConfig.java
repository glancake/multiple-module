package com.gl.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    Producer producer() {
        Properties properties = new Properties();
        //设置图片边框
        properties.setProperty("kaptcha.border", "no");
        //设置图片边框为蓝色
//        properties.setProperty("kaptcha.border.color", "blue");
        //背景颜色渐变开始
        properties.put("kaptcha.background.clear.from", "127,255,212");
        //背景颜色渐变结束
        properties.put("kaptcha.background.clear.to", "240,255,255");
        // 字体颜色
        properties.put("kaptcha.textproducer.font.color", "black");
        // 文字间隔
        properties.put("kaptcha.textproducer.char.space", "8");
        //如果需要去掉干扰线
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        // 字体
        properties.put("kaptcha.textproducer.font.names", "Arial,Courier,cmr10,宋体,楷体,微软雅黑");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size","26");
        // 图片宽度
        properties.setProperty("kaptcha.image.width", "150");
        // 图片高度
        properties.setProperty("kaptcha.image.height", "50");
        // 从哪些字符中产生
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789abcdefghijklmnopqrsduvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // 字符个数
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }

}
