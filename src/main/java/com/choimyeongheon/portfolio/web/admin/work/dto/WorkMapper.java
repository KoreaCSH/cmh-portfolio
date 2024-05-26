package com.choimyeongheon.portfolio.web.admin.work.dto;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class WorkMapper {

    @Value("${portfolio.upload.path}")
    private String uploadPath;

    public Work toEntity(WorkSaveRequest request, Admin admin) {
        String originName = request.getWork().getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originName.substring(originName.lastIndexOf("\\") + 1);
        String path = uploadPath + File.separator + fileName;
        Path savePath = Paths.get(path);
        try {
            request.getWork().transferTo(savePath);
        } catch (IOException | IllegalStateException e) {
            throw new CustomException(ErrorType.FILE_UPLOAD_EXCEPTION);
        }

        // YYYY-MM String 을 LocalDate 로 변환. DD는 01로 고정
        LocalDate workDate = stringToLocalDate(request.getWorkDate());

        return Work.builder()
                .workDate(workDate)
                .title(request.getTitle())
                .originName(originName)
                .fileName(fileName)
                .path(path)
                .createdBy(admin)
                .build();
    }

    public WorkResponse toResponse(Work work) {
        return new WorkResponse(work.getId(), work.getFileName(), work.getOriginName(), work.getTitle(), work.getWorkDate(), work.getCreatedAt());
    }

    public ResponseEntity<Resource> toResource(String fileName) {

        // Resource 가 무엇인지
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        Path filePath = Paths.get(uploadPath + File.separator + fileName);

        // Exception 대응 추가
        HttpHeaders header = new HttpHeaders();
        try {
            header.add("Content-type", Files.probeContentType(filePath));
        } catch (IOException e) {
            throw new CustomException(ErrorType.FILE_IO_EXCEPTION);
        }

        return new ResponseEntity(resource, header, HttpStatus.OK);
    }

    public LocalDate stringToLocalDate(String strWorkDate) {
        String[] yearAndMonth = strWorkDate.split("-");
        int year = Integer.parseInt(yearAndMonth[0]);
        int month = Integer.parseInt(yearAndMonth[1]);
        int date = Integer.parseInt(yearAndMonth[2]);
        return LocalDate.of(year, month, date);
    }

}
