package com.better.application.service.impl;

import com.better.application.entity.Report;
import com.better.application.enums.DocumentSourceType;
import com.better.application.repository.IReportRepository;
import com.better.application.service.IReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final IReportRepository reportRepository;

    public ReportService(IReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void save(Integer doctorId, Long duration, DocumentSourceType documentSourceType, String error) {
        final var report = Report.builder()
                .doctorId(doctorId)
                .executionTime(duration)
                .documentSourceType(documentSourceType)
                .error(error)
                .build();

        reportRepository.save(report);
    }
}
