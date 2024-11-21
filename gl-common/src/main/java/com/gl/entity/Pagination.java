package com.gl.entity;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class Pagination {
    @Min(value = 1)
    private Integer currentPage = 1;
    private Integer pageSize=10;
    private Integer total;
}
