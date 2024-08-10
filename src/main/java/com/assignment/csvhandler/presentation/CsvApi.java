package com.assignment.csvhandler.presentation;

import com.assignment.csvhandler.entity.CsvRecordEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/csv")
public interface CsvApi {

    @PostMapping()
    ResponseEntity<String> uploadCsvToDatabase(@RequestParam("file") MultipartFile file) throws IOException;

    @GetMapping
    ResponseEntity<List<CsvRecordEntity>> getAllCsvData();

    @GetMapping("/{code}")
    ResponseEntity<CsvRecordEntity> getCsvDataByCode(@PathVariable String code);

    @DeleteMapping
    ResponseEntity<String> deleteAll();
}
