package com.better.application.service.impl;

import com.better.application.enums.DocumentSourceType;
import com.better.application.helper.Parser;
import com.better.application.helper.FileUtils;
import com.better.application.model.DoctorModel;
import com.better.application.service.IDoctorService;
import com.better.application.service.IDataCommandService;
import com.better.application.service.IReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@Slf4j
public class DataCommandService implements IDataCommandService {

    private final IDoctorService doctorService;

    private final IReportService reportService;

    private final Parser parser;

    private final FileUtils fileUtils;

    public DataCommandService(IDoctorService doctorService,
                              IReportService reportService, Parser parser,
                              FileUtils fileUtils) {
        this.doctorService = doctorService;
        this.reportService = reportService;
        this.parser = parser;
        this.fileUtils = fileUtils;
    }

    @Scheduled(fixedDelay = 90000)
    @Override
    public void processStorageData() {
        final var startTime = System.currentTimeMillis();

        final var files= fileUtils.getInputFiles();

        if (files.isEmpty()) {
            log.info("Nothing to process, input directory does not contain any file.");
            return;
        }x

        files.forEach(file -> runCommand(file, startTime, DocumentSourceType.FOLDER));
    }

    @Override
    public void processRequestData(MultipartFile multipartFile) {
        final var startTime = System.currentTimeMillis();
        final var file = fileUtils.convertToFile(multipartFile);

        runCommand(file, startTime, DocumentSourceType.HTTP_REQUEST);
    }

    private void runCommand(File file, Long startTime, DocumentSourceType documentSourceType) {
        var error = "";
        var doctorId = 0;

        try {
            final var doctor = parser.parse(file.getPath(), DoctorModel.class);
            doctorId = doctor.getId();
            doctorService.save(doctor);
            log.info(String.format("All the operations have successfully completed"));
        } catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            error = e.getMessage();
        } finally {
            final var endTime = System.currentTimeMillis();
            final var duration =  endTime - startTime;
            reportService.save(doctorId, duration, documentSourceType, error);
            fileUtils.moveFileOut(file, error);
        }
    }
}
