package com.assignment.csvhandler.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CsvRecord {
    @CsvBindByName(column = "source")
    String source;

    @CsvBindByName(column = "codeListCode")
    String codeListCode;

    @CsvBindByName(column = "code")
    String code;

    @CsvBindByName(column = "displayValue")
    String displayValue;

    @CsvBindByName(column = "longDescription")
    String longDescription;

    @CsvBindByName(column = "fromDate")
    String fromDate;

    @CsvBindByName(column = "toDate")
    String toDate;

    @CsvBindByName(column = "sortingPriority")
    Integer sortingPriority;
}

