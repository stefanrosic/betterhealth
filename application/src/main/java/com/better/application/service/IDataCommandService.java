package com.better.application.service;

import org.springframework.web.multipart.MultipartFile;

public interface IDataCommandService {

    void processStorageData();

    void processRequestData(MultipartFile multipartFile);
}
