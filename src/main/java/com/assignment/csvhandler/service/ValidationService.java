package com.assignment.csvhandler.service;

import com.assignment.csvhandler.exceptions.UnsupportedFileFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Service
public class ValidationService {

    public void validateFileUpload(MultipartFile file) throws FileNotFoundException {

        if (file.isEmpty())
            throw new FileNotFoundException();
        if (!file.getContentType().equals("text/csv"))
            throw new UnsupportedFileFormatException();

    }

}
