package com.sunlx.learn.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${qcloud.oss.file.region}")
    private String region;

    @Value("${qcloud.oss.file.secretId}")
    private String secretId;

    @Value("${qcloud.oss.file.secretKey}")
    private String secretKey;

    @Value("${qcloud.oss.file.bucketName}")
    private String bucketName;

    public static String REGION;
    public static String SECRETID;
    public static  String SECRETKEY;
    public static  String BUCKNAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        REGION=region;
        SECRETID=secretId;
        SECRETKEY=secretKey;
        BUCKNAME=bucketName;
    }
}
