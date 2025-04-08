package com.jasperdemo.report.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {
    private int id;
    private String name;
    private String type;
    private Long reportId;

}
