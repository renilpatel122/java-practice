package com.jasperdemo.report.repository;

import com.jasperdemo.report.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface empRepo extends JpaRepository<Emp , Integer> {
}
