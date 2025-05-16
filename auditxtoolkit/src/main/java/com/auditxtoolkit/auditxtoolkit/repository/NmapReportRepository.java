package com.auditxtoolkit.auditxtoolkit.repository;

import com.auditxtoolkit.auditxtoolkit.model.NmapReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NmapReportRepository extends JpaRepository<NmapReport, Long> {
}