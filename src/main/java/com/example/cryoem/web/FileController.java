package com.example.cryoem.web;

import com.example.cryoem.exception.Result;
import com.example.cryoem.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxingang
 */
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @GetMapping
    public void download(HttpServletResponse response, String fileName) {
        fileService.download(response, fileName);
    }

    @PostMapping("upload")
    public Result<String> upload(MultipartFile file) {
        boolean success = fileService.upload(file);
        return success ? Result.ok("上传成功") : Result.error("上传失败");
    }

}
