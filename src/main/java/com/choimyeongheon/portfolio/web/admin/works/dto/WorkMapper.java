package com.choimyeongheon.portfolio.web.admin.works.dto;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class WorkMapper {

    @Value("${portfolio.upload.path}")
    private String uploadPath;

    public Work toEntity(WorkSaveRequest request) {
        String originName = request.getWork().getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originName.substring(originName.lastIndexOf("\\") + 1);
        String path = uploadPath + File.separator + fileName;
        Path savePath = Paths.get(path);
        try {
            request.getWork().transferTo(savePath);
        } catch (IOException | IllegalStateException e) {
            throw new CustomException(ErrorType.FILE_UPLOAD_EXCEPTION);
        }

        return Work.builder()
                .workDate(request.getWorkDate())
                .title(request.getTitle())
                .originName(originName)
                .fileName(fileName)
                .path(path)
                .build();
    }

    public WorkResponse toResponse(Work work) {
        return new WorkResponse(work.getId(), work.getFileName(), work.getTitle(), work.getWorkDate());
    }

}
