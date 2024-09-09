package com.example.cryoem.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxingang
 */
public interface FileService {

    void download(HttpServletResponse response, String fileName);

    boolean upload(MultipartFile file);

    String downloadString(String fileName);
}
