package com.gl.controller;

import com.gl.entity.FileUploadPath;
import com.gl.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {
    private final FileUploadPath fileUploadPath;
    private final FileService fileService;

    @GetMapping("/avatar/{filename}")
    public void showImage(@PathVariable String filename, HttpServletResponse response) throws IOException {
        Path filePath = Paths.get(fileUploadPath.getAvatarPath(), filename);

        if (!fileService.fileExists(filePath)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String mimeType = fileService.getMimeType(filePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream"; // Default type
        }
        response.setContentType(mimeType);
        response.setHeader(HttpHeaders.CACHE_CONTROL, "public, max-age=3600"); // Set cache

        byte[] content = fileService.readFileContent(filePath);
        response.getOutputStream().write(content);
    }

}
