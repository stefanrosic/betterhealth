package com.better.application.service;

import com.better.application.enums.DocumentSourceType;

public interface IReportService {

    void save(Integer doctorId, Long duration, DocumentSourceType documentSourceType, String error);
}
