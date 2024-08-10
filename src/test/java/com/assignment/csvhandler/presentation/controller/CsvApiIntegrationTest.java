package com.assignment.csvhandler.presentation.controller;

import com.assignment.csvhandler.entity.CsvRecordEntity;
import com.assignment.csvhandler.repository.CsvRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CsvApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CsvRecordRepository csvRecordRepository;

    @BeforeEach
    void setUp() {
        csvRecordRepository.deleteAll();
    }

    @Test
    void testUploadCsvToDatabase() throws Exception {
        String csvContent = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority\n" +
                "source1,codeList1,code1,display1,longDesc1,2023-01-01,2024-01-01,1";
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

        mockMvc.perform(multipart("/csv")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("CSV File data uploaded"));
    }

    @Test
    void testUploadCsvToDatabaseException() throws Exception {
        String csvContent = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority\n" +
                "source1,codeList1,code1,display1,longDesc1,2023-01-01,2024-01-01,1";
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/txt", csvContent.getBytes());

        mockMvc.perform(multipart("/csv")
                        .file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This file format is not supported for upload"));
    }

    @Test
    void testGetAllCsvData() throws Exception {
        CsvRecordEntity record = new CsvRecordEntity();
        record.setSource("source1");
        record.setCodeListCode("codeList1");
        record.setCode("code1");
        record.setDisplayValue("display1");
        record.setLongDescription("longDesc1");
        record.setFromDate("2023-01-01");
        record.setToDate("2024-01-01");
        record.setSortingPriority(1);
        csvRecordRepository.save(record);

        mockMvc.perform(get("/csv")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code").value("code1"));
    }

    @Test
    void testGetCsvDataByCode() throws Exception {
        CsvRecordEntity record = new CsvRecordEntity();
        record.setSource("source1");
        record.setCodeListCode("codeList1");
        record.setCode("code1");
        record.setDisplayValue("display1");
        record.setLongDescription("longDesc1");
        record.setFromDate("2023-01-01");
        record.setToDate("2024-01-01");
        record.setSortingPriority(1);
        csvRecordRepository.save(record);

        mockMvc.perform(get("/csv/code1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("code1"));
    }

    @Test
    void testGetCsvDataByCodeException() throws Exception {
        mockMvc.perform(get("/csv/code1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Record with the given Code is Not found in the database"));

    }

    @Test
    void testDeleteAll() throws Exception {
        CsvRecordEntity record = new CsvRecordEntity();
        record.setSource("source1");
        record.setCodeListCode("codeList1");
        record.setCode("code1");
        record.setDisplayValue("display1");
        record.setLongDescription("longDesc1");
        record.setFromDate("2023-01-01");
        record.setToDate("2024-01-01");
        record.setSortingPriority(1);
        csvRecordRepository.save(record);

        mockMvc.perform(delete("/csv"))
                .andExpect(status().isOk())
                .andExpect(content().string("All records deleted from Database"));

    }
}
