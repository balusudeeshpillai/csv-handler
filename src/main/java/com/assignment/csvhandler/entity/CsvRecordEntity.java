package com.assignment.csvhandler.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "csvdata")
@Getter
@Setter
//Avoiding Lombok @Data as it has performance issues with Entity Classes
public class CsvRecordEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private Integer sortingPriority;

}
