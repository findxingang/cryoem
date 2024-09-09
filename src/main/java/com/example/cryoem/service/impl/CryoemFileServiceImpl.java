package com.example.cryoem.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.example.cryoem.exception.BaseException;
import com.example.cryoem.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author wangxingang
 */
@Service
public class CryoemFileServiceImpl implements FileService {
    @Value("${file.path}")
    private String filePath;


    public void download(HttpServletResponse response, String fileName) {
        // 使用 ClassLoader 获取资源文件
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new RuntimeException("文件不存在");
        }

        try {
            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", -1);

            // 复制文件到响应流中
            IoUtil.copy(inputStream, response.getOutputStream());
            response.getOutputStream().flush(); // 确保所有数据发送到客户端
        } catch (IOException e) {
            throw new RuntimeException("读取文件错误", e);
        } finally {
            IoUtil.close(inputStream); // 确保流关闭
        }
    }

    @Override
    public boolean upload(MultipartFile file) {
        try {
            File dir = FileUtil.mkdir(filePath);
            File localFile = FileUtil.file(dir, file.getOriginalFilename());
            IoUtil.copy(file.getInputStream(), FileUtil.getOutputStream(localFile));
        } catch (IOException e) {
            throw new BaseException(StrUtil.format("文件上传失败: {}", e.getMessage()), e);
        }
        return true;
    }

    @Override
    public String downloadString(String fileName) {
        // 使用 ClassLoader 获取资源文件
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new RuntimeException("文件不存在");
        }

        // 使用 BufferedReader 读取文件内容并转换为字符串
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
