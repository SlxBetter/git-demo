package com.sunlx.learn.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 文件上传至腾讯云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
