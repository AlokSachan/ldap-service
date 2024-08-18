package com.alok.service;

import com.alok.dto.FileDto;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileServiceImpl implements FileService {
    public void writeToFile(FileDto fileDto) {

        String directory = "D:\\file-operation\\";
        String fileName = generateFileName(directory);
        // String fullFileName= directory.concat(fileName);
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND, StandardOpenOption.APPEND)) {
                writer.newLine();
                writer.write(fileDto.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(String.valueOf(fileDto));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateFileName(String directory) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String timestamp = LocalDateTime.now().format(formatter);
        return Paths.get(directory, "hsbc_" + timestamp + ".txt").toString();
    }
}
