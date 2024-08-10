package com.assignment.csvhandler.presentation.controller;

import com.assignment.csvhandler.entity.CsvRecordEntity;
import com.assignment.csvhandler.presentation.CsvApi;
import com.assignment.csvhandler.service.CsvReaderService;
import com.assignment.csvhandler.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvController implements CsvApi {

    private final CsvReaderService csvReaderService;
    private final ValidationService validationService;

    @Override
    public ResponseEntity<String> uploadCsvToDatabase(MultipartFile file) throws IOException {
        validationService.validateFileUpload(file);
        csvReaderService.saveCsvToDatabase(file);
        return ResponseEntity.ok().body("CSV File data uploaded");
    }

    @Override
    public ResponseEntity<List<CsvRecordEntity>> getAllCsvData() {
        return ResponseEntity.ok().body(csvReaderService.getAllCsvData());
    }

    @Override
    public ResponseEntity<CsvRecordEntity> getCsvDataByCode(String code) {
        return ResponseEntity.ok().body(csvReaderService.getCsvRecordEntityByCode(code));
    }

    @Override
    public ResponseEntity<String> deleteAll() {
        return ResponseEntity.ok().body("All records deleted from Database");
    }
}
