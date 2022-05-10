package com.better.application.controller;

import com.better.application.service.IDataCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file-upload")
public class FileUploadController {

    private final IDataCommandService dataCommandService;

    public FileUploadController(IDataCommandService dataCommandService) {
        this.dataCommandService = dataCommandService;
    }

    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        dataCommandService.processRequestData(file);
    }
}
