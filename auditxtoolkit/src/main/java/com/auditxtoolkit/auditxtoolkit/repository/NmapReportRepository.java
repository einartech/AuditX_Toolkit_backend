package com.auditxtoolkit.auditxtoolkit.repository;

import com.auditxtoolkit.auditxtoolkit.model.NmapReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NmapReportRepository extends JpaRepository<NmapReport, Long> {
    List<NmapReport> findByUserId(Integer userId);
}