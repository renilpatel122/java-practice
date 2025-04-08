package com.jasperdemo.report.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "employee")
public class Emp {
    @Id
    private int id  ;
    private String first_name  ;
    private String last_name  ;
    private String email ;
    private String gender  ;
    private String department ;
    private Date joining_date ;

}
