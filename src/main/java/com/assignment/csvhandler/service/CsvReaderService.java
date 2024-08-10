package com.assignment.csvhandler.service;

import com.assignment.csvhandler.dto.CsvRecord;
import com.assignment.csvhandler.entity.CsvRecordEntity;
import com.assignment.csvhandler.repository.CsvRecordRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvReaderService {

    private final CsvRecordRepository csvRecordRepository;

    public List<CsvRecord> readCsv(MultipartFile file) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            return new CsvToBeanBuilder<CsvRecord>(reader)
                    .withType(CsvRecord.class)
                    .build()
                    .parse();
        }
    }

    public void saveCsvToDatabase(MultipartFile file) throws IOException {
        List<CsvRecord> records = readCsv(file);

        for (CsvRecord csvRecord : records) {
            CsvRecordEntity csvEntity = CsvRecordEntity.builder()
                    .source(csvRecord.getSource())
                    .codeListCode(csvRecord.getCodeListCode())
                    .code(csvRecord.getCode())
                    .displayValue(csvRecord.getDisplayValue())
                    .longDescription(csvRecord.getLongDescription())
                    .fromDate(csvRecord.getFromDate())
                    .toDate(csvRecord.getToDate())
                    .sortingPriority(csvRecord.getSortingPriority()).build();
            csvRecordRepository.save(csvEntity);
        }
    }

    public List<CsvRecordEntity> getAllCsvData() {
        return csvRecordRepository.findAll();
    }

    public CsvRecordEntity getCsvRecordEntityByCode(String code) {
        return csvRecordRepository.findByCode(code)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void removeAll() {
        csvRecordRepository.deleteAll();
    }
}
