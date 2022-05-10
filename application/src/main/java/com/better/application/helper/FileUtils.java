package com.better.application.helper;

import com.better.application.configuration.ConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class FileUtils {

    private final ConfigProperties configProperties;

    public FileUtils(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public List<File> getInputFiles() {
        final var folder = new File(configProperties.getInput());
        return Arrays.asList(folder.listFiles());
    }

    public File convertToFile(MultipartFile multipartFile) {
        final var file = new File(System.getProperty("java.io.tmpdir")+"/"+multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void moveFileOut(File file, String error) {
        final var errorOutput = configProperties.getError();
        final var output = configProperties.getOutput();

        if (!error.isBlank()) {
            file.renameTo(new File(errorOutput.concat(file.getName())));
            log.info(String.format("File output stored to the %s directory.", errorOutput));
            return;
        }

        file.renameTo(new File(output.concat(file.getName())));
        log.info(String.format("File output stored to the %s directory.", output));
    }
}
