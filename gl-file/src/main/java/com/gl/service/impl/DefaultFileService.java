package com.gl.service.impl;

import com.gl.service.FileService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DefaultFileService implements FileService {

    @Override
    public boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }

    @Override
    public String getMimeType(Path filePath) throws IOException {
        return Files.probeContentType(filePath);
    }

    @Override
    public byte[] readFileContent(Path filePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(filePath.toFile())) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}