package com.gl.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtil {

    /**
     * 文件上传
     *
     * @param file       上传的文件
     * @param uploadPath 上传路径
     * @return 文件名
     * @throws IOException
     */
    public static String upload(MultipartFile file, String uploadPath) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件为空");
        }

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 获取后缀名
        String newFileName = UUID.randomUUID().toString().replace("-", "") + suffixName; // 新文件名

        // 创建目录
        File dest = new File(uploadPath);
        if (!dest.exists()) {
            if (!dest.mkdirs()) { //mkdirs()可以创建多级目录，mkdir()只能创建以及目录
                throw new IOException("创建目录失败");
            }
        }

        Path filePath = Paths.get(uploadPath, newFileName);
        Files.copy(file.getInputStream(), filePath); // 使用 NIO 进行文件复制

        return newFileName;
    }

    /**
     * 文件上传并自定义文件名
     *
     * @param file        上传的文件
     * @param uploadPath  上传路径
     * @param newFileName 自定义文件名
     * @return 文件名
     * @throws IOException
     */
    public static String upload(MultipartFile file, String uploadPath, String newFileName) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件为空");
        }

        File dest = new File(uploadPath);
        if (!dest.exists()) {
            if (!dest.mkdirs()) { //mkdirs()可以创建多级目录，mkdir()只能创建以及目录
                throw new IOException("创建目录失败");
            }
        }
        Path filePath = Paths.get(uploadPath, newFileName);
        Files.copy(file.getInputStream(), filePath); // 使用 NIO 进行文件复制

        return newFileName;
    }


    /**
     * 读取文件所有字节
     * 适用于二进制文件（如图片、视频等）
     *
     * @param filePath 文件路径
     * @return 文件内容的字节数组
     * @throws IOException
     */
    public static byte[] readFileAsBytes(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}