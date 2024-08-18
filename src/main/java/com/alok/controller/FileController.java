package com.alok.controller;

import com.alok.dto.FileDto;
import com.alok.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/write-to-file")
    public String writeToFile(@RequestBody FileDto fileDto) {
        fileService.writeToFile(fileDto);
        return "File written successfully";
    }
}
