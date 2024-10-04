package com.choimyeongheon.portfolio.web.admin.work;

import com.choimyeongheon.portfolio.domain.work.service.WorkService;
import com.choimyeongheon.portfolio.web.visitor.work.dto.VisitorWorkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/works")
@RequiredArgsConstructor
public class ApiWorkController {

    private final WorkService workService;

    @GetMapping("/{id}")
    public ResponseEntity<VisitorWorkResponse> getWorkDetail(@PathVariable(name = "id") Long id) {
        VisitorWorkResponse workResponse = workService.findVisitorWorkResponseById(id);
        return ResponseEntity.ok().body(workResponse);
    }

}
