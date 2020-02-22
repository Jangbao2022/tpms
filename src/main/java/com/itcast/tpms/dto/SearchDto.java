package com.itcast.tpms.dto;

import lombok.Data;

@Data
public class SearchDto {
    private String keyword;
    private Integer page;
    private Integer offset;
    private Integer total;
    private Integer limit;
}
