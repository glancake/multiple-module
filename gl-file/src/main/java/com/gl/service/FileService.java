package com.gl.service;

import java.io.IOException;
import java.nio.file.Path;

public interface FileService {

    boolean fileExists(Path filePath);

    String getMimeType(Path filePath) throws IOException;

    byte[] readFileContent(Path filePath) throws IOException;
}