package com.choimyeongheon.portfolio.domain.work.domain;

import com.choimyeongheon.portfolio.web.admin.work.dto.WorkYearDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_year_id")
    private Long id;

    private Integer year;

    public WorkYear(WorkYearDto dto) {
        this.year = dto.getWorkYear();
    }

}
