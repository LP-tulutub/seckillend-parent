package com.seckillend.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Configuration
public class DruidMonitoringConfig {

    @Bean
    public ServletRegistrationBean statViewServletDemo() {
        ServletRegistrationBean srb = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
        srb.addInitParameter("allow", "127.0.0.1");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        srb.addInitParameter("deny", "192.168.25.123");
        // 添加控制台管理用户
        srb.addInitParameter("loginUsername", "root");
        srb.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据
        srb.addInitParameter("resetEnable", "false");
        return srb;
    }
    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean statFilterDemo() {
        FilterRegistrationBean frb = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        frb.addUrlPatterns("/*");
        // 忽略过滤格式
        frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        // session 统计功能
        frb.addInitParameter("sessionStatEnable", "false");
        // 缺省 sessionStatMaxCount 是 1000 个
        frb.addInitParameter("sessionStatMaxCount", "1000");
        // 配置 profileEnable 能够监控单个url调用的sql列表
        frb.addInitParameter("profileEnable", "true");
        return frb;
    }

//    @Bean
//    public StatFilter statFilter(){
//        StatFilter statFilter = new StatFilter();
//        statFilter.setLogSlowSql(true); //slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
//        statFilter.setMergeSql(true); //SQL合并配置
//        statFilter.setSlowSqlMillis(1000);//slowSqlMillis的缺省值为3000，也就是3秒。
//        return statFilter;
//    }
//
//    @Bean
//    public WallFilterMBean wallFilter(){
//        WallFilter wallFilter = new WallFilter();
//        wallFilter.setDbType("mysql");
//        WallConfig wallConfig = new WallConfig();
//        wallConfig.setUpdateAllow(false);
//        wallFilter.setConfig(wallConfig);
//        return wallFilter;
//    }
}
