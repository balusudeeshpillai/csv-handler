package com.assignment.csvhandler.presentation.service;

import com.assignment.csvhandler.dto.CsvRecord;
import com.assignment.csvhandler.entity.CsvRecordEntity;
import com.assignment.csvhandler.repository.CsvRecordRepository;
import com.assignment.csvhandler.service.CsvReaderService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CsvReaderServiceTest {
    @Mock
    private CsvRecordRepository csvRecordRepository;
    @InjectMocks
    private CsvReaderService csvReaderService;

    @Test
    void testSaveCsvToDatabase() throws Exception {
        String csvContent = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority\n" +
                "source1,codeList1,code1,display1,longDesc1,2023-01-01,2024-01-01,1";
        MultipartFile file = new MockMultipartFile("file.csv", "file.csv", "text/csv", csvContent.getBytes());

        CsvRecord csvRecord = new CsvRecord();
        csvRecord.setSource("source1");
        csvRecord.setCodeListCode("codeList1");
        csvRecord.setCode("code1");
        csvRecord.setDisplayValue("display1");
        csvRecord.setLongDescription("longDesc1");
        csvRecord.setFromDate("2023-01-01");
        csvRecord.setToDate("2024-01-01");
        csvRecord.setSortingPriority(1);

        List<CsvRecord> records = List.of(csvRecord);

        CsvReaderService spyService = spy(csvReaderService);
        doReturn(records).when(spyService).readCsv(any(MultipartFile.class));

        spyService.saveCsvToDatabase(file);

        verify(spyService, times(1)).readCsv(any(MultipartFile.class)); // Verify that readCsv was called
        verify(csvRecordRepository, times(1)).save(any(CsvRecordEntity.class)); // Verify that save was called on the repository
    }

    @Test
    void testAll() {
        assertNotNull(csvReaderService.getAllCsvData());
    }

    @Test
    void testGetCsvRecordEntityByCode_Found() {
        String code = "code1";
        CsvRecordEntity csvRecordEntity = new CsvRecordEntity();
        csvRecordEntity.setCode(code);

        when(csvRecordRepository.findByCode(code)).thenReturn(Optional.of(csvRecordEntity));

        CsvRecordEntity result = csvReaderService.getCsvRecordEntityByCode(code);

        assertNotNull(result);
        assertEquals(code, result.getCode());
        verify(csvRecordRepository, times(1)).findByCode(code);
    }

    @Test
    void testGetCsvRecordEntityByCode_NotFound() {
        String code = "code1";
        when(csvRecordRepository.findByCode(code)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> csvReaderService.getCsvRecordEntityByCode(code));

    }

    @Test
    void testRemoveAll() {
        csvReaderService.removeAll();
        verify(csvRecordRepository, times(1)).deleteAll();
    }
}
